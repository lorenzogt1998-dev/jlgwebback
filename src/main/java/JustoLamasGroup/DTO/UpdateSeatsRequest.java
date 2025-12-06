package JustoLamasGroup.DTO;

public record UpdateSeatsRequest(
        Integer seatsRequested,
        Integer seatsConfirmed
) {}