package JustoLamasGroup.DTO;

import java.time.LocalDateTime;

import JustoLamasGroup.Entity.TicketReservation;

public record ReservationExternalRequest(
        Long showDateId,
        String organizationName,
        String contactName,
        String contactEmail,
        String contactPhone,
        Integer seatsRequested,
        Integer seatsConfirmed,
        LocalDateTime createdAt
) {
    public static ReservationExternalRequest fromEntity(TicketReservation reservation) {
        return new ReservationExternalRequest(
                reservation.getShowDate().getId(),
                reservation.getOrganizationName(),
                reservation.getContactName(),
                reservation.getContactEmail(),
                reservation.getContactPhone(),
                reservation.getSeatsRequested(),
                reservation.getSeatsConfirmed(),
                reservation.getCreatedAt()
        );
    }
}