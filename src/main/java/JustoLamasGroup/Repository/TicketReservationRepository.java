package JustoLamasGroup.Repository;


import JustoLamasGroup.Entity.TicketReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {

    List<TicketReservation> findByShowDateId(Long showDateId);
}
