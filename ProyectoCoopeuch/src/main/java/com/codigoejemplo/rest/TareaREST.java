package com.codigoejemplo.rest;

import org.springframework.web.bind.annotation.RestController;

import com.codigoejemplo.dao.TareaDAO;
import com.codigoejemplo.entity.Tarea;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("tareas")
public class TareaREST {

	@Autowired
	private TareaDAO tareaDAO;

	//Metodo para Listar TODAS las tareas almacenadas en la tabla 'Tarea' de la base de datos 
	@GetMapping
	public ResponseEntity<List<Tarea>> getTareas(){
		try {
			List<Tarea> tareas = tareaDAO.findAll();
			return ResponseEntity.ok(tareas);	
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	//Metodo para Listar por ID las tareas almacenadas en la tabla 'Tarea' de la base de datos 
	@RequestMapping(value="{tareaId}")
	public ResponseEntity<Tarea> getTareasPorId(@PathVariable("tareaId") Long tareaId){
		Optional<Tarea> optionalTareas = tareaDAO.findById(tareaId);
		try {
			if(optionalTareas.isPresent()) {
				return ResponseEntity.ok(optionalTareas.get());			
			}else {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	//Metodo para Crear por ID alguna tarea (Se pasan los parámetros excepto el ID que es autoincremental)
	@PostMapping
	public ResponseEntity<Tarea> crearTarea(@RequestBody Map<String, String> tarea) throws ParseException{
		Tarea nuevaTarea = new Tarea();
		try {
			//Validaciones por si faltan datos de entrada
			if(tarea.get("dg_CADENA") == null || 
					tarea.get("df_FECHA_CREACION") == null ||
					tarea.get("db_VIGENTE") == null) {
				return ResponseEntity.noContent().build();
				
			}
			
			boolean dbVigente = Boolean.parseBoolean(tarea.get("db_VIGENTE")); 
			nuevaTarea.setDG_CADENA(tarea.get("dg_CADENA").toString());
			nuevaTarea.setDB_VIGENTE(dbVigente);
			
			String sDate1=tarea.get("df_FECHA_CREACION");  
			Date fechaParseada=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
			nuevaTarea.setDF_FECHA_CREACION(fechaParseada);
			
			tareaDAO.save(nuevaTarea);
			return ResponseEntity.ok(nuevaTarea);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	//Metodo para Eliminar por ID alguna tarea
	@DeleteMapping(value= "{tareaId}")
	public ResponseEntity<Void> borrarTareaPorId(@PathVariable("tareaId") Long tareaId)
	{
		try {
			tareaDAO.deleteById(tareaId);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	//Metodo para Actualizar por ID alguna tarea
	@PutMapping
	public ResponseEntity<Tarea> actualizarTarea(@RequestBody Map<String, String> tarea) throws ParseException{
		
		try {
			Long dnIdentificador = Long.parseLong(tarea.get("dn_IDENTIFICADOR"));
			String dgCadena = tarea.get("dg_CADENA");
			String dfFechaCreacion = tarea.get("df_FECHA_CREACION");
			Boolean dbVigente =Boolean.parseBoolean(tarea.get("db_VIGENTE"));
			
			//Validacion de campos
			if(tarea.get("dn_IDENTIFICADOR") == null || tarea.get("dg_CADENA") == null || 
					tarea.get("df_FECHA_CREACION") == null ||
					tarea.get("db_VIGENTE") == null) {
				return ResponseEntity.noContent().build();
			}
			 
			Optional<Tarea> optionalTareas = tareaDAO.findById(dnIdentificador);
			if(optionalTareas.isPresent()) {
				Tarea actualizarTarea = optionalTareas.get();
				actualizarTarea.setDG_CADENA(tarea.get("dg_CADENA").toString());
				
				String sDate1=tarea.get("df_FECHA_CREACION");  
				Date fechaParseada=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
				
				actualizarTarea.setDF_FECHA_CREACION(fechaParseada);
				actualizarTarea.setDB_VIGENTE(dbVigente);
				tareaDAO.save(actualizarTarea);
				return ResponseEntity.ok(actualizarTarea);
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
