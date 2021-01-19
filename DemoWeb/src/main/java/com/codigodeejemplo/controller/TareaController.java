package com.codigodeejemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codigodeejemplo.model.Tarea;

import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
@Controller
public class TareaController {
	private SimpleJdbcCall simpleJdbcCall;

	@GetMapping("/ListarTarea")
	public String greeting2(@RequestParam(name="name", required=false, defaultValue="World2") String name, Model model) {
		
		simpleJdbcCall.withProcedureName("SP_OBTENER_TAREAS");
		Map<String, Object> outMap = simpleJdbcCall.execute();
		Tarea tarea = new Tarea();
		tarea.setdn_identificador((int)outMap.get("DN_IDENTIFICADOR_O"));
		tarea.setDg_cadena((String)outMap.get("DG_CADENA_O"));
		String fechaStr = (String)outMap.get("DF_FECHA_CREACION_O");
		model.addAttribute("name2", name);
		return "greeting";
		
		/*
		 * 
		// Para parametros entrada MapSqlParameterSource
		
		//Date fechaConvertida =new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);  
		//tarea.setDf_fecha_creacion(fechaConvertida);
		
		if(outMap.get("DB_VIGENTE_O").toString() == "S") {
			tarea.setDb_vigente(true);
		}else {
			tarea.setDb_vigente(false);
		}
		return tarea;
		 * 
		 */
	}
	
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name2", name);
		return "greeting";
	}
}
