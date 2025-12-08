package JustoLamasGroup.Repository;

import JustoLamasGroup.Entity.ShowDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;

public interface ShowDateRepository extends JpaRepository<ShowDate, Long>, JpaSpecificationExecutor<ShowDate> {

    List<ShowDate> findByStatus(String status);
}
