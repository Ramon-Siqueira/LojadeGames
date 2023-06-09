package com.generation.lojadegames.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_jogos")
public class jogos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message ="Este atributo é de preenchimento obrigatório")
	@Size(min = 5, max = 100, message="Este atributo tem que ter no mínimo 5 caracteres e no máximo 100")
	private String titulo;
	
	@NotBlank(message ="Este atributo é de preenchimento obrigatório")
	@Size(min = 5, max = 100, message="Este atributo tem que ter no mínimo 5 caracteres e no máximo 100")
	private String tipo;
	
	@NotBlank(message ="Este atributo é de preenchimento obrigatório")
	@Size(min = 5, max = 100, message="Este atributo tem que ter no mínimo 5 caracteres e no máximo 100")
	private String empresa;
	
	@NotBlank(message ="Este atributo é de preenchimento obrigatório")
	@Size(min = 10, max = 1000, message="Este atributo tem que ter no mínimo 10 caracteres e no máximo 1000")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("Jogos")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("Jogos")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
}
