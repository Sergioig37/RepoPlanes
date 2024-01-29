package com.spring.start.enmarca;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.start.actividad.Actividad;
import com.spring.start.plan.Plan;
import com.spring.start.tutor.Tutor;

public interface EnmarcaDAO extends CrudRepository<Enmarca, Long>{
 	
	
	@Query(value = "SELECT * FROM Enmarca WHERE plan_id = :plan AND actividad_id = :actividad", nativeQuery = true)
	Optional<Enmarca> getEnmarcaIgual(@Param("plan") Long plan, @Param("actividad") Long actividad);

}
