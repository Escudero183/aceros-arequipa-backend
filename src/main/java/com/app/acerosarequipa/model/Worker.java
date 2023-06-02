package com.app.acerosarequipa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@Entity
@Table(name = "worker")
public class Worker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_worker")
	@ApiModelProperty(value = "Clave primaria del Modelo Worker.")
	private Integer idWorker;
	
	private String firstName;
	
	private String lastName;
	
	private String document;
	
	private String position;
	
	private String photo;
	
	private String email;
	
}
