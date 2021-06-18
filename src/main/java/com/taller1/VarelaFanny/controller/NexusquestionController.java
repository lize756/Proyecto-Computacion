package com.taller1.VarelaFanny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.taller1.VarelaFanny.delegate.INexuspollDelegate;
import com.taller1.VarelaFanny.delegate.INexusquestionDelegate;
import com.taller1.VarelaFanny.model.Nexuspoll;
import com.taller1.VarelaFanny.model.Nexusquestion;

@Controller
public class NexusquestionController {
	
	private INexusquestionDelegate nexusquesDelegate;
	private INexuspollDelegate nexusDelegate;

	@Autowired
	public NexusquestionController(INexusquestionDelegate nexusquesDelegate, INexuspollDelegate nexusDelegate) {
		this.nexusquesDelegate = nexusquesDelegate;
		this.nexusDelegate = nexusDelegate;
	}
	
	@RequestMapping(value = "/nexusquestions", method = RequestMethod.GET)
	public String nexus(Model model) {
		System.out.println("Tamaño nexos list: "+ nexusquesDelegate.GET_Nexusquestions().size());
		model.addAttribute("nexusquestions", nexusquesDelegate.GET_Nexusquestions());
		return "nexusquestions/index";
	}
	
	@GetMapping("/nexusquestions/add-nexusquestion")
	public String addNexus(Model model) {
		model.addAttribute("nexus", new Nexusquestion());
		model.addAttribute("nexuspolls", nexusDelegate.GET_Nexuspolls());
		
		return "nexusquestions/add-nexusquestion";
	}
	
	@PostMapping("/nexusquestions/add-nexusquestion")
	public String saveNexus(@RequestParam(value = "action", required = true) String action, @Validated Nexusquestion nexus,
			BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/nexusquestions";
		}
		
		if(bindingResult.hasErrors()) {
			return "nexusquestions/add-nexusquestion";
		}
		
		if(!action.equals("Cancel")) {
			try {
				
				nexusquesDelegate.POST_Nexusquestion(nexus);
				model.addAttribute("id", nexus.getNexquesId());
				model.addAttribute("nexquesName", nexus.getNexquesName());
				model.addAttribute("nexquesWeight", nexus.getNexquesWeight());
				model.addAttribute("nexuspoll", nexus.getNexuspoll());
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "redirect:/error";
			}
		}
		return "redirect:/nexusquestions";
	}
	
	
	@GetMapping("/nexusquestions/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		
		Nexusquestion nexus = nexusquesDelegate.GET_Nexusquestion(id);
		
		if (nexus == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("nexus", nexus);
		model.addAttribute("nexuspolls", nexusDelegate.GET_Nexuspolls());

		return "nexusquestions/update-nexusquestion";
	}
	
	@PostMapping("/nexusquestions/edit/{id}")
	public String updateNexus(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated Nexusquestion nexus, BindingResult bindingResult, Model model) {
		
		
		System.out.println("id: "+ id);
		if (action != null && !action.equals("Cancel")) {
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("nexus", nexusquesDelegate.GET_Nexusquestions());
				
				return "nexusquestions/update-nexusquestion";
			} else {
				try {
					System.out.println("id nexus " + nexus.getNexquesId());
					nexusquesDelegate.PUT_Nexusquestion(nexus);	
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/nexusquestions";
	}
	
	
	@GetMapping("/nexusquestions/poll")
	public String findByInstitution(@RequestParam(value = "nexpollId",  required = true) long nexpollId , Model model){
		
		List<Nexusquestion> nexusquestions = nexusquesDelegate.findByPoll(nexpollId);
		model.addAttribute("nexusquestions", nexusquestions);
		return "nexusquestions/poll";
	}

}
