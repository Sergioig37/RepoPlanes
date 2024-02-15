package com.spring.start.actividad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.start.curso.Curso;
import com.spring.start.enmarca.Enmarca;
import com.spring.start.enmarca.EnmarcaDAO;
import com.spring.start.plan.Plan;
import com.spring.start.tutor.Tutor;

@Controller
public class ActividadController {

	@Autowired
	ActividadDAO actividadDAO;
	@Autowired
	EnmarcaDAO enmarcaDAO;
	
	//esto queda comentado hasta que acabe lo de la tabla enmarca
	@GetMapping("/actividad")
	public ModelAndView getActividades() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("actividades");
		List<Actividad> actividades = (List<Actividad>)actividadDAO.findAll();
		
		model.addObject("actividades", actividades);
		
		return model;
	}
	
	@GetMapping("/actividad/{id}")
	public ModelAndView getActividad(@PathVariable long id) {
		
		
		ModelAndView model = new ModelAndView();
		model.setViewName("actividad");
		Optional<Actividad> actividad = actividadDAO.findById(id);
		if(actividad.isPresent()) {
			model.addObject("actividad", actividad.get());
		}
		
		
		return model;
	}
	
	@GetMapping("/actividad/del/{id}")
	public ModelAndView deleteActividad(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/actividad");

		Optional<Actividad> actividad = actividadDAO.findById(id);
		
		List<Enmarca> relacionesEnmarca = (List<Enmarca>) enmarcaDAO.findAll();
		
		for(Enmarca enmarca: relacionesEnmarca) {
			if(enmarca.getActividad().getId()==actividad.get().getId()) {
				enmarcaDAO.deleteById(enmarca.getId());
			}
		}
		
		actividadDAO.deleteById(id);
		

		return model;

	}

	@GetMapping("/actividad/add")
	public ModelAndView addPlan() {

		ModelAndView model = new ModelAndView();
		model.setViewName("formActividad");
		model.addObject("actividad", new Actividad());

		return model;

	}

	@PostMapping("/actividad/save")
	public ModelAndView savePlan(@ModelAttribute Actividad actividad) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/actividad");
		actividadDAO.save(actividad);

		return model;
	}
	
	@GetMapping("/actividad/edit/{id}")
	public ModelAndView editPlan(@PathVariable Long id) {

		ModelAndView model = new ModelAndView();

		Optional<Actividad> actividad = actividadDAO.findById(id);

		if (actividad.isPresent()) {
			model.addObject("actividad", actividad.get());
			model.setViewName("formActividad");

		} else {
			model.setViewName("redirec:/actividad");
		}

		return model;
	}

	
}
