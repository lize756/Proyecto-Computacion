package com.taller1.VarelaFanny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller1.VarelaFanny.delegate.IMeasurementDelegate;
import com.taller1.VarelaFanny.model.Institution;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.services.InstitutionService;
import com.taller1.VarelaFanny.services.MeasurementService;


@Controller
@RequestMapping("/frontapi")
public class MeasurementController {
	

	private MeasurementService measurService;
	private IMeasurementDelegate measurDelegate;
	private InstitutionService institutionService;
	
	@Autowired
	public MeasurementController(IMeasurementDelegate measurDelegate, MeasurementService measurService, InstitutionService institutionService) {
		
		this.measurDelegate = measurDelegate;
		this.measurService = measurService;
		this.institutionService = institutionService;
	}
	
	@GetMapping("/measurements")
	public String measurement(Model model) {
		model.addAttribute("measurements", measurDelegate.GET_Measurements());
		return "measurements/index";
	}

	
	@GetMapping("/measurements/add-measurements")
	public String addMeasurement(Model model) {
		model.addAttribute("measur", new Measurement());
		model.addAttribute("institutions", institutionService.findAll());
		
		return "measurements/add-measurements";
	}

	
	@PostMapping("/measurements/add-measurements")
	public String saveMeasurement(@RequestParam(value = "action", required = true) String action, @Validated Measurement measur,
			BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/frontapi/measurements";
		}
				
		if(bindingResult.hasErrors()) {
			return "measurements/add-measurements";
		}
			
		else if(!action.equals("Cancel")) {
			try {
				
				 measurDelegate.POST_Measurement(measur);	
				
				if(measur != null && institutionService.findById(measur.getInstitution().getInstId())!= null){
					model.addAttribute("measId", measur.getMeasId());
					model.addAttribute("measName", measur.getMeasName());		
				    model.addAttribute("measDescription", measur.getMeasDescription());
					model.addAttribute("measUnit", measur.getMeasUnit());
					model.addAttribute("measMaxthreshold", measur.getMeasMaxthreshold());
					model.addAttribute("measMinthreshold", measur.getMeasMinthreshold());
					model.addAttribute("institution", measur.getInstitution());
					
				}
				
			} catch (Exception e) {
				
				return "redirect:/error";
			}
		}
		return "redirect:/frontapi/measurements";
	}
	
	
	@GetMapping("/measurements/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		
		Measurement measur = measurDelegate.GET_Measurement(id);
		//Optional<Measurement> measur = measurService.findById(id);
		if (measur == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("measur", measur);
		model.addAttribute("institutions", institutionService.findAll());
		
		return "measurements/update-measurements";
	}
	
	@PostMapping("/measurements/edit/{id}")
	public String updateMeasurement(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, 
			@Validated Measurement measur, BindingResult bindingResult, Model model) {
		
		System.out.println("id: "+ id);
		if (action != null && !action.equals("Cancel")) {
			
			if (bindingResult.hasErrors()) {
								
				return "measurements/update-measurements";
			} else {
				try {
					System.out.println("id measur " + measur.getMeasId());
					measurDelegate.PUT_Measurement(measur);
					//measurService.updateMeasurement(measur);	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/frontapi/measurements";
	}
	
	@GetMapping("/measurements/del/{id}")
    public String deleteMeasurement(@PathVariable("id") long id) {
        Measurement measur = measurDelegate.GET_Measurement(id);
        Institution inst = measur.getInstitution();
        institutionService.deleteInstitution(inst.getInstId());
        measurDelegate.DELETE_Measurement(measur);
        return "redirect:/frontapi/measurements/";
    }

}
