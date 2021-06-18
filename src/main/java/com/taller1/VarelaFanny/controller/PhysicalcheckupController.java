package com.taller1.VarelaFanny.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller1.VarelaFanny.delegate.IPhysicalcheckupDelegate;
import com.taller1.VarelaFanny.delegate.IVisitDelegate;
import com.taller1.VarelaFanny.delegate.PhysicalcheckupDelegate;
import com.taller1.VarelaFanny.delegate.VisitDelegate;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.services.PersonService;
import com.taller1.VarelaFanny.services.PhysicalcheckupService;
import com.taller1.VarelaFanny.services.PhysicalcheckupServiceImpl;
import com.taller1.VarelaFanny.services.VisitService;

@Controller
@RequestMapping("/frontapi")
public class PhysicalcheckupController {

	private IPhysicalcheckupDelegate physDelegate;
	private PhysicalcheckupService physicalcheckupService;
	private PersonService personService;
	private VisitService visitService;
	private IVisitDelegate vistDelegate;
	
	@Autowired
	public PhysicalcheckupController(IVisitDelegate vistDelegate, IPhysicalcheckupDelegate physDelegate, PhysicalcheckupService physicalcheckupService, PersonService personService, VisitService visitService) {
		
		this.physicalcheckupService = physicalcheckupService;
		this.vistDelegate = vistDelegate;
		this.physDelegate = physDelegate;
		this.personService = personService;
		this.visitService = visitService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@RequestMapping(value = "/physicalcheckups", method = RequestMethod.GET)
	public String physicalCheckups(Model model) {
		model.addAttribute("physicalcheckups", physDelegate.GET_Physicalcheckups());
		
		return "physicalcheckups/index";
	}
	
	@GetMapping("/physicalcheckups/add-physicalcheckup")
	public String addPhysicalCheckup(Model model) {
		
		model.addAttribute("physic", new Physicalcheckup());
		model.addAttribute("persons", personService.findAll());
		model.addAttribute("visits", vistDelegate.GET_Visits());
		return "physicalcheckups/add-physicalcheckup";
	}
	
	@PostMapping("/physicalcheckups/add-physicalcheckup")
	public String savePhysicalCheckup(@RequestParam(value = "action", required = true) String action, @RequestParam("phycheDate") 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date phycheDate, @Validated Physicalcheckup physic,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "physicalcheckups/add-physicalcheckup";
		}
		
		if(!action.equals("Cancel")) {
			try {
				//Physicalcheckup pc = physicalcheckupService.createPhysicalcheckupService(physic);
				physDelegate.POST_Physicalcheckup(physic);
				
				if(personService.findById(physic.getPerson().getPersId()) != null && vistDelegate.GET_Visit(physic.getVisit().getVisitId()) != null) {
					model.addAttribute("id", physic.getPhycheId());
					model.addAttribute("phycheDate", physic.getPhycheDate());
					model.addAttribute("person", physic.getPerson());
					model.addAttribute("visit", physic.getVisit());	
				}				
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "redirect:/error";
			}
		}
		return "redirect:/frontapi/physicalcheckups/";
	}
	
	@GetMapping("/physicalcheckups/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		
		//Optional<Physicalcheckup> physic = physicalcheckupService.findById(id);
		Physicalcheckup physic = physDelegate.GET_Physicalcheckup(id);
		
		if (physic == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("physic", physic);
		model.addAttribute("persons", personService.findAll());
		model.addAttribute("visits", vistDelegate.GET_Visits());
		return "physicalcheckups/update-physicalcheckup";
	}
	
	@PostMapping("/physicalcheckups/edit/{id}")
	public String updatePhysicalCheckup(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, 
			@Validated Physicalcheckup physic, BindingResult bindingResult, Model model) {
		
		System.out.println("id: "+ id);
		if (action != null && !action.equals("Cancel")) {
			
			if (bindingResult.hasErrors()) {
								
				return "physicalcheckups/update-physicalcheckup";
			} else {
				try {
					System.out.println("id physic " + physic.getPhycheId());
					//physicalcheckupService.updatePhysicalcheckupService(physic);
					physDelegate.PUT_Physicalcheckup(physic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/frontapi/physicalcheckups";
	}
	

}
