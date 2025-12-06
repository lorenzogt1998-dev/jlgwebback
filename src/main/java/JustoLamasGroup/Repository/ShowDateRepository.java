package JustoLamasGroup.Repository;

import JustoLamasGroup.Entity.ShowDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowDateRepository extends JpaRepository<ShowDate, Long> {

    List<ShowDate> findByStatus(String status);
}
