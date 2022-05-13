package com.hortin.HORTIN.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Vendedor{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Vendedor(){
	}
	
	public Vendedor(Long id){
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
