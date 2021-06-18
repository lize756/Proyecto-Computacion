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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller1.VarelaFanny.delegate.IVisitDelegate;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.services.InstitutioncampusService;
import com.taller1.VarelaFanny.services.PersonService;
import com.taller1.VarelaFanny.services.VisitService;


import javassist.NotFoundException;

@Controller
@RequestMapping("/frontapi")
public class VisitController {

	private VisitService visitService;
	private IVisitDelegate vistDelegate;
	private PersonService personService;
	private InstitutioncampusService institutioncampusService;
	
	
	@Autowired
	public VisitController(IVisitDelegate vistDelegate ,VisitService visitService, PersonService personService,  InstitutioncampusService institutioncampusService) {
		
		this.vistDelegate = vistDelegate;
		this.visitService = visitService;
		this.personService = personService;
		this.institutioncampusService = institutioncampusService;	
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	
	 
	@RequestMapping(value = "/visits", method = RequestMethod.GET)
	public String visits(Model model) {
		model.addAttribute("visits", vistDelegate.GET_Visits());
		return "visits/index";
	}
	
	
	@GetMapping("/visits/add-visit")
	public String addVisit(Model model) {
		model.addAttribute("visit", new Visit());
		model.addAttribute("persons", personService.findAll());
		model.addAttribute("institutioncampus", institutioncampusService.findAll());
		
		return "visits/add-visit";
	}
	
	
	@PostMapping("/visits/add-visit")
	public String saveVisit(@RequestParam(value = "action", required = true) String action, @RequestParam("visitEntrancedate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date visitEntrancedate, @Validated Visit visit,
			BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/frontapi/visits";
		}
		
		if(bindingResult.hasErrors()) {
			return "visits/add-visit";
		}
		
		if(!action.equals("Cancel")) {
			try {
				
				vistDelegate.POST_Visit(visit);
				
				if(personService.findById(visit.getPerson().getPersId()) != null && institutioncampusService.findById(visit.getInstitutioncampus().getInstcamId()) != null) {
					model.addAttribute("id", visit.getVisitId());
					model.addAttribute("visitDetail", visit.getVisitDetail());		
				    model.addAttribute("visitEntrancedate", visit.getVisitEntrancedate());
					model.addAttribute("visitExitdate", visit.getVisitExitdate());
					model.addAttribute("person", visit.getPerson());
					model.addAttribute("institutioncampus", visit.getInstitutioncampus());
				}
											
				//return "redirect:/visits";
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "redirect:/error";
			}
		}
		return "redirect:/frontapi/visits";
	}
	
	
	@GetMapping("/visits/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		//Optional<Visit> visit = visitService.findById(id);
		Visit visit = vistDelegate.GET_Visit(id);
		if (visit == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("visit", visit);
		model.addAttribute("persons", personService.findAll());
		model.addAttribute("institutioncampus", institutioncampusService.findAll());
		return "visits/update-visit";
	}
	
	@PostMapping("/visits/edit/{id}")
	public String updateVisit(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated Visit visit, BindingResult bindingResult, Model model) {
		
		
		System.out.println("id: "+ id);
		if (action != null && !action.equals("Cancel")) {
			
			if (bindingResult.hasErrors()) {
				//model.addAttribute("visits", visitServiceImpl.findAll());
				
				return "visits/update-visit";
			} else {
				try {
					System.out.println("id visit " + visit.getVisitId());
					//visitService.updateVisit(visit);	
					vistDelegate.PUT_Visit(visit);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/frontapi/visits";
	}
	
	@GetMapping("/visits/consult-visit")
    public String consultVisit(Model model) {
        model.addAttribute("visit", new Visit());
        return "visits/consult-visit";
    }

    @PostMapping("visits/consult-visit")
    public String showConsultVisit(@ModelAttribute Visit visit, Model model) throws NotFoundException {

   
    	
    	System.out.println("consult id visit"+ visit.getVisitId());
        //Visit vis = visitService.findById(visit.getVisitId()).get();
        Visit vis = vistDelegate.GET_Visit(visit.getVisitId());
        	
        
        if (vis == null) {
            throw new NotFoundException("USU NO ENCONTRADO");
        }

        model.addAttribute("visit", vis);

        return "visits/update-visit";
    }
	
	
	
}
