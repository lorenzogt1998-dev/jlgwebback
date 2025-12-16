package JustoLamasGroup.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowDateExternalRequest(
        LocalDate date,
        String city,
        String state,
        String country,
        String venueName,
        String venueType,
        LocalTime startTime,
        LocalTime endTime,
        String status
) {
    public static ShowDateExternalRequest fromEntity(JustoLamasGroup.Entity.ShowDate showDate) {
        return new ShowDateExternalRequest(
                showDate.getDate(),
                showDate.getCity(),
                showDate.getState(),
                showDate.getCountry(),
                showDate.getVenueName(),
                showDate.getVenueType(),
                showDate.getStartTime(),
                showDate.getEndTime(),
                showDate.getStatus()
        );
    }
}