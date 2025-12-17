// src/main/java/JustoLamasGroup/Service/MailService.java
package JustoLamasGroup.Service;

import JustoLamasGroup.DTO.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.to.primary}")
    private String primaryTo;

    @Value("${app.mail.to.cc}")
    private String ccTo;

    @Value("${app.mail.from}")
    private String from;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Email cuando se crea / registra una nueva fecha de show (ShowDate).
     */
    public void sendShowDateRequest(ShowDateExternalRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(primaryTo);
        message.setCc(ccTo);

        message.setSubject("[SET A DATE] Nueva fecha de show creada");

        String body = """
                Nueva fecha de show registrada

                Fecha: %s
                Ciudad: %s
                Escuela: %s
                Horario: %s - %s
                Status: %s
                """.formatted(
                request.date(),
                request.address(),
                request.schoolName(),
                request.startTime(),
                request.endTime(),
                request.status()
        );

        message.setText(body);
        mailSender.send(message);
    }

    /**
     * Email cuando se crea una nueva reserva de tickets.
     */
    public void sendTicketReservation(ReservationExternalRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(primaryTo);
        message.setCc(ccTo);

        message.setSubject("[RESERVE TICKET] Nueva reserva");

        String body = """
                Nueva reserva de tickets (RESERVE TICKET)

                ShowDate ID: %s
                Organización: %s

                Nombre de contacto: %s
                Email de contacto: %s
                Teléfono de contacto: %s

                Asientos solicitados: %s
                Asientos confirmados: %s

                Creado en: %s
                """.formatted(
                request.showDateId(),
                request.organizationName(),
                request.contactName(),
                request.contactEmail(),
                request.contactPhone(),
                request.seatsRequested(),
                request.seatsConfirmed(),
                request.createdAt()
        );

        message.setText(body);
        mailSender.send(message);
    }


    // ================== LEADS DESDE REACT ==================

    // SET A DATE
    public void sendSetDateLead(SetDateLeadRequest r) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(primaryTo);
        message.setCc(ccTo);
        message.setSubject("[SET A DATE] Nueva solicitud de fecha");

        String body = """
                Nueva solicitud de SET A DATE

                CONTACT INFORMATION
                - Full Name: %s
                - Email: %s
                - Phone: %s

                SCHOOL INFORMATION
                - School Name: %s
                - School phone: %s
                - Address: %s
                - Estimated Capacity (students): %s

                PREFERRED DATE
                - Preferred Date: %s

                ADDITIONAL DETAILS
                %s
                """.formatted(
                r.contactName(),
                r.email(),
                r.cellphone(),
                r.school(),
                r.schoolphone(),
                r.address(),
                r.capacity(),
                r.preferredDate(),
                r.notes()
        );

        message.setText(body);
        mailSender.send(message);
    }

    // RESERVE TICKET
    public void sendReserveTicketLead(ReserveTicketLeadRequest r) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(primaryTo);
        message.setCc(ccTo);
        message.setSubject("[RESERVE TICKET] Nueva reserva preliminar");

        String body = """
                Nueva reserva de tickets (lead)

                TOUR DATE
                - Selected option: %s

                CONTACT INFORMATION
                - Full Name: %s
                - Email: %s
                - Phone: %s

                SCHOOL RESERVATION
                - School Name: %s
                - School Address: %s
                - Number of Students: %s

                NOTES / SPECIAL REQUESTS
                %s
                """.formatted(
                r.tourDate(),
                r.contactName(),
                r.email(),
                r.phone(),
                r.school(),
                r.schoolAddress(),
                r.students(),
                r.notes()
        );

        message.setText(body);
        mailSender.send(message);
    }

    // CONTACT FORM (desde React)
    public void sendContactLead(ContactLeadRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(primaryTo);
        message.setCc(ccTo);

        message.setSubject("[CONTACT] New message from website");

        String body = """
                New contact from website

                Name:  %s
                Email: %s

                Enquiry:
                %s
                """
                .formatted(
                        request.getName(),
                        request.getEmail(),
                        request.getEnquiry()
                );

        message.setText(body);
        mailSender.send(message);
    }


}
