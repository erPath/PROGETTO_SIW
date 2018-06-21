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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.controller.validator.AllievoValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Azienda;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.AziendaService;

@Controller
public class AllievoController {
	
    @Autowired
    private AllievoService allievoService;

    @Autowired
    private AllievoValidator validator;

    @Autowired
	private AziendaService aziendaService;
//    @Autowired
//	private AttivitaService attivitaService;

    @RequestMapping("/allievi")
    public String allievi(Model model) {
        model.addAttribute("allievi", this.allievoService.findAll());
        return "responsabile/allievoList";
    }
    @RequestMapping("/homepage")
    public String homePage(Model model) {
    	return "index";
    }
    @RequestMapping("/allieviAttivita")
    public String allieviAttivita(Model model) {
    	model.addAttribute("allievi", this.allievoService.findAll());
    	return "responsabile/allievoListAttivita"; 
    }
    @RequestMapping("/addAllievo")
    public String addCustomer(Model model) {
    	model.addAttribute("allievo", new Allievo());
    	model.addAttribute("azienda", new Azienda());
        return "responsabile/allievoForm";
    }

    @RequestMapping(value = "/allievo/{id}", method = RequestMethod.GET)
    public String getCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("allievo", this.allievoService.findById(id));
    	return "responsabile/showAllievo";
    }
   
   
    
    @RequestMapping(value = "/allievo", method = RequestMethod.POST)
    public String newCustomer(@Valid @ModelAttribute("allievo") Allievo allievo, 
    									Model model, BindingResult bindingResult) {
        this.validator.validate(allievo, bindingResult);
        
        if (this.allievoService.emailAlreadyExists(allievo)) {
            model.addAttribute("esiste", "Esiste gia' un allievo con questa email");
            return "responsabile/allievoForm";
        }
        else {
            if (!bindingResult.hasErrors()) {
                this.allievoService.save(allievo);
                model.addAttribute("allievi", this.allievoService.findAll());
                Azienda aziendaAppartenenza= this.aziendaService.findAll();
                aziendaAppartenenza.getAllievi().add(allievo);
                model.addAttribute("azienda_id", this.aziendaService.findById(aziendaAppartenenza.getId()));
                model.addAttribute("allievi", aziendaAppartenenza.getAllievi());
                return "responsabile/allievoList";
            }
        }
        return "responsabile/allievoForm";
    }
    
    @RequestMapping(value="/allievoRimosso",method=RequestMethod.GET)
	public String deleteByNome(@RequestParam("email") String email,Model model) {
		this.allievoService.deleteByEmail(email);
		model.addAttribute("allievi",this.allievoService.findAll());
		return "responsabile/allievoList";
	}
	
	@RequestMapping("/rimuoviAllievo")
	public String delete(Model model) {
		return "rimuoviAllievo";
	}


}
