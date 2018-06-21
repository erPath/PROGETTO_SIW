package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Azienda;
import it.uniroma3.repository.AziendaRepository;

@Service
@Transactional
public class AziendaService {
	
	@Autowired
	private AziendaRepository repository;
	
	public Azienda save(Azienda azienda) {
		return this.repository.save(azienda);
	}

	public Azienda findAll() {
		 List<Azienda>azienda= (List<Azienda>) this.repository.findAll();
		 return azienda.get(0); 
	}

	public Azienda findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Azienda> azienda = this.repository.findById(id);
		if(azienda.isPresent())
			return azienda.get();
		else 
			return null;
	}
}
