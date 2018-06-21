package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Allievo;

public interface AllievoRepository extends CrudRepository<Allievo, Long> {

	public Allievo findByEmail(String email);

	public List<Allievo> findByNameAndSurnameAndEmail(String name, String surname, String email);
	
	public void deleteByEmail(String email);

}
