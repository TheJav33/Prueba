package com.codigoejemplo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tarea")
public class Tarea {


	public Long getDN_IDENTIFICADOR() {
		return DN_IDENTIFICADOR;
	}

	public void setDN_IDENTIFICADOR(Long dN_IDENTIFICADOR) {
		DN_IDENTIFICADOR = dN_IDENTIFICADOR;
	}

	public String getDG_CADENA() {
		return DG_CADENA;
	}

	public void setDG_CADENA(String dG_CADENA) {
		DG_CADENA = dG_CADENA;
	}

	public Date getDF_FECHA_CREACION() {
		return DF_FECHA_CREACION;
	}

	public void setDF_FECHA_CREACION(Date dF_FECHA_CREACION) {
		DF_FECHA_CREACION = dF_FECHA_CREACION;
	}

	public String getDB_VIGENTE() {
		return DB_VIGENTE;
	}

	public void setDB_VIGENTE(String dB_VIGENTE) {
		DB_VIGENTE = dB_VIGENTE;
	}

	@Id
	@Column(name="DN_IDENTIFICADOR")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long DN_IDENTIFICADOR;
	
	@Column(name="DG_CADENA",nullable=false,length=30)
	private String DG_CADENA;
	
	@Column(name="DF_FECHA_CREACION",nullable=false,length=30)
	private Date DF_FECHA_CREACION;
	
	@Column(name="DB_VIGENTE",nullable=false,length=30)
	private String DB_VIGENTE;
	
	
	
}
