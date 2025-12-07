package JustoLamasGroup.DTO;

public record UpdateReservationFullRequest(
        String organizationName,
        String contactName,
        String contactEmail,
        String contactPhone,
        String city,
        String state,
        String grades,
        String notes,
        Integer seatsRequested,
        Integer seatsConfirmed
) {}