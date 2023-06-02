package com.app.acerosarequipa.controller.v1;

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

import com.app.acerosarequipa.model.Parameter;
import com.app.acerosarequipa.model.Worker;
import com.app.acerosarequipa.service.ParameterService;
import com.app.acerosarequipa.service.WorkerService;
import io.swagger.annotations.ApiOperation;

/**
 * @author Linygn Escudero
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/administration", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AdministrationController {
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private ParameterService parameterService;
	
	@ApiOperation(value = "Lista todas los Parámetros")
	@GetMapping(value = "/parameter")	
	public ResponseEntity<?> findAllParameters(
			@RequestParam(value = "query", required = false, defaultValue = "") String query,
			HttpServletRequest request) {
		return new ResponseEntity<>(parameterService.findAll(query), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crea un Parámetro")	
	@PostMapping(value = "/parameter")
	public ResponseEntity<?> saveParameter(@RequestBody Parameter parameter, HttpServletRequest request){
		HashMap<String, Object> result = new HashMap<>();
		Boolean isSuccess = parameterService.insert(parameter);
		String msg = isSuccess ? "Se ha registrado correctamente." : "Ha ocurrido un error, intente en otro momento.";
		
		result.put("success", isSuccess);
		result.put("message", msg);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualiza datos de un Parámetro")
	@PutMapping(value = "/parameter")
	public ResponseEntity<?> updateParameter (@RequestBody Parameter parameter, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Boolean isSuccess = parameterService.update(parameter);
		String msg = isSuccess ? "Se ha actualizado los datos correctamente." : "Ha ocurrido un error, intente en otro momento.";
		
		result.put("success", isSuccess);
		result.put("message", msg);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtiene datos de un Parámetro")
	@GetMapping(value = "/parameter/{idParameter}")
	public ResponseEntity<?> findParameter(@PathVariable(value = "idParameter") Integer idParameter, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Parameter data = parameterService.findById(idParameter);
		if(data == null) {
			result.put("success", false);
			result.put("message", "No existe el Parámetro con código: " + idParameter);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND); 
		}
		
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("result", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un Parámetro")
	@DeleteMapping(value = "/parameter/{idParameter}")
	public ResponseEntity<?> deleteParameter (@PathVariable(value = "idParameter") Integer idParameter, HttpServletRequest request){
		HashMap<String, Object> result = new HashMap<>();
		Boolean isSuccess = parameterService.delete(idParameter);
		String msg = isSuccess ? "Se ha eliminado el registro correctamente." : "Ha ocurrido un error, intente en otro momento.";
		
		result.put("success", isSuccess);
		result.put("message", msg);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Lista todas los Trabajadores")
	@GetMapping(value = "/worker")	
	public ResponseEntity<?> findAllWorkers(
			@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@RequestParam(value = "position", required = false, defaultValue = "") String position,
			HttpServletRequest request) {
		return new ResponseEntity<>(workerService.findAll(query, position), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crea un Trabajador")	
	@PostMapping(value = "/worker")
	public ResponseEntity<?> saveWorker(@RequestBody Worker worker, HttpServletRequest request){
		HashMap<String, Object> result = new HashMap<>();
		Boolean isSuccess = workerService.insert(worker);
		String msg = isSuccess ? "Se ha registrado correctamente." : "Ha ocurrido un error, intente en otro momento.";
		
		result.put("success", isSuccess);
		result.put("message", msg);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualiza datos de un Trabajador")
	@PutMapping(value = "/worker")
	public ResponseEntity<?> updateWorker (@RequestBody Worker worker, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Boolean isSuccess = workerService.update(worker);
		String msg = isSuccess ? "Se ha actualizado los datos correctamente." : "Ha ocurrido un error, intente en otro momento.";
		
		result.put("success", isSuccess);
		result.put("message", msg);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un Trabajador")
	@DeleteMapping(value = "/worker/{idWorker}")
	public ResponseEntity<?> deleteWorker (@PathVariable(value = "idWorker") Integer idWorker, HttpServletRequest request){
		HashMap<String, Object> result = new HashMap<>();
		Boolean isSuccess = workerService.delete(idWorker);
		String msg = isSuccess ? "Se ha eliminado el registro correctamente." : "Ha ocurrido un error, intente en otro momento.";
		
		result.put("success", isSuccess);
		result.put("message", msg);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
