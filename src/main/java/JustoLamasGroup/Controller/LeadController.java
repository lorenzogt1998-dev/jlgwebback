// src/main/java/JustoLamasGroup/Controller/LeadController.java
package JustoLamasGroup.Controller;

import JustoLamasGroup.DTO.ContactLeadRequest;
import JustoLamasGroup.DTO.SetDateLeadRequest;
import JustoLamasGroup.DTO.ReserveTicketLeadRequest;
import JustoLamasGroup.Service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin(origins = "http://localhost:5173")   // ⬅️ front Vite
public class LeadController {

    private final MailService mailService;

    public LeadController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/set-date")
    public ResponseEntity<Void> handleSetDate(@RequestBody SetDateLeadRequest request) {
        mailService.sendSetDateLead(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reserve-ticket")
    public ResponseEntity<Void> handleReserveTicket(@RequestBody ReserveTicketLeadRequest request) {
        mailService.sendReserveTicketLead(request);
        return ResponseEntity.ok().build();
    }

    // 👇 NUEVO: formulario de contacto
    @PostMapping("/contact")
    public ResponseEntity<Void> handleContact(@RequestBody ContactLeadRequest request) {
        mailService.sendContactLead(request);
        return ResponseEntity.ok().build();
    }

}
