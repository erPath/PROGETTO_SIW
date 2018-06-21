package it.uniroma3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Centro {
	
	public Centro(String nome, String email, String indirizzo, Long numeroDiTelefono, int capienza, Responsabile responsabile) {
		this.nome = nome;
		this.email = email;
		this.indirizzo = indirizzo;
		this.numeroDiTelefono = numeroDiTelefono;
		this.capienza = capienza;
		this.responsabile= responsabile;
	}

	public Centro() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(nullable= false)
	private String nome;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String indirizzo;
	
	@Column(nullable=false)
	private Long numeroDiTelefono;
	
	@Column(nullable=false)
	private int capienza;
	
	@OneToMany(mappedBy="centro",cascade=CascadeType.ALL)
	private List<Attivita> attivita;

	@OneToOne
	private Responsabile responsabile; 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Long getNumeroDiTelefono() {
		return numeroDiTelefono;
	}

	public void setNumeroDiTelefono(Long numeroDiTelefono) {
		this.numeroDiTelefono = numeroDiTelefono;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public List<Attivita> getAttivita() {
		return attivita;
	}

	public void setAttivita(List<Attivita> attivita) {
		this.attivita = attivita;
	}

	public Responsabile getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(Responsabile responsabile) {
		this.responsabile = responsabile;
	}
	
	
	

}
