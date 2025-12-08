package JustoLamasGroup.Repository;

import JustoLamasGroup.Entity.TicketReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {

    // Buscar reservas por la fecha del show (ShowDate.date)
    List<TicketReservation> findByShowDate_Date(LocalDate date);

}
