package it.uniroma3.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Azienda {
	
	
	
	public Azienda(String nome, String partitaIva) {
		this.nome = nome;
		PartitaIva = partitaIva;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Azienda() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(nullable= false)
	private String nome;
	
	@Column(nullable=false)
	private String PartitaIva;
	
	@OneToMany
	@JoinColumn(name="azienda_id")
	private List<Allievo> allievi;
	
	@OneToMany
	@JoinColumn(name="azienda_id")
	private List<Centro> centri;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPartitaIva() {
		return PartitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		PartitaIva = partitaIva;
	}

	public List<Allievo> getAllievi() {
		return allievi;
	}

	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}

	public List<Centro> getCentri() {
		return centri;
	}

	public void setCentri(List<Centro> centri) {
		this.centri = centri;
	} 
	
	
}
