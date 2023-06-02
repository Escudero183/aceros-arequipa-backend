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
@Table(name = "parameter")
public class Parameter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_parameter")
	@ApiModelProperty(value = "Clave primaria del Modelo Parameter.")
	private Integer idParameter;
	
	private String keyparam;
	
	private String value;

}
