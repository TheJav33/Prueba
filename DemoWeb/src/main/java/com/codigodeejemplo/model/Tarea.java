package com.codigodeejemplo.model;

import java.sql.Date;

public class Tarea {

	private int dn_identificador;
	private String dg_cadena;
	private Date df_fecha_creacion;
	private boolean db_vigente;
	
	public long getdn_identificador() {
        return dn_identificador;
    }

    public void setdn_identificador(int dn_identificador) {
        this.dn_identificador = dn_identificador;
    }

	public String getDg_cadena() {
		return dg_cadena;
	}

	public void setDg_cadena(String dg_cadena) {
		this.dg_cadena = dg_cadena;
	}

	public Date getDf_fecha_creacion() {
		return df_fecha_creacion;
	}

	public void setDf_fecha_creacion(Date df_fecha_creacion) {
		this.df_fecha_creacion = df_fecha_creacion;
	}

	public boolean isDb_vigente() {
		return db_vigente;
	}

	public void setDb_vigente(boolean db_vigente) {
		this.db_vigente = db_vigente;
	}

}
