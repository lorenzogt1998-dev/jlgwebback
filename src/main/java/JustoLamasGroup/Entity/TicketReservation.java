package JustoLamasGroup.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

// Reserva de tickets para una fecha concreta

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

    private String city;
    private String state;
    private String grades;      // niveles de grado enviados por el front
    private String notes;       // detalles adicionales

    private Integer seatsRequested;
    private Integer seatsConfirmed;

    private LocalDateTime createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShowDate getShowDate() {
        return showDate;
    }

    public void setShowDate(ShowDate showDate) {
        this.showDate = showDate;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getSeatsRequested() {
        return seatsRequested;
    }

    public void setSeatsRequested(Integer seatsRequested) {
        this.seatsRequested = seatsRequested;
    }

    public Integer getSeatsConfirmed() {
        return seatsConfirmed;
    }

    public void setSeatsConfirmed(Integer seatsConfirmed) {
        this.seatsConfirmed = seatsConfirmed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
