package com.spring.start.plan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.start.tutor.Tutor;

public interface PlanDAO extends CrudRepository<Plan, Long>{

	

}
