package com.spring.start.plan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

import com.spring.start.actividad.ActividadDAO;
import com.spring.start.curso.Curso;
import com.spring.start.curso.CursoDAO;
import com.spring.start.enmarca.Enmarca;
import com.spring.start.enmarca.EnmarcaDAO;
import com.spring.start.tutor.Tutor;
import com.spring.start.tutor.TutorDAO;

import jakarta.validation.Valid;

@Controller
public class PlanController {

	@Autowired
	PlanDAO planDAO;
	@Autowired
	TutorDAO tutorDAO;
	@Autowired
	CursoDAO cursoDAO;
	@Autowired
	ActividadDAO actividadDAO;
	

	@GetMapping("/plan")
	public ModelAndView getPlanes() {

		ModelAndView model = new ModelAndView();
		model.setViewName("planes");
		List<Plan> planes = (List<Plan>) planDAO.findAll();
		model.addObject("plan", new Plan());
		model.addObject("cursos", cursoDAO.findAll());
		model.addObject("tutores", tutorDAO.getTutoresNoEnlazados());
		model.addObject("planes", planes);

		return model;
	}

	@GetMapping("/plan/{id}")
	public ModelAndView getPlan(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		
		
		model.setViewName("plan");
		Optional<Plan> plan = planDAO.findById(id);
		if (plan.isPresent()) {
			model.addObject("plan", plan.get());
			model.addObject("enmarca", new Enmarca());
			model.addObject("actividades", actividadDAO.findNotLinkPlan(plan.get().getId()));
		}

		
		return model;
	}

	@GetMapping("/plan/del/{id}")
	public ModelAndView deletePlan(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/plan");

		Optional<Plan> plan = planDAO.findById(id);

		if (plan.isPresent()) {

			if (plan.get().getIdCurso() != null) {
				Optional<Curso> curso = cursoDAO.findById(planDAO.findById(id).get().getIdCurso().getId());

				if (curso.isPresent()) {
					List<Plan> planes = curso.get().getPlanes();
					planes.remove(plan.get());
					curso.get().setPlanes(planes);

					cursoDAO.save(curso.get());
				}

			}

			planDAO.deleteById(id);
		}

		return model;

	}

	@GetMapping("/plan/add")
	public ModelAndView addPlan() {

		ModelAndView model = new ModelAndView();
		model.setViewName("formPlan");
		model.addObject("plan", new Plan());
		model.addObject("cursos", cursoDAO.findAll());
		model.addObject("tutores", tutorDAO.getTutoresNoEnlazados());

		return model;

	}

	@PostMapping("/plan/save")
	public ModelAndView savePlan(@ModelAttribute @Valid Plan plan,
			 BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		if(bindingResult.hasErrors()) {
			
			model.setViewName("formPlan");
			plan.setTutor(null);
			model.addObject("plan", plan);
			model.addObject("cursos", cursoDAO.findAll());
			model.addObject("tutores", tutorDAO.getTutoresNoEnlazados());
			return model;
		}
		
		
		Tutor tutor = plan.getTutor();
		if (tutor != null) {
			tutor.setIdPlan(plan);
			tutorDAO.save(tutor);
		}
		else {
			plan.setTutor(null);
			planDAO.save(plan);
		}

		model.addObject("planNuevo", plan);
		model.setViewName("redirect:/plan/nuevo/" + plan.getId());
		

		return model;
	}

	@GetMapping("plan/nuevo/{id}")
	public ModelAndView popupNuevoPlan(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		List<Plan> planes = (List<Plan>) planDAO.findAll();

		Plan planNuevo = planDAO.findById(id).get();

	
			model.addObject("plan", new Plan());
			model.addObject("tutores", tutorDAO.getTutoresNoEnlazados());
			model.addObject("planes", planes);
			model.addObject("planNuevo", planNuevo);
			model.addObject("cursos", cursoDAO.findAll());
			model.setViewName("planes");
		
		

		

		return model;
	}

	@GetMapping("/plan/edit/{id}")
	public ModelAndView editPlan(@PathVariable Long id) {

		ModelAndView model = new ModelAndView();

		Optional<Plan> planazo = planDAO.findById(id);

		if (planazo.isPresent()) {
			model.addObject("plan", planazo.get());
			model.addObject("cursos", cursoDAO.findAll());
			model.addObject("tutores", tutorDAO.getTutoresNoEnlazados());
			model.setViewName("formPlan");

		} else {
			model.setViewName("redirect:/plan");
		}

		return model;

	}

	@GetMapping("/plan/tutor/del/{idPlan}")
	public ModelAndView deleteTutor(@PathVariable Long idPlan) {

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/plan");

		Optional<Plan> plan = planDAO.findById(idPlan);
		if (planDAO.findById(idPlan).isPresent()) {
			Tutor tutor = tutorDAO.findById(plan.get().getTutor().getId()).get();
			tutor.setIdPlan(null);
			tutorDAO.save(tutor);
		}

		return model;

	}
}
