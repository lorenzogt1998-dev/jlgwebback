// src/main/java/JustoLamasGroup/DTO/ReserveTicketLeadRequest.java
package JustoLamasGroup.DTO;

public record ReserveTicketLeadRequest(
        Long showDateId,     // <-- este ID viene del select del frontend
        String tourDate,        // value del <select>
        String contactName,
        String email,
        String phone,
        String school,
        String schoolAddress,
        Integer students,
        String notes
) {}
