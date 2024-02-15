package com.spring.start.actividad;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.start.enmarca.Enmarca;

public interface ActividadDAO extends CrudRepository<Actividad, Long> {

	
	@Query(value="SELECT * from actividad a where a.id NOT IN (SELECT e.actividad_id FROM enmarca e where e.plan_id = :id)",nativeQuery = true)
	List<Actividad> findNotLinkPlan(long id);
	
}
