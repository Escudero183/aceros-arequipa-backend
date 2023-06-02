package com.app.acerosarequipa.model;

import java.util.Date;

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
@Table(name = "requirement_request")
public class RequirementRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_requirement_request")
	@ApiModelProperty(value = "Clave primaria del Modelo Requirement Request.")
	private Integer idRequirementRequest;
	
	private String code;
	
	private String title;
	
	private String description;
	
	private String urlAnnex;
	
	private Date vbRecord;
	
	private Date vbSendEmail;
	
	private String requestStatus;
	
	private String firstNameWorker;
	
	private String lastNameWorker;
	
	private String photoWorker;
	
	private String emailWorker;
	
	private String documentoWorker;
	
	private String positionWorker;
	
	private Integer idWorker;

}
