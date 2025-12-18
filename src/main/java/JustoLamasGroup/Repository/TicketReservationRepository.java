package JustoLamasGroup.Repository;

import JustoLamasGroup.Entity.TicketReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {

    // Buscar reservas por la fecha del show (ShowDate.date)
    List<TicketReservation> findByShowDate_Date(LocalDate date);

    // Contar reservas asociadas a un show específico (CLAVE para el delete)
    long countByShowDate_Id(Long showDateId);

    // Listar todas las reservas asociadas a un show específico
    List<TicketReservation> findByShowDate_Id(Long showDateId);
}
