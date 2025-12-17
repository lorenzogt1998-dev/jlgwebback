package JustoLamasGroup.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowDateExternalRequest(
        LocalDate date,
        String address,
        String schoolName,
        LocalTime startTime,
        LocalTime endTime,
        String status
) {
    public static ShowDateExternalRequest fromEntity(JustoLamasGroup.Entity.ShowDate showDate) {
        return new ShowDateExternalRequest(
                showDate.getDate(),
                showDate.getAddress(),
                showDate.getSchoolName(),
                showDate.getStartTime(),
                showDate.getEndTime(),
                showDate.getStatus()
        );
    }
}