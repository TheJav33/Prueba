package com.codigoejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codigoejemplo.entity.Tarea;

public interface TareaDAO extends JpaRepository<Tarea, Long> {

}
