package com.taller1.VarelaFanny.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller1.VarelaFanny.services.CheckMeasurService;
import com.taller1.VarelaFanny.services.MeasurementService;
import com.taller1.VarelaFanny.services.PhysicalcheckupService;

import com.taller1.VarelaFanny.delegate.ICheckMeasurDelegate;
import com.taller1.VarelaFanny.delegate.IMeasurementDelegate;
import com.taller1.VarelaFanny.delegate.IPhysicalcheckupDelegate;
import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;


@Controller
public class CheckMeasurController {
	
	private CheckMeasurService chMeasurService;
	private MeasurementService measurService;
	private PhysicalcheckupService physService;
	private ICheckMeasurDelegate checkDelegate;
	private IPhysicalcheckupDelegate physDelegate;
	private IMeasurementDelegate measurDelegate;	
	
	@Autowired
	public CheckMeasurController(ICheckMeasurDelegate checkDelegate, IPhysicalcheckupDelegate physDelegate, IMeasurementDelegate measurDelegate,CheckMeasurService chMeasurService, MeasurementService measurService, PhysicalcheckupService physService) {
		this.checkDelegate = checkDelegate;
		this.physDelegate = physDelegate;
		this.measurDelegate = measurDelegate;
		this.chMeasurService = chMeasurService;
		this.measurService = measurService;
		this.physService = physService;
		
	}

	@RequestMapping(value = "/checkMeasurs", method = RequestMethod.GET)
	public String CheckMeasur(Model model) {
		model.addAttribute("checkMeasurs", checkDelegate.GET_CheckMeasurs());
		return "checkMeasurs/index";
	}
	
	@GetMapping("/checkMeasurs/add-checkMeasurs")
	public String addCheckMeasur(Model model) {
		
		model.addAttribute("check", new CheckMeasur());
		model.addAttribute("measurements", measurDelegate.GET_Measurements());
		model.addAttribute("physicalcheckups", physDelegate.GET_Physicalcheckups());	
		return "checkMeasurs/add-checkMeasurs";
	}
	
	@PostMapping("/checkMeasurs/add-checkMeasurs")
	public String saveCheckMeasur(@RequestParam(value = "action", required = true) String action,  
			@Validated CheckMeasur check, BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/checkMeasurs";
		}
		if(bindingResult.hasErrors()) {
			return "checkMeasurs/add-checkMeasurs";
		}
		
		if(!action.equals("Cancel")) {
			try {
					
				checkDelegate.POST_CheckMeasur(check);
				
				if(measurDelegate.GET_Measurement(check.getMeasurement().getMeasId()) != null 
						&& physDelegate.GET_Physicalcheckup(check.getPhysicalcheckup().getPhycheId()) != null) {			
					
					check.setId(new CheckMeasurPK(check.getMeasurement().getMeasId(), check.getPhysicalcheckup().getPhycheId()));
					model.addAttribute("id", check.getId());
					model.addAttribute("measvalue", check.getMeasvalue());
					model.addAttribute("measurement", check.getMeasurement());
					model.addAttribute("physicalcheckup", check.getPhysicalcheckup());	
				}				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "redirect:/error";
			}
		}
		return "redirect:/checkMeasurs";
	}
	
	@GetMapping("/checkMeasurs/edit/{measMeasId}/{phychePhycheId}")
	public String showUpdateForm(@PathVariable("measMeasId") String measMeasId, @PathVariable("phychePhycheId") long phychePhycheId, Model model) {
		
		long idmeas = Long.parseLong(measMeasId);
		
		System.out.println("idddddd: "+idmeas);
		Physicalcheckup phys = physDelegate.GET_Physicalcheckup(phychePhycheId);
		Measurement meas = measurDelegate.GET_Measurement(idmeas);
		CheckMeasur check = new CheckMeasur();
		if(meas != null && phys != null) {
			check = checkDelegate.GET_CheckMeasur(idmeas,phychePhycheId);
		}
		
		if (check == null)
			throw new IllegalArgumentException("Invalid user Id:" + idmeas);
		
		model.addAttribute("check", check);
		model.addAttribute("measurements", measurDelegate.GET_Measurements());
		model.addAttribute("physicalcheckups", physDelegate.GET_Physicalcheckups());	
		
		
		return "checkMeasurs/update-checkMeasurs";
	}
	
	@PostMapping("/checkMeasurs/edit/{measMeasId}/{phychePhycheId}")
	public String updateVisit(@PathVariable("measMeasId") String  measMeasId, @PathVariable("phychePhycheId") long phychePhycheId,
			@RequestParam(value = "action", required = true) String action, @Validated CheckMeasur check, BindingResult bindingResult, Model model) {
				
		
		System.out.println("id: "+ measMeasId);
		
		if (action != null && !action.equals("Cancel")) {
			
			if (bindingResult.hasErrors()) {
				
				return "checkMeasurs/update-checkMeasurs";
			} else {
				try {
					System.out.println("id check " + check.getId().getMeasMeasId());
					checkDelegate.PUT_CheckMeasur(check);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/checkMeasurs";
	}
	

}
