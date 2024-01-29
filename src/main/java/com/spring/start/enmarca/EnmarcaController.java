package com.spring.start.enmarca;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.start.actividad.Actividad;
import com.spring.start.actividad.ActividadDAO;
import com.spring.start.plan.PlanDAO;

@Controller
public class EnmarcaController {

	
	@Autowired
	EnmarcaDAO enmarcaDAO;
	@Autowired
	ActividadDAO actividadDAO;
	@Autowired
	PlanDAO planDAO;
	
	@GetMapping("/enmarca")
	public ModelAndView getEnmarca() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("enmarcaciones");
		List<Enmarca> listaEnmarca = (List<Enmarca>)enmarcaDAO.findAll();
		model.addObject("listaEnmarca", listaEnmarca);
		
		return model;
	}
	

		

	@GetMapping("/enmarca/del/{id}")
	public ModelAndView deleteActividad(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/enmarca");

		enmarcaDAO.deleteById(id);		

		return model;

	}

	@GetMapping("/enmarca/add")
	public ModelAndView addPlan() {

		ModelAndView model = new ModelAndView();
		model.setViewName("formEnmarca");
		model.addObject("enmarca", new Enmarca());
		model.addObject("actividades", actividadDAO.findAll());
		model.addObject("planes", planDAO.findAll());

		return model;

	}

	@PostMapping("/enmarca/save")
	public ModelAndView savePlan(@ModelAttribute Enmarca enmarca) {

		ModelAndView model = new ModelAndView();
		
		List<Enmarca> enmarcaciones = (List<Enmarca>) enmarcaDAO.findAll();
		
		
		int i = 0;
		
		do{
			if(!enmarcaciones.get(i).getActividad().equals(enmarca.getActividad())&&!enmarcaciones.get(i).getPlan().equals(enmarca.getPlan())) {
				enmarcaDAO.save(enmarca);
			}
		}
		while(!enmarcaciones.get(i).getActividad().equals(enmarca.getActividad())&&!enmarcaciones.get(i).getPlan().equals(enmarca.getPlan()));
		
		
		model.setViewName("redirect:/enmarca");
		

		return model;
	}
	
	@GetMapping("/enmarca/edit/{id}")
	public ModelAndView editPlan(@PathVariable Long id) {

		ModelAndView model = new ModelAndView();

		Optional<Enmarca> enmarca = enmarcaDAO.findById(id);

		if (enmarca.isPresent()) {
			model.addObject("enmarca", enmarca.get());
			model.setViewName("formEnmarca");

		} else {
			model.setViewName("redirec:/enmarca");
		}

		return model;
	}
}
