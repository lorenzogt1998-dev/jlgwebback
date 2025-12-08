// src/main/java/JustoLamasGroup/Specification/ShowDateSpecification.java
package JustoLamasGroup.Specifications;

import JustoLamasGroup.Entity.ShowDate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ShowDateSpecification {

    public static Specification<ShowDate> hasCity(String city) {
        return (root, query, builder) -> {
            if (city == null || city.isEmpty()) return null;
            return builder.like(builder.lower(root.get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    public static Specification<ShowDate> hasVenue(String venueName) {
        return (root, query, builder) -> {
            if (venueName == null || venueName.isEmpty()) return null;
            return builder.like(builder.lower(root.get("venueName")), "%" + venueName.toLowerCase() + "%");
        };
    }

    public static Specification<ShowDate> hasDate(LocalDate date) {
        return (root, query, builder) -> {
            if (date == null) return null;
            return builder.equal(root.get("date"), date);
        };
    }
}
