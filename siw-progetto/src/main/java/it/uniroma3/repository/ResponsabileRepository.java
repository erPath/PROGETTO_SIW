package it.uniroma3.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Responsabile;

public interface ResponsabileRepository extends CrudRepository<Responsabile, Long>{

	public Responsabile findByUsername(String name);

}
