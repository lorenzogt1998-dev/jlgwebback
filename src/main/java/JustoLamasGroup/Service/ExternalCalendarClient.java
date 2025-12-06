package JustoLamasGroup.Service;

import JustoLamasGroup.Entity.ShowDate;
import JustoLamasGroup.Entity.TicketReservation;

public interface ExternalCalendarClient {

    void createShowDate(ShowDate showDate);

    void createReservation(TicketReservation reservation);
}