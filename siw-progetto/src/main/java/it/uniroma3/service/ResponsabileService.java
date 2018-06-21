package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Responsabile;
import it.uniroma3.repository.ResponsabileRepository;

@Transactional
@Service
public class ResponsabileService {
	
	@Autowired
	private ResponsabileRepository rep; 
	
	public Responsabile save(Responsabile resp) {
		return this.rep.save(resp);
	}
	public Responsabile findById(Long id){
		
		Optional<Responsabile> responsabile = this.rep.findById(id);
		if(responsabile.isPresent())
			return responsabile.get();
		else 
			return null;
		
	}
	public List<Responsabile> findAll() {
		// TODO Auto-generated method stub
		return (List<Responsabile>) this.rep.findAll();
	}
	public Responsabile findByUsername(String name) {
		// TODO Auto-generated method stub
		return this.rep.findByUsername(name);
	}
	
	public boolean usernameAlreadyExists(Responsabile responsabile) {
		Responsabile check = this.rep.findByUsername(responsabile.getUsername());
		if (check!=null) {
			return true;
		}
		else 
			return false;
	}

	
}
