package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.repository.AttivitaRepository;

@Transactional
@Service
public class AttivitaService {

	@Autowired
	private AttivitaRepository attivitaRepository;
	
	public Attivita save(Attivita attivita) {
		return this.attivitaRepository.save(attivita);
	}

	public boolean alreadyExists(Attivita attivita) {
		// TODO Auto-generated method stub
		List<Attivita> attivitas= this.attivitaRepository.findByName(attivita.getName());
		if(attivitas.size()>0) 
			return true;
		else
			return false;
	}
	public boolean alreadySigned(Attivita attivita, Allievo allievo) {
		for (Allievo a : attivita.getAllievi()) {
			if(a.getId().equals(allievo.getId()))
				return true; 
		}
		return false; 
	}
	public List<Attivita> findAll() {
		// TODO Auto-generated method stub
		return (List<Attivita>) this.attivitaRepository.findAll();
	}

	public Attivita findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Attivita> attivita = this.attivitaRepository.findById(id);
		if(attivita.isPresent())
			return attivita.get();
		else 
			return null;
	}

	public Attivita findByNome(String nome) {
		// TODO Auto-generated method stub
		 List<Attivita> attivita = this.attivitaRepository.findByName(nome);
		 if(!attivita.isEmpty())
			 return attivita.get(0);
		 else 
			 return null;
	}
	
	public void deleteByNome(String name) {
		this.attivitaRepository.deleteByName(name);
	}
	
	public List<Attivita> findByCentro(Centro centro){
		return this.attivitaRepository.findByCentro(centro);
	}
}
