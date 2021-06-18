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

import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.services.MeasurementService;

@RestController
@RequestMapping("/backapi")
public class MeasurementRest {
	
	private MeasurementService measurService;
	
	public MeasurementRest(MeasurementService measurService) {
		this.measurService = measurService;		
	}
	
	@GetMapping("/measurements")
	public Iterable<Measurement> loadMeasur(){
		return measurService.findAll();
	}
	
	@PostMapping("/measurements")
	public ResponseEntity<Measurement> saveMeasur(@RequestBody Measurement measur) {
		return ResponseEntity.ok(measurService.createMeasurement(measur));
	}
	
	@GetMapping("/measurements/{id}")
	public Measurement findById(@PathVariable("id") long id) {
		return measurService.findById(id).get();
	}
	
	@PutMapping("/measurements/")
	public ResponseEntity<Measurement> updateMeasur(@RequestBody Measurement measur) {
		return ResponseEntity.ok(measurService.updateMeasurement(measur));
	}
	
	@DeleteMapping("/measurements/")
	public void deleteMeasur(@RequestBody Measurement measur){
		measurService.deleteMeasurement(measur);
	}
	

}
