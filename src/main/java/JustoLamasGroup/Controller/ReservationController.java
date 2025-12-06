// src/main/java/JustoLamasGroup/Controller/ReservationController.java
package JustoLamasGroup.Controller;

import JustoLamasGroup.DTO.ReservationExternalRequest;
import JustoLamasGroup.DTO.ReserveTicketLeadRequest;
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


    // 🔥 NUEVO → Eliminar reserva
    @DeleteMapping("/{ShowDateId}")
    public void delete(@PathVariable Long ShowDateId) {
        bookingService.deleteReservation(ShowDateId);
    }

    // 🔥 NUEVO → Actualizar asientos
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
