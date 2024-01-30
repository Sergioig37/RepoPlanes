package com.spring.start.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.start.curso.Curso;
import com.spring.start.enmarca.Enmarca;
import com.spring.start.tutor.Tutor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;

	@JoinColumn(name = "FK_CURSO")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private Curso idCurso;

	@OneToOne(mappedBy = "idPlan")
	private Tutor tutor;
	
	
	@OneToMany(mappedBy="plan", cascade=CascadeType.ALL)
	private Set<Enmarca> enmarcados;
	

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Curso getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Curso idCurso) {
		this.idCurso = idCurso;
	}

	@Override
	public String toString() {
		// Depending on your requirements, you can adjust the logic here
		if (tutor != null) {
			return "Plan [id=" + id + ", nombre=" + nombre + ", idCurso=" + idCurso + " tutor=" + tutor + "]";
		} else {
			return "Plan [id=" + id + ", nombre=" + nombre + ", idCurso=" + idCurso + "]";
		}
	}

}
