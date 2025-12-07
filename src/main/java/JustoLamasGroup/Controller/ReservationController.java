// src/main/java/JustoLamasGroup/Controller/ReservationController.java
package JustoLamasGroup.Controller;

import JustoLamasGroup.DTO.ReservationExternalRequest;
import JustoLamasGroup.DTO.ReserveTicketLeadRequest;
<<<<<<< HEAD
=======
import JustoLamasGroup.DTO.UpdateReservationFullRequest;
>>>>>>> f179150 (se agrega dto para editar por completo las reservas)
import JustoLamasGroup.DTO.UpdateSeatsRequest;
import JustoLamasGroup.Entity.ShowDate;
import JustoLamasGroup.Entity.TicketReservation;
import JustoLamasGroup.Repository.ShowDateRepository;
import JustoLamasGroup.Service.BookingService;
import JustoLamasGroup.Service.MailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservationController {

    private final BookingService bookingService;
    private final MailService mailService;

    public ReservationController(BookingService bookingService,
                                 MailService mailService) {
        this.bookingService = bookingService;
        this.mailService = mailService;
    }

    @PostMapping
    public TicketReservation create(@RequestBody ReserveTicketLeadRequest request) {
        // 1) Lógica de negocio en el service
        TicketReservation saved = bookingService.reserveTickets(request);

        // 2) Enviar correo
        ReservationExternalRequest dto = ReservationExternalRequest.fromEntity(saved);
        mailService.sendTicketReservation(dto);

        // 3) Devolver lo guardado
        return saved;
    }
    // NUEVO: listar todas las reservas
    @GetMapping
    public List<TicketReservation> list() {
        return bookingService.getAllReservations();
    }


<<<<<<< HEAD
    // 🔥 NUEVO → Eliminar reserva
=======
    //  NUEVO → Eliminar reserva
>>>>>>> f179150 (se agrega dto para editar por completo las reservas)
    @DeleteMapping("/{ShowDateId}")
    public void delete(@PathVariable Long ShowDateId) {
        bookingService.deleteReservation(ShowDateId);
    }

<<<<<<< HEAD
    // 🔥 NUEVO → Actualizar asientos
=======
    //  NUEVO → Actualizar asientos
>>>>>>> f179150 (se agrega dto para editar por completo las reservas)
    @PatchMapping("/{id}")
    public TicketReservation updateSeats(
            @PathVariable Long id,
            @RequestBody UpdateSeatsRequest request
    ) {
        return bookingService.updateSeats(id, request);
    }


    // GET ONE
    @GetMapping("/{id}")
    public TicketReservation getOne(@PathVariable Long id) {
        return bookingService.getReservationById(id);
    }

    // LIST BY SHOWDATE
    @GetMapping("/by-show/{showDateId}")
    public List<TicketReservation> listByShow(@PathVariable Long showDateId) {
        return bookingService.getReservationsByShowDate(showDateId);
    }

<<<<<<< HEAD
=======
    // Editar todos los campos de una reservation
    @PatchMapping("/{id}/full")
    public TicketReservation updateFull(
            @PathVariable Long id,
            @RequestBody UpdateReservationFullRequest req
    ) {
        return bookingService.updateReservationFull(id, req);
    }

>>>>>>> f179150 (se agrega dto para editar por completo las reservas)
    // (OPCIONAL) Confirmar reserva / seatsConfirmados
    @PatchMapping("/{id}/confirm")
    public TicketReservation confirmReservation(
            @PathVariable Long id,
            @RequestBody UpdateSeatsRequest request
    ) {
        // por ejemplo: solo actualizar seatsConfirmed y dejar seatsRequested igual
        return bookingService.confirmReservation(id, request);
    }
}
