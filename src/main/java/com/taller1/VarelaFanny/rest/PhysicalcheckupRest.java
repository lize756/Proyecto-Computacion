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

import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.services.PhysicalcheckupService;

@RestController
@RequestMapping("/backapi")
public class PhysicalcheckupRest {
	
	private PhysicalcheckupService physService;

	public PhysicalcheckupRest(PhysicalcheckupService physService) {
		this.physService = physService;
	}
	
	@GetMapping("/physicalcheckups")
	public Iterable<Physicalcheckup> loadPhysicalcheckup(){
		return physService.findAll();
	}
	
	@PostMapping("/physicalcheckups")
	public ResponseEntity<Physicalcheckup> savePhysicalcheckup(@RequestBody Physicalcheckup phys) {
		return ResponseEntity.ok(physService.createPhysicalcheckupService(phys));
	}
	
	@GetMapping("/physicalcheckups/{id}")
	public Physicalcheckup findById(@PathVariable("id") long id) {
		return physService.findById(id).get();
	}
	
	@PutMapping("/physicalcheckups")
	public ResponseEntity<Physicalcheckup> updatePhysicalcheckup(@RequestBody Physicalcheckup phys) {
		return ResponseEntity.ok(physService.updatePhysicalcheckupService(phys));
	}
	
	@DeleteMapping("/physicalcheckups")
	public void deletePhysicalcheckup(@RequestBody Physicalcheckup phys){
		physService.delete(phys);
	}

	
	
}
