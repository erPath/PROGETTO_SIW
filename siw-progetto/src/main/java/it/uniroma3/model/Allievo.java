package it.uniroma3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Allievo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String surname;
	
	@Column(unique=true,nullable=false)
	private String email;
	
	@Column
	private String numberphone;
	
	@Column
	private String birthplace;
	
	@ManyToMany(mappedBy="allievi",cascade=CascadeType.ALL)
	private List<Attivita> attivita;
	
	public Allievo() {}

	public Allievo(String name, String surname, String email, String numberphone, String birthplace) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.numberphone= numberphone;
		this.birthplace=birthplace;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getEmail() {
		return this.email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumberphone() {
		return numberphone;
	}

	public void setNumberphone(String numberphone) {
		this.numberphone = numberphone;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public List<Attivita> getAttivita() {
		return attivita;
	}

	public void setAttivita(List<Attivita> attivita) {
		this.attivita = attivita;
	}
	
	
}
