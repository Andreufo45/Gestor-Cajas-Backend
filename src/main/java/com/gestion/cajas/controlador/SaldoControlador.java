package com.gestion.cajas.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.cajas.excepciones.ResourcesNotFoundException;
import com.gestion.cajas.modelo.Saldo;
import com.gestion.cajas.repositorio.SaldoRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class SaldoControlador {
	
	private final SaldoRepositorio repositorio;

    @Autowired
    public SaldoControlador(SaldoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

	@GetMapping("/saldos")
	public List<Saldo> listarSaldos() {
		return repositorio.findAll();
	}
	
	@PostMapping("/saldos")
	public Saldo guardarSaldo(@RequestBody Saldo saldo) {
		return repositorio.save(saldo);
	}
	
	@GetMapping("/saldos/{id}")
	public ResponseEntity<Saldo> obtenerSaldoPorID(@PathVariable Long id){
		return ResponseEntity.ok(repositorio.findById(id).orElseThrow(() -> new ResourcesNotFoundException("No esiste ningún saldo con id:" + id)));
	}
	
	@PostMapping(value="/saldos/search")
    public ResponseEntity<List<Saldo>> findByDateAndIdCaja(@RequestParam("fecha") String fromDate, @RequestParam("idCaja") String idCaja) {
		if (!fromDate.isEmpty()) {
			if(idCaja!=null&&!idCaja.equals("undefined")&&!idCaja.isEmpty()&&!idCaja.equals("null")) {
				
				return ResponseEntity.ok(repositorio.findByDateAndIdCaja(asSqlDate(fromDate), Long.parseLong(idCaja)));
			} else {
				return ResponseEntity.ok(repositorio.findByDate(asSqlDate(fromDate)));
				
			}
		}
		return null;
    }
	
	@DeleteMapping("/saldos/{id}")
	public ResponseEntity<Boolean> eliminarSaldo(@PathVariable Long id){
		Saldo saldo = repositorio.findById(id).orElseThrow(() -> new ResourcesNotFoundException("No existe el empleado con el ID : " + id));
		repositorio.delete(saldo);
		
		return ResponseEntity.ok(Boolean.TRUE);
    }
	
	public java.sql.Date asSqlDate(String date) {
        try {
            java.util.Date dt = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(date);
            return new java.sql.Date(dt.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
