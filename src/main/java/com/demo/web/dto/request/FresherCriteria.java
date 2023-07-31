package com.demo.web.dto.request;

import com.demo.model.Fresher;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class FresherCriteria {
    private String fullName;
    private String program;
    private String email;

    public Specification<Fresher> toSpecification() {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(fullName)) {
                predicates.add(criteriaBuilder.like(root.get("profile").get("fullName"), "%" + fullName + "%"));
            }
            if (Objects.nonNull(program)) {
                predicates.add(criteriaBuilder.like(root.get("program"), "%" + program + "%"));
            }
            if (Objects.nonNull(email)) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }
}
