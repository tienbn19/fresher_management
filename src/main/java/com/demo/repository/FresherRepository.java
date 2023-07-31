package com.demo.repository;

import com.demo.model.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FresherRepository extends JpaRepository<Fresher, String>, JpaSpecificationExecutor<Fresher> {
    List<Fresher> findByCenter_Id(String centerId);
}
