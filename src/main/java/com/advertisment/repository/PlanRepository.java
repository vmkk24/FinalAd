package com.advertisment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advertisment.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{

}
