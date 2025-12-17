// src/main/java/JustoLamasGroup/Specification/ShowDateSpecification.java
package JustoLamasGroup.Specifications;

import JustoLamasGroup.Entity.ShowDate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ShowDateSpecification {

    public static Specification<ShowDate> hasAddress(String address) {
        return (root, query, builder) -> {
            if (address == null || address.isEmpty()) return null;
            return builder.like(builder.lower(root.get("address")), "%" + address.toLowerCase() + "%");
        };
    }

    public static Specification<ShowDate> hasSchool(String schoolName) {
        return (root, query, builder) -> {
            if (schoolName == null || schoolName.isEmpty()) return null;
            return builder.like(builder.lower(root.get("schoolName")), "%" + schoolName.toLowerCase() + "%");
        };
    }

    public static Specification<ShowDate> hasDate(LocalDate date) {
        return (root, query, builder) -> {
            if (date == null) return null;
            return builder.equal(root.get("date"), date);
        };
    }
}
