package com.example.docker.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="test_docker")
@Setter @Getter
public class Test {
	@Id
	private String a;
	private Integer b;
}
