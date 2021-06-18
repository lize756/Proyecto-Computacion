package com.taller1.VarelaFanny.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.services.VisitService;

@RestController
@RequestMapping("/backapi")
public class VisitRest {

	private VisitService visitService;
	
	public VisitRest(VisitService visitService) {
		this.visitService = visitService;
	}
	
	@GetMapping("/visits")
	public Iterable<Visit> loadVisit(){
		return visitService.findAll();
	}
	
	@PostMapping("/visits")
	public ResponseEntity<Visit> saveVisit(@RequestBody Visit visit) {
		return ResponseEntity.ok(visitService.createVisit(visit));
	}
	
	@GetMapping("/visits/{id}")
	public Visit findById(@PathVariable("id") long id) {
		return visitService.findById(id).get();
	}
	
	@PutMapping("/visits")
	public ResponseEntity<Visit> updateVisit(@RequestBody Visit visit) {
		return ResponseEntity.ok(visitService.updateVisit(visit));
	}
	
	@DeleteMapping("/visits")
	public void deleteVisit(@RequestBody Visit visit){
		visitService.deleteVisit(visit);
	}
	
}
