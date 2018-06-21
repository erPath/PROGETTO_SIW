package it.uniroma3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.CentroValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Azienda;
import it.uniroma3.model.Centro;
import it.uniroma3.model.Responsabile;
import it.uniroma3.service.AziendaService;
import it.uniroma3.service.CentroService;
import it.uniroma3.service.ResponsabileService;

@Controller
public class CentroController {
	
	@Autowired
	private CentroService service; 
	
	@Autowired 
	private AziendaService aziendaService;
	@Autowired
	private ResponsabileService respService;
	
	@Autowired
	private CentroService centroService;
	
	@Autowired
	private CentroValidator centroValidator;
	
	//getCentri verrà richiamato a seguito del login anzichè dall'azione getCentri dall'index
	@RequestMapping(value="/getCentri")
	public String getCentri(Model model) {
		Azienda aziendaAppartenenza= this.aziendaService.findAll();
		Centro centro= this.service.findAll().get(0);
		
		aziendaAppartenenza.getCentri().add(centro);
		model.addAttribute("azienda_id", this.aziendaService.findById(aziendaAppartenenza.getId()));
		model.addAttribute("centri", aziendaAppartenenza.getCentri());
		return "responsabile/mostraCentri";
	}
	
	 @RequestMapping(value = "/centro/{id}", method = RequestMethod.GET)
	    public String getCustomer(@PathVariable("id") Long id, Model model) {
	        model.addAttribute("centro", this.service.findById(id));
	    	return "direttore/mostraCentro";
	 }
	 
	 @RequestMapping("/addCentro")
	    public String addCustomer(Model model) {
	    	model.addAttribute("centro", new Centro());
	        return "direttore/centroForm";
	}
	 
	 @RequestMapping(value = "/centro", method = RequestMethod.POST)
	    public String newCustomer(@Valid @ModelAttribute("centro") Centro centro, 
	    									Model model, BindingResult bindingResult) {
	        this.centroValidator.validate(centro, bindingResult);
	        
	        if (this.centroService.nomeAlreadyExists(centro)) {
	            model.addAttribute("esiste", "Esiste gia' un centro con questo nome");
	            return "direttore/centroForm";
	        }
	        else {
	            if (!bindingResult.hasErrors()) {
	                this.centroService.save(centro);
	                model.addAttribute("centri", this.centroService.findAll());
	                Azienda aziendaAppartenenza= this.aziendaService.findAll();
	                aziendaAppartenenza.getCentri().add(centro);
	                
	                return "direttore/mostraCentri";
	            }
	        }
	        return "responsabile/allievoForm";
	    }

	
}
