package JustoLamasGroup.DTO;

public record SetDateLeadRequest(
        String contactName,
        String role,
        String email,
        String cellphone,
        String schoolphone,
        String school,
        String address,
        Integer capacity, // Estimated Capacity (students)
        String preferredDate,
        String preferredTime,
        String notes
) {}