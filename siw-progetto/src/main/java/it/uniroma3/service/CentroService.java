package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Centro;
import it.uniroma3.repository.CentroRepository;

@Transactional
@Service
public class CentroService {

	@Autowired
	private CentroRepository repository;

	public Centro save(Centro centro) {
		return this.repository.save(centro);
	}

	public List<Centro> findAll() {
		// TODO Auto-generated method stub
		return (List<Centro>) this.repository.findAll();
	}

	public Centro findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Centro> centro = this.repository.findById(id);
		if(centro.isPresent())
			return centro.get();
		else 
			return null;
	}
	
	public boolean nomeAlreadyExists(Centro centro) {
		Centro check = this.repository.findByNome(centro.getNome());
		if (check!=null) {
			return true;
		}
		else 
			return false;
	}
}
