package JustoLamasGroup.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDateTime;

// Reserva de tickets para una fecha concreta

@Getter
@Entity
public class TicketReservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ShowDate showDate;

    private String organizationName;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

    private String address;
    private String notes;       // detalles adicionales

    private Integer seatsRequested;
    private Integer seatsConfirmed;

    private LocalDateTime createdAt;


    public void setId(Long id) {
        this.id = id;
    }

    public void setShowDate(ShowDate showDate) {
        this.showDate = showDate;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSeatsRequested(Integer seatsRequested) {
        this.seatsRequested = seatsRequested;
    }

    public void setSeatsConfirmed(Integer seatsConfirmed) {
        this.seatsConfirmed = seatsConfirmed;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
