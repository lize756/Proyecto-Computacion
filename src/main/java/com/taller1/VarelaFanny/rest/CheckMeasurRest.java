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
import org.springframework.web.bind.annotation.RestController;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.services.CheckMeasurService;

@RestController
@RequestMapping("/backapi")
public class CheckMeasurRest {

	private CheckMeasurService checkService;
	
	public CheckMeasurRest(CheckMeasurService checkService) {
		this.checkService = checkService;
	}
	
	@GetMapping("/checkMeasurs")
	public Iterable<CheckMeasur> loadCheckMeasur(){
		return checkService.findAll();
	}
	
	@PostMapping("/checkMeasurs")
	public ResponseEntity<CheckMeasur> saveCheckMeasur(@RequestBody CheckMeasur checkMeasur) {
		return ResponseEntity.ok(checkService.createCheckMeasur(checkMeasur));
	}
	
	@GetMapping("/checkMeasurs/{id}")
	public Optional<CheckMeasur> findById(@PathVariable("id") CheckMeasurPK id) {
		return checkService.findById(id);
	}
	
	@PutMapping("/checkMeasurs")
	public ResponseEntity<CheckMeasur> updateCheckMeasur(@RequestBody CheckMeasur checkMeasur) {
		return ResponseEntity.ok(checkService.updateCheckMeasur(checkMeasur));
	}
	
	@DeleteMapping("/checkMeasurs/")
	public void deleteCheckMeasur(@RequestBody CheckMeasur checkMeasur){
		checkService.deleteCheckMeasur(checkMeasur);
	}

}
