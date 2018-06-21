package it.uniroma3;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.model.Azienda;
//import it.uniroma3.model.Allievo;
import it.uniroma3.model.Centro;
import it.uniroma3.model.Responsabile;
import it.uniroma3.service.AziendaService;
//import it.uniroma3.service.AllievoService;
import it.uniroma3.service.CentroService;
import it.uniroma3.service.ResponsabileService;

@SpringBootApplication
public class SiwDemoSpringApplication {

//	@Autowired
//	private AllievoService allievoService; 
	@Autowired
	private CentroService centroService;
	@Autowired
	private AziendaService aziendaService;
	@Autowired
	private ResponsabileService responsabileService;
	
	public static void main(String[] args) {
		SpringApplication.run(SiwDemoSpringApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
//		Allievo customer = new Allievo("Paolo", "Merialdo","gmail", "09723384792","Genova");
//		allievoService.save(customer);
//	 Allievo c = allievoService.findByEmail("Genova");
//			System.out.println(c.getName());
//		
//		
		Centro centro= new Centro("c1","c1@alice.com","via  c1", (long) 4000, 80, null);
		centroService.save(centro);
		Centro centro2= new Centro("c2","c2@gmail.com", "via  c2", (long) 9005, 90, null);
		centroService.save(centro2);
		Centro centro3= new Centro("c3","c3@gmail.com", "via  c3", (long) 9005, 90, null);
		centroService.save(centro3);
		Azienda azienda= new Azienda("Azienda","12345677");
		aziendaService.save(azienda);
														
		Responsabile resp1= new Responsabile("andrea","$2a$09$OgQXnvh1C5LgzgCLTKqtgOtCGtzVchD9JLMzzumFXm5LGdn4UvfG.", "RESPONSABILE",centro);
		responsabileService.save(resp1);
		Responsabile resp2= new Responsabile("patrizio","$2a$09$OgQXnvh1C5LgzgCLTKqtgOtCGtzVchD9JLMzzumFXm5LGdn4UvfG.", "DIRETTORE",centro);
		responsabileService.save(resp2);
		
		Responsabile resp3= new Responsabile("luca","$2a$09$OgQXnvh1C5LgzgCLTKqtgOtCGtzVchD9JLMzzumFXm5LGdn4UvfG.", "RESPONSABILE",centro3);
		responsabileService.save(resp3);
		 
		
		centro.setResponsabile(resp1);
		centro2.setResponsabile(resp2);

		
		centroService.save(centro);
		centroService.save(centro2);
		centroService.save(centro3);
		
		
		
		
	}
}
