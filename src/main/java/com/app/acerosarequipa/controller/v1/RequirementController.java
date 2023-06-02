package com.app.acerosarequipa.controller.v1;

import java.io.File;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.acerosarequipa.config.exception.ApiException;
import com.app.acerosarequipa.config.exception.RestException;
import com.app.acerosarequipa.helper.Helpers;
import com.app.acerosarequipa.helper.ManageFiles;
import com.app.acerosarequipa.mappers.WorkerMapper;
import com.app.acerosarequipa.model.Parameter;
import com.app.acerosarequipa.model.RequirementRequest;
import com.app.acerosarequipa.model.Worker;
import com.app.acerosarequipa.service.ParameterService;
import com.app.acerosarequipa.service.RequirementRequestService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Linygn Escudero
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/manage-requirements", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RequirementController {
	
	@Autowired
	private RequirementRequestService requirementRequestService;
	
	@Autowired
	private ParameterService parameterService;
	
	@ApiOperation(value = "Crea una Solicitud de Requerimiento")	
	@PostMapping(value = "/request", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveRequest(
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "description", required = false, defaultValue = "") String description,
			@RequestParam(value = "idWorker", required = false, defaultValue = "") Integer idWorker,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws ApiException{
		HashMap<String, Object> result = new HashMap<>();
		
		
		//Proceso para subir archivo anexo
		Parameter dataP = parameterService.findById(2);
		Helpers metodos = new Helpers();
		
		RequirementRequest bean = new RequirementRequest();
		bean.setTitle(title);
		bean.setDescription(description);
		bean.setIdWorker(idWorker);
		bean.setRequestStatus("O");
		bean.setCode("REQ-" + metodos.getAlphaNumeric(6));
		bean.setVbRecord(new java.sql.Timestamp(System.currentTimeMillis()));
		bean.setVbSendEmail(new java.sql.Timestamp(System.currentTimeMillis()));
		
		String pathRepo = dataP.getValue();
		String folder = "/files/";
		
		File carpeta = new File(pathRepo+folder);
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		Boolean isSuccess = requirementRequestService.insert(bean);
		
		if(isSuccess) {
			String namefile = bean.getCode() + "-" + metodos.getAlphaNumeric(6); 
			ManageFiles manageFile = new ManageFiles(); 
			String pathFile = manageFile.upLoadFiles(pathRepo,folder,file,namefile);
			
			bean.setUrlAnnex(pathFile);
			requirementRequestService.updateFile(bean);
			
		}
		
		String msg = isSuccess ? "Se ha registrado correctamente." : "Ha ocurrido un error, intente en otro momento.";
		
		result.put("success", isSuccess);
		result.put("message", msg);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualiza una Solicitud de Requerimiento")	
	@PutMapping(value = "/request", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateRequest(
			@RequestParam(value = "idRequirementRequest", required = true, defaultValue = "-1") Integer idRequirementRequest,
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "description", required = false, defaultValue = "") String description,
			@RequestParam(value = "idWorker", required = false, defaultValue = "") Integer idWorker,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws ApiException{
		HashMap<String, Object> result = new HashMap<>();
		
		RequirementRequest data = requirementRequestService.findById(idRequirementRequest);
		if(data == null) {
			result.put("success", false);
			result.put("message", "No existe el registro con código: " + idRequirementRequest);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND); 
		}
		
		try {
			RequirementRequest bean = new RequirementRequest();
			bean.setIdRequirementRequest(idRequirementRequest);
			bean.setTitle(title);
			bean.setDescription(description);
			bean.setIdWorker(idWorker);
			bean.setRequestStatus("O");
			bean.setCode(data.getCode());
			bean.setVbRecord(new java.sql.Timestamp(System.currentTimeMillis()));
			bean.setVbSendEmail(new java.sql.Timestamp(System.currentTimeMillis()));
			
			if(file != null) {
				Parameter dataP = parameterService.findById(2);
				Helpers metodos = new Helpers();
				
				
				String pathRepo = dataP.getValue();
				String folder = "/files/";
				
				File carpeta = new File(pathRepo+folder);
				if (!carpeta.exists()) {
					carpeta.mkdirs();
				}
				String namefile = bean.getCode() + "-" + metodos.getAlphaNumeric(6); 
				ManageFiles manageFile = new ManageFiles(); 
				String pathFile = manageFile.upLoadFiles(pathRepo,folder,file,namefile);
				
				bean.setUrlAnnex(pathFile);
			}else {
				bean.setUrlAnnex(data.getUrlAnnex());
			}
			requirementRequestService.update(bean);
			
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos del registro.");
			result.put("result", bean);
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<>(new RestException(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
