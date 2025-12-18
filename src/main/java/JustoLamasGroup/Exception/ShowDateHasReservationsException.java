package JustoLamasGroup.Exception;

public class ShowDateHasReservationsException extends RuntimeException {
    public ShowDateHasReservationsException(Long showDateId) {
        super("ShowDate con ID " + showDateId + " tiene reservas asociadas.");
    }
}