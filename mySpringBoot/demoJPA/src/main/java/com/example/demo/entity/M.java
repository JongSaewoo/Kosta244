package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Setter @Getter
public class M {
	@Id
	private String id;
	
	private String name;
	private String role;
	
	@OneToMany
	private List<B> bList;
}
