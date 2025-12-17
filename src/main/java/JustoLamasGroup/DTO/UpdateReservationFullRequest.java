package JustoLamasGroup.DTO;

public record UpdateReservationFullRequest(
        String organizationName,
        String contactName,
        String contactEmail,
        String contactPhone,
        String address,
        String notes,
        Integer seatsRequested,
        Integer seatsConfirmed
) {}