package com.taller1.VarelaFanny.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.taller1.VarelaFanny.delegate.INexuspollDelegate;
import com.taller1.VarelaFanny.model.Nexuspoll;


@Controller
public class NexusPollController {
	
	private INexuspollDelegate nexusDelegate;
	
	@Autowired
	public NexusPollController(INexuspollDelegate nexusDelegate) {
		this.nexusDelegate = nexusDelegate;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@RequestMapping(value = "/nexuspolls", method = RequestMethod.GET)
	public String nexus(Model model) {
		System.out.println("Tamaño nexos list: "+ nexusDelegate.GET_Nexuspolls().size());
		model.addAttribute("nexuspolls", nexusDelegate.GET_Nexuspolls());
		return "nexuspolls/index";
	}
	
	@GetMapping("/nexuspolls/add-nexuspoll")
	public String addNexus(Model model) {
		model.addAttribute("nexus", new Nexuspoll());
		return "nexuspolls/add-nexuspoll";
	}
	
	@PostMapping("/nexuspolls/add-nexuspoll")
	public String saveNexus(@RequestParam(value = "action", required = true) String action, @Validated Nexuspoll nexus,
			BindingResult bindingResult, Model model) {
		
		if(action.equals("Cancel")) {
			return "redirect:/nexuspolls";
		}
		
		if(bindingResult.hasErrors()) {
			return "nexuspolls/add-nexuspoll";
		}
		
		if(!action.equals("Cancel")) {
			try {
				
				nexusDelegate.POST_Nexuspoll(nexus);
				model.addAttribute("id", nexus.getNexpollId());
				model.addAttribute("nexpollName", nexus.getNexpollName());
				model.addAttribute("nexpollStartdate", nexus.getNexpollStartdate());
				model.addAttribute("nexpollEnddate", nexus.getNexpollEnddate());
				model.addAttribute("instInstId", nexus.getInstInstId());
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "redirect:/error";
			}
		}
		return "redirect:/nexuspolls";
	}
	
	
	@GetMapping("/nexuspolls/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		
		Nexuspoll nexus = nexusDelegate.GET_Nexuspoll(id);
		
		if (nexus == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("nexus", nexus);

		return "nexuspolls/update-nexuspoll";
	}
	
	@PostMapping("/nexuspolls/edit/{id}")
	public String updateNexus(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated Nexuspoll nexus, BindingResult bindingResult, Model model) {
		
		
		System.out.println("id: "+ id);
		if (action != null && !action.equals("Cancel")) {
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("nexus", nexusDelegate.GET_Nexuspolls());
				
				return "nexuspoll/update-nexuspoll";
			} else {
				try {
					System.out.println("id nexus " + nexus.getNexpollId());
					nexusDelegate.PUT_Nexuspoll(nexus);	
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/nexuspolls";
	}
	
	@GetMapping("/nexuspolls/institution")
	public String findByInstitution(@RequestParam(value = "instInstId",  required = true) long instInstId , Model model){
		
		List<Nexuspoll> nexuspolls = nexusDelegate.findByinstitution(instInstId);
		model.addAttribute("nexuspolls", nexuspolls);
		return "nexuspolls/institution";
	}

}
