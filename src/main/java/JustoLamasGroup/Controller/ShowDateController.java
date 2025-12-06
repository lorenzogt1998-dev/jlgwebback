// src/main/java/JustoLamasGroup/Controller/ShowDateController.java
package JustoLamasGroup.Controller;

import JustoLamasGroup.DTO.ShowDateExternalRequest;
import JustoLamasGroup.DTO.UpdateShowStatusRequest;
import JustoLamasGroup.Entity.ShowDate;
import JustoLamasGroup.Service.BookingService;
import JustoLamasGroup.Service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show-dates")
@CrossOrigin(origins = "http://localhost:5173")
public class ShowDateController {

    private final BookingService bookingService;
    private final MailService mailService;

    public ShowDateController(BookingService bookingService,
                              MailService mailService) {
        this.bookingService = bookingService;
        this.mailService = mailService;
    }

    // Crear una fecha para un tour específico
    @PostMapping("/{tourId}")
    public ShowDate create(@PathVariable Long tourId,
                           @RequestBody ShowDate showDate) {

        ShowDate saved = bookingService.createShowDate(tourId, showDate);

        ShowDateExternalRequest dto = ShowDateExternalRequest.fromEntity(saved);
        mailService.sendShowDateRequest(dto);

        return saved;
    }

    // Listar todas las fechas (de todos los tours)
    @GetMapping
    public List<ShowDate> list() {
        return bookingService.getAllShowDates();
    }

    // SOLO fechas con status = OPEN (para el formulario público)
    @GetMapping("/open")
    public List<ShowDate> listOpenShows() {
        return bookingService.getOpenShowDates();
    }

    //  actualizar status de un show
    @PatchMapping("/{id}/status")
    public ShowDate updateStatus(@PathVariable Long id,
                                 @RequestBody UpdateShowStatusRequest req) {
        return bookingService.updateShowStatus(id, req.status());
    }

    // eliminar una fecha de show por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.deleteShowDate(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener una sola fecha por id
    @GetMapping("/{id}")
    public ResponseEntity<ShowDate> getOne(@PathVariable Long id) {
        return bookingService.getShowDateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
