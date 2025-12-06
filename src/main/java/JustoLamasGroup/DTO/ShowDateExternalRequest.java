package JustoLamasGroup.DTO;

import java.time.LocalDate;

public record ShowDateExternalRequest(
        LocalDate date,
        String city,
        String state,
        String country,
        String venueName,
        String venueType,
        String timeSlot,
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
                showDate.getTimeSlot(),
                showDate.getStatus()
        );
    }
}