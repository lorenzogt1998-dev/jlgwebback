// src/main/java/JustoLamasGroup/Repository/TourRepository.java
package JustoLamasGroup.Repository;

import JustoLamasGroup.Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
