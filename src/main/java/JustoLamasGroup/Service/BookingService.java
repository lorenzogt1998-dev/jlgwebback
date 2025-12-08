// src/main/java/JustoLamasGroup/Service/BookingService.java
package JustoLamasGroup.Service;

import JustoLamasGroup.DTO.ReserveTicketLeadRequest;
import JustoLamasGroup.DTO.UpdateReservationFullRequest;
import JustoLamasGroup.DTO.UpdateSeatsRequest;
import JustoLamasGroup.Entity.ShowDate;
import JustoLamasGroup.Entity.TicketReservation;
import JustoLamasGroup.Entity.Tour;
import JustoLamasGroup.Repository.ShowDateRepository;
import JustoLamasGroup.Repository.TicketReservationRepository;
import JustoLamasGroup.Repository.TourRepository;
import JustoLamasGroup.Specifications.ShowDateSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final ShowDateRepository showDateRepository;
    private final TicketReservationRepository reservationRepository;
    private final TourRepository tourRepository;
    private final ExternalCalendarClient externalCalendarClient;

    public BookingService(ShowDateRepository showDateRepository,
                          TicketReservationRepository reservationRepository, TourRepository tourRepository,
                          ExternalCalendarClient externalCalendarClient) {
        this.showDateRepository = showDateRepository;
        this.reservationRepository = reservationRepository;
        this.tourRepository = tourRepository;
        this.externalCalendarClient = externalCalendarClient;
    }

    // ---------- TOURS ----------

    @Transactional
    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }


    @Transactional
    public Tour updateTour(Long id, Tour updatedTour) {
        Tour existing = tourRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found: " + id));

        // actualizá los campos que realmente usás en el formulario
        if (updatedTour.getName() != null) {
            existing.setName(updatedTour.getName());
        }
        if (updatedTour.getYear() != null) {
            existing.setYear(updatedTour.getYear());
        }

        return tourRepository.save(existing);
    }

    // ---------- SHOW DATES ----------

    @Transactional
    public ShowDate createShowDate(Long tourId, ShowDate showDate) {

        // 1) Buscar el tour
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found: " + tourId));

        // 2) Asociar el showDate al tour
        showDate.setTour(tour);

        // 3) Guardar en DB
        ShowDate saved = showDateRepository.save(showDate);

        // 4) (Opcional) Enviar al calendario externo
        try {
            externalCalendarClient.createShowDate(saved);
        } catch (Exception e) {
            // manejar error
        }

        return saved;
    }


    public List<ShowDate> getAllShowDates() {
        return showDateRepository.findAll();
    }

    public List<ShowDate> searchShowDates(String city, String venueName, LocalDate date) {
        Specification<ShowDate> spec = Specification.where(null);

        if (city != null && !city.isEmpty()) {
            spec = spec.and(ShowDateSpecification.hasCity(city));
        }
        if (venueName != null && !venueName.isEmpty()) {
            spec = spec.and(ShowDateSpecification.hasVenue(venueName));
        }
        if (date != null) {
            spec = spec.and(ShowDateSpecification.hasDate(date));
        }

        return showDateRepository.findAll(spec);
    }

    // ---------- RESERVAS ----------

    @Transactional
    public TicketReservation reserveTickets(ReserveTicketLeadRequest req) {
        // 1) Buscar la fecha relacionada
        ShowDate showDate = showDateRepository.findById(req.showDateId())
                .orElseThrow(() -> new IllegalArgumentException("Show date not found: " + req.showDateId()));

        // 2) Mapear DTO -> entidad
        TicketReservation reservation = new TicketReservation();
        reservation.setShowDate(showDate);

        reservation.setOrganizationName(req.school());
        reservation.setContactName(req.contactName());
        reservation.setContactEmail(req.email());
        reservation.setContactPhone(req.phone());



        reservation.setSeatsRequested(req.students());
        // si al inicio no confirmás, podés dejar seatsConfirmed = null o = seatsRequested
        reservation.setSeatsConfirmed(null);

        reservation.setNotes(req.notes());
        reservation.setCreatedAt(LocalDateTime.now());

        // 3) Guardar en DB
        TicketReservation saved = reservationRepository.save(reservation);

        // 4) Sincronizar con calendario externo
        try {
            externalCalendarClient.createReservation(saved);
        } catch (Exception e) {
            // manejar error de sync
        }

        return saved;
    }

    @Transactional
    public ShowDate createShowDateForTour(Long tourId, ShowDate showDate) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found: " + tourId));

        showDate.setTour(tour);
        ShowDate saved = showDateRepository.save(showDate);

        try {
            externalCalendarClient.createShowDate(saved);
        } catch (Exception e) {
            // loguear si querés
        }

        return saved;
    }

    // NUEVO: listar todas las reservas
    public List<TicketReservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteShowDate(Long id) {
        // opcional: validar que exista antes
        if (!showDateRepository.existsById(id)) {
            throw new IllegalArgumentException("ShowDate not found: " + id);
        }
        showDateRepository.deleteById(id);
    }

    public void deleteTour(Long id) {
        // opcional: validar que exista antes
        if (!tourRepository.existsById(id)) {
            throw new IllegalArgumentException("tour not found: " + id);
        }
        tourRepository.deleteById(id);
    }


    public ShowDate updateShowStatus(Long id, String newStatus) {
        ShowDate showDate = showDateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ShowDate not found: " + id));

        showDate.setStatus(newStatus); // asumiendo que tenés getStatus/setStatus en la entidad
        return showDateRepository.save(showDate);
    }

    //  eliminar reserva
    public void deleteReservation(Long ShowDateId) {
        if (!reservationRepository.existsById(ShowDateId)) {
            throw new RuntimeException("Reservation not found");
        }
        reservationRepository.deleteById(ShowDateId);
    }

    //  actualizar asientos
    public TicketReservation updateSeats(Long id, UpdateSeatsRequest req) {
        TicketReservation reservation =
                reservationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (req.seatsRequested() != null) {
            reservation.setSeatsRequested(req.seatsRequested());
        }
        if (req.seatsConfirmed() != null) {
            reservation.setSeatsConfirmed(req.seatsConfirmed());
        }

        return reservationRepository.save(reservation);
    }

    public List<ShowDate> getOpenShowDates() {
        return showDateRepository.findByStatus("OPEN");
    }

    public Optional<ShowDate> getShowDateById(Long id) {
        return showDateRepository.findById(id);
    }

    public TicketReservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found: " + id));
    }

    public List<TicketReservation> getReservationsByShowDate(LocalDate date) {
        return reservationRepository.findByShowDate_Date(date);
    }

    // Actualización completa de la reserva
    public TicketReservation updateReservationFull(Long id, UpdateReservationFullRequest req) {

        TicketReservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (req.organizationName() != null) r.setOrganizationName(req.organizationName());
        if (req.contactName() != null) r.setContactName(req.contactName());
        if (req.contactEmail() != null) r.setContactEmail(req.contactEmail());
        if (req.contactPhone() != null) r.setContactPhone(req.contactPhone());
        if (req.city() != null) r.setCity(req.city());
        if (req.state() != null) r.setState(req.state());
        if (req.grades() != null) r.setGrades(req.grades());
        if (req.notes() != null) r.setNotes(req.notes());
        if (req.seatsRequested() != null) r.setSeatsRequested(req.seatsRequested());
        if (req.seatsConfirmed() != null) r.setSeatsConfirmed(req.seatsConfirmed());

        return reservationRepository.save(r);
    }


    @Transactional
    public TicketReservation confirmReservation(Long id, UpdateSeatsRequest req) {
        TicketReservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found: " + id));

        if (req.seatsConfirmed() == null) {
            throw new IllegalArgumentException("seatsConfirmed is required to confirm reservation");
        }

        reservation.setSeatsConfirmed(req.seatsConfirmed());

        return reservationRepository.save(reservation);
    }

}
