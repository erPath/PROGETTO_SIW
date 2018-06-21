package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Allievo;
import it.uniroma3.repository.AllievoRepository;

@Transactional
@Service
public class AllievoService {
	
	@Autowired
	private AllievoRepository customerRepository; 
	
	public Allievo save(Allievo customer) {
		return this.customerRepository.save(customer);
	}

	public Allievo findByEmail(String email) {
		return this.customerRepository.findByEmail(email);
	}

	public List<Allievo> findAll() {
		return (List<Allievo>) this.customerRepository.findAll();
	}
	
	public Allievo findById(Long id) {
		Optional<Allievo> customer = this.customerRepository.findById(id);
		if (customer.isPresent()) 
			return customer.get();
		else
			return null;
	}

	public boolean emailAlreadyExists(Allievo customer) {
		Allievo check = this.customerRepository.findByEmail(customer.getEmail());
		if (check!=null) {
			return true;
		}
		else 
			return false;
	}
	
	public void deleteByEmail(String email) {
		this.customerRepository.deleteByEmail(email);
	}
}
