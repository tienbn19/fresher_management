package com.demo.repository;

import com.demo.model.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FresherRepository extends JpaRepository<Fresher, String>, JpaSpecificationExecutor<Fresher> {
}
