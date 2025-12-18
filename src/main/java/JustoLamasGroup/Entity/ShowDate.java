package JustoLamasGroup.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
public class ShowDate {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    private Tour tour;   // NUEVO: a qué tour pertenece esta fecha
    private LocalDate date;
    private String address;
    private String schoolName;      // teatro / escuela / etc.
    private LocalTime startTime;
    private LocalTime endTime;
    // estado de la fecha: ABIERTA, COMPLETA, CANCELADA
    private String status;
    private boolean deleted = false;

    @OneToMany(mappedBy = "showDate", cascade = CascadeType.REMOVE)
    private List<TicketReservation> reservations;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}