package com.example.freemarket.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
	
	public Role(String name) {
		this.name = name;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Role name cant be empty")
	@Column(nullable = false, length = 50)
	private String name;
	
	private String description;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
}