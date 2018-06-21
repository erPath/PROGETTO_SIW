package it.uniroma3.controller;


import java.util.List;

//import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

//import it.uniroma3.controller.validator.AllievoValidator;
import it.uniroma3.controller.validator.AttivitaValidator;
import it.uniroma3.model.Allievo;
//import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
//import it.uniroma3.model.Azienda;
import it.uniroma3.model.Centro;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
//import it.uniroma3.service.AziendaService;
import it.uniroma3.service.CentroService;

@Controller
public class AttivitaController {

	@Autowired
	private AttivitaService attivitaService; 

	@Autowired
	private AttivitaValidator validator; 

	@Autowired
	private AllievoService allievoService;
	//	@Autowired
	//	private AllievoValidator allievoValidator;
	@Autowired
	private CentroService centroService;
	
	@RequestMapping(value="/getattivitas")
	public String attivitas(Model model) {
		model.addAttribute("attivitas", attivitaService.findAll());
		return "responsabile/mostraListaAttivita";
	}
	@RequestMapping(value="/attivitas",method=RequestMethod.GET)
	public String attivitas(Model model,@RequestParam("email") String email) {
		Allievo allievo= this.allievoService.findByEmail(email);
		if(allievo.getAttivita().size()==0) {
			model.addAttribute("nessunaAttivita", "L'allievo non e' iscritto a nessuna attivita'");
			return "responsabile/mostraListaAttivita";
		}
		else {
		model.addAttribute("attivitas", allievo.getAttivita());
		return "responsabile/mostraListaAttivita";
		}
	}
	

	@RequestMapping("/addAttivita")
	public String addAttivita(Model model) {
		model.addAttribute("attivita", new Attivita());
		model.addAttribute("centro", new Centro());
		return "responsabile/attivitaForm"; 
	}

	@RequestMapping(value = "/attivita/{id}", method = RequestMethod.GET)
	public String getAttivita(@PathVariable("id") Long id, Model model) {
		model.addAttribute("attivita", this.attivitaService.findById(id));
		return "responsabile/showAttivita";
	}
	@RequestMapping("/addAllievoAttivita")
	public String aggiungiAllievoAttivita(Model model) {
		model.addAttribute("attivitas", this.attivitaService.findAll());
		model.addAttribute("allievi", this.allievoService.findAll());
		return "responsabile/attivitaList";
	}

	@RequestMapping("/linkAllievoAttivita")
	public String inserisciAllievoAttivita(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {
		Attivita attivita = this.attivitaService.findByNome(name);

		Allievo allievo = this.allievoService.findByEmail(email);
		
		if(attivita==null || allievo== null) {
			model.addAttribute("notexists","ATTENZIONE! l'email dell'allievo o il nome dell'attività non esistono");
			attivitas(model,email);
			return "responsabile/attivitaList";
		}else if(attivita !=null && !attivitaService.alreadySigned(attivita, allievo) ){
			List<Allievo> allieviAttivita = attivita.getAllievi();
			allieviAttivita.add(allievo);
			model.addAttribute("allievi",this.allievoService.findById(allievo.getId()));
			List<Attivita> attivitaAllievo = allievo.getAttivita();
			attivitaAllievo.add(attivita);
			Attivita attivitaId = this.attivitaService.findById(attivita.getId());
			model.addAttribute("attivita",attivitaId);
			model.addAttribute("allievi",attivita.getAllievi());
			return "responsabile/allievoList";
		}
		else {
			model.addAttribute("giaIscritto","L'allievo è già presente nell'elenco degli iscritti ");
			return "responsabile/attivitaList";
		}

	}



	@RequestMapping(value="/attivita",method=RequestMethod.POST)
	public String newAttivita(@Valid @ModelAttribute("attivita") Attivita attivita, Model model, BindingResult bindingResult) {

		this.validator.validate(attivita, bindingResult);

		if(this.attivitaService.alreadyExists(attivita)) {
			model.addAttribute("exists", "Attivita already exists");
			return "responsabile/attivitaForm";
		}	else 
			if(!bindingResult.hasErrors()) {
				this.attivitaService.save(attivita);
				Centro centroAppartenenza = this.centroService.findAll().get(0);// tocca cambiarla
				attivita.setCentro(centroAppartenenza);
				model.addAttribute("centro",centroAppartenenza);
				model.addAttribute("attivitas", this.attivitaService.findAll());
				return "responsabile/mostraListaAttivita";
			}
		return "responsabile/attivitaForm";

	}
	
	@RequestMapping(value="/attivitaRimossa",method=RequestMethod.GET)
	public String deleteByNome(@RequestParam("nome") String nome,Model model) {
		this.attivitaService.deleteByNome(nome);
		model.addAttribute("attivitas",this.attivitaService.findAll());
		return "responsabile/attivitaList";
	}
	
	@RequestMapping("/rimuoviAttivita")
	public String delete(Model model) {
		return "rimuoviAttivita";
	}
	
}
