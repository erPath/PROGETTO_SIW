package it.uniroma3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.ResponsabileValidator;
import it.uniroma3.model.Azienda;
import it.uniroma3.model.Centro;
import it.uniroma3.model.Responsabile;
import it.uniroma3.service.ResponsabileService;

public class ResponsabileController {

	
	@Autowired
	private ResponsabileService responsabileService;
	
	@Autowired
	private ResponsabileValidator validator;
	
	 @RequestMapping("/addResp")
	    public String addCustomer(Model model) {
	    	model.addAttribute("responsabile", new Responsabile());
	        return "direttore/respForm";
	}
	 
	 @RequestMapping(value = "/responsabile", method = RequestMethod.POST)
	    public String newCustomer(@Valid @ModelAttribute("responsabile") Responsabile responsabile, 
	    									Model model, BindingResult bindingResult) {
	        this.validator.validate(responsabile, bindingResult);
	        
	        if (this.responsabileService.usernameAlreadyExists(responsabile)) {
	            model.addAttribute("esiste", "Esiste gia' un responsabile con questo username");
	            return "direttore/respForm";
	        }
	        else {
	            if (!bindingResult.hasErrors()) {
	                this.responsabileService.save(responsabile);
	                model.addAttribute("responsabili", this.responsabileService.findAll());
	                
	                
	                return "direttore/mostraCentri";
	            }
	        }
	        return "responsabile/respForm";
	    }



}
