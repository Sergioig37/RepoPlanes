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

@Controller
public class EnmarcaController {

	
	@Autowired
	EnmarcaDAO enmarcaDAO;
	
	@GetMapping("/enmarca")
	public ModelAndView getEnmarca() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("enmarcaciones");
		List<Enmarca> listaEnmarca = (List<Enmarca>)enmarcaDAO.findAll();
		for(Enmarca enmarca: listaEnmarca) {
			System.out.println(enmarca);
		}
		model.addObject("listaEnmarca", listaEnmarca);
		
		return model;
	}
	
//	@GetMapping("/actividad/{id}")
//	public ModelAndView getActividad(@PathVariable long id) {
//		
//		
//		ModelAndView model = new ModelAndView();
//		model.setViewName("actividad");
//		Optional<Actividad> actividad = actividadDAO.findById(id);
//		if(actividad.isPresent()) {
//			model.addObject("actividad", actividad.get());
//		}
//		
//		
//		return model;
//	}
//	
//	@GetMapping("/actividad/del/{id}")
//	public ModelAndView deleteActividad(@PathVariable long id) {
//
//		ModelAndView model = new ModelAndView();
//
//		model.setViewName("redirect:/actividad");
//
//		Optional<Actividad> actividad = actividadDAO.findById(id);
//		
//		List<Enmarca> relacionesEnmarca = (List<Enmarca>) enmarcaDAO.findAll();
//		
//		for(Enmarca enmarca: relacionesEnmarca) {
//			if(enmarca.getActividad().getId()==actividad.get().getId()) {
//				enmarcaDAO.deleteById(enmarca.getId());
//			}
//		}
//		
//		actividadDAO.deleteById(id);
//		
//
//		return model;
//
//	}
//
//	@GetMapping("/actividad/add")
//	public ModelAndView addPlan() {
//
//		ModelAndView model = new ModelAndView();
//		model.setViewName("formActividad");
//		model.addObject("actividad", new Actividad());
//
//		return model;
//
//	}
//
//	@PostMapping("/actividad/save")
//	public ModelAndView savePlan(@ModelAttribute Actividad actividad) {
//
//		ModelAndView model = new ModelAndView();
//
//		model.setViewName("redirect:/actividad");
//		actividadDAO.save(actividad);
//
//		return model;
//	}
//	
//	@GetMapping("/actividad/edit/{id}")
//	public ModelAndView editPlan(@PathVariable Long id) {
//
//		ModelAndView model = new ModelAndView();
//
//		Optional<Actividad> actividad = actividadDAO.findById(id);
//
//		if (actividad.isPresent()) {
//			model.addObject("actividad", actividad.get());
//			model.setViewName("formActividad");
//
//		} else {
//			model.setViewName("redirec:/actividad");
//		}
//
//		return model;
//	}
}
