package com.taller1.VarelaFanny.rest;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.VarelaFanny.model.Nexusquestion;
import com.taller1.VarelaFanny.services.NexusquestionService;

@RestController
@RequestMapping("/backapi")
public class NexusquestionRest {
	
	private NexusquestionService nexusService;

	public NexusquestionRest(NexusquestionService nexusService) {
		this.nexusService = nexusService;
	}
	
	@GetMapping("/nexusquestions")
	public Iterable<Nexusquestion> loadNexusquestion(){
		return nexusService.findAll();
	}
	
	@PostMapping("/nexusquestions")
	public ResponseEntity<Nexusquestion> saveNexusquestion(@RequestBody Nexusquestion nexusquestion) {
		return ResponseEntity.ok(nexusService.createNexusquestion(nexusquestion));
	}
	
	@GetMapping("/nexusquestions/{id}")
	public Optional<Nexusquestion> findById(@PathVariable("id") long id) {
		return nexusService.findById(id);
	}
	
	@GetMapping("/nexusquestions/poll")
	public Iterable<Nexusquestion> findByPoll(@RequestParam(value = "nexpollId") long nexpollId) {
		return nexusService.findByPoll(nexpollId);
	}
	
	@PutMapping("/nexusquestions")
	public ResponseEntity<Nexusquestion> updateNexusquestion(@RequestBody Nexusquestion nexusquestion) {
		return ResponseEntity.ok(nexusService.updateNexusquestion(nexusquestion));
	}
	
	@DeleteMapping("/nexusquestions/")
	public void deleteNexusquestion(@RequestBody Nexusquestion nexusquestion){
		nexusService.delete(nexusquestion);
	}

}
