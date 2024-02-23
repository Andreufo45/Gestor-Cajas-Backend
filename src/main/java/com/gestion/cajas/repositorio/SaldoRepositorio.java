package com.gestion.cajas.repositorio;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.cajas.modelo.Saldo;

@Repository
public interface SaldoRepositorio extends JpaRepository<Saldo, Long>{
	
	@Query("Select s from Saldo s where fecha = ?1")
	List<Saldo> findByDate(Date fecha);

	@Query("Select s from Saldo s where fecha = ?1 and idCaja = ?2")
	List<Saldo> findByDateAndIdCaja(Date fecha, Long idCaja);

}
