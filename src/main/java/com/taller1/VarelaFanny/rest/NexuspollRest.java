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

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.model.Nexuspoll;
import com.taller1.VarelaFanny.services.NexuspollService;

@RestController
@RequestMapping("/backapi")
public class NexuspollRest {
	
	private NexuspollService nexusService;

	public NexuspollRest(NexuspollService nexusService) {
		this.nexusService = nexusService;
	}
	
	@GetMapping("/nexuspolls")
	public Iterable<Nexuspoll> loadNexuspoll(){
		return nexusService.findAll();
	}
	
	@PostMapping("/nexuspolls")
	public ResponseEntity<Nexuspoll> saveNexuspoll(@RequestBody Nexuspoll nexuspoll) {
		return ResponseEntity.ok(nexusService.createNexuspoll(nexuspoll));
	}
	
	@GetMapping("/nexuspolls/{id}")
	public Optional<Nexuspoll> findById(@PathVariable("id") long id) {
		return nexusService.findById(id);
	}
	
	@GetMapping("/nexuspolls/institution")
	public Iterable<Nexuspoll> findByInstitution(@RequestParam(value = "instInstId") long instInstId) {
		return nexusService.findByInstution(instInstId);
	}
	
	@PutMapping("/nexuspolls")
	public ResponseEntity<Nexuspoll> updateNexuspoll(@RequestBody Nexuspoll nexuspoll) {
		return ResponseEntity.ok(nexusService.updateNexuspoll(nexuspoll));
	}
	
	@DeleteMapping("/nexuspolls/")
	public void deleteNexuspoll(@RequestBody Nexuspoll nexuspoll){
		nexusService.delete(nexuspoll);
	}

}
