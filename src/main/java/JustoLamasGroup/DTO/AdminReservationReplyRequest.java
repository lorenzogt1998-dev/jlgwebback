package JustoLamasGroup.DTO;

public record AdminReservationReplyRequest(
        Long reservationId,
        Integer seatsConfirmed,
        String message
) {}
