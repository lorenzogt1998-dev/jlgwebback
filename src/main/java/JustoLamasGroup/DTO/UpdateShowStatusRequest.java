package JustoLamasGroup.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateShowStatusRequest(
        String schoolName,
        String address,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String status) {
}