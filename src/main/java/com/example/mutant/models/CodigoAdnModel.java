package com.example.mutant.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.apache.tomcat.util.buf.StringUtils;

@Document("mutantes")
public class CodigoAdnModel {

	@Id
	private String id;
	private String dna;
	private Boolean mutante;
	
	public CodigoAdnModel() {}
	
	public CodigoAdnModel(String[] dna) {
		super();
		this.dna = StringUtils.join(dna);
		this.mutante = false;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Boolean getMutante() {
		return mutante;
	}

	public void setMutante(Boolean mutante) {
		this.mutante = mutante;
	}

	@Override
	public String toString() {
		return "CodigoAdnModel [id=" + id + ", mutante=" + mutante
				+ "]";
	}
	

	
	
	
	
}
