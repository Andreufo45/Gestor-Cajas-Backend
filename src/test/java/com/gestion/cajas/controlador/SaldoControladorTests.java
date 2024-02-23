package com.gestion.cajas.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gestion.cajas.excepciones.ResourcesNotFoundException;
import com.gestion.cajas.modelo.Saldo;
import com.gestion.cajas.repositorio.SaldoRepositorio;


@ExtendWith(MockitoExtension.class)
class SaldoControladorTests {

	@Mock
	private SaldoRepositorio repositorio;
	@InjectMocks
	SaldoControlador controlador;
	
	@Test
    void findByDateAndIdCajaTest() {
		Saldo saldoEsperado1 = new Saldo(1L, 1L, controlador.asSqlDate("2024-02-22"), 1000l, "A");
		Saldo saldoEsperado2 = new Saldo(2L, 1L, controlador.asSqlDate("2024-02-23"), 1500l, "A");
		Saldo saldoEsperado3 = new Saldo(3L, 2L, controlador.asSqlDate("2024-02-22"), 2000l, "A");
		Saldo saldoEsperado4 = new Saldo(4L, 2L, controlador.asSqlDate("2024-02-23"), 2500l, "A");
		List<Saldo> listaSaldosEsperadosSoloFecha = new ArrayList<Saldo>();
		listaSaldosEsperadosSoloFecha.add(saldoEsperado1);
		listaSaldosEsperadosSoloFecha.add(saldoEsperado2);
		listaSaldosEsperadosSoloFecha.add(saldoEsperado3);
		listaSaldosEsperadosSoloFecha.add(saldoEsperado4);
		List<Saldo> listaSaldosEsperadosFechaYCaja = new ArrayList<Saldo>();
		listaSaldosEsperadosFechaYCaja.add(saldoEsperado1);
		listaSaldosEsperadosFechaYCaja.add(saldoEsperado2);
		String fromDate = "2024-02-22";
		Mockito.when(repositorio.findByDate(controlador.asSqlDate(fromDate))).thenReturn(listaSaldosEsperadosSoloFecha);
		Mockito.when(repositorio.findByDateAndIdCaja(controlador.asSqlDate(fromDate), 1L)).thenReturn(listaSaldosEsperadosFechaYCaja);
		Assertions.assertEquals(listaSaldosEsperadosSoloFecha, controlador.findByDateAndIdCaja(fromDate, null).getBody());
		Assertions.assertEquals(listaSaldosEsperadosSoloFecha, controlador.findByDateAndIdCaja(fromDate, "undefined").getBody());
		Assertions.assertEquals(listaSaldosEsperadosSoloFecha, controlador.findByDateAndIdCaja(fromDate, "null").getBody());
		Assertions.assertEquals(listaSaldosEsperadosSoloFecha, controlador.findByDateAndIdCaja(fromDate, "").getBody());
		Assertions.assertEquals(listaSaldosEsperadosFechaYCaja, controlador.findByDateAndIdCaja(fromDate, "1").getBody());
		Assertions.assertEquals(null, controlador.findByDateAndIdCaja("", ""));
    }
	
	@Test
	void listarSaldosTest() {
		Saldo saldoEsperado1 = new Saldo(1L, 1L, controlador.asSqlDate("2024-02-22"), 1000l, "A");
		Saldo saldoEsperado2 = new Saldo(2L, 1L, controlador.asSqlDate("2024-02-23"), 1500l, "A");
		Saldo saldoEsperado3 = new Saldo(3L, 2L, controlador.asSqlDate("2024-02-22"), 2000l, "A");
		Saldo saldoEsperado4 = new Saldo(4L, 2L, controlador.asSqlDate("2024-02-23"), 2500l, "A");
		List<Saldo> listaSaldosEsperada = new ArrayList<Saldo>();
		listaSaldosEsperada.add(saldoEsperado1);
		listaSaldosEsperada.add(saldoEsperado2);
		listaSaldosEsperada.add(saldoEsperado3);
		listaSaldosEsperada.add(saldoEsperado4);
		Mockito.when(repositorio.findAll()).thenReturn(listaSaldosEsperada);
		Assertions.assertEquals(listaSaldosEsperada, controlador.listarSaldos());
	}
	
	@Test
	void guardarSaldoTest() {
		Saldo saldoEsperado1 = new Saldo(1L, 1L, controlador.asSqlDate("2024-02-22"), 1000l, "A");
		Mockito.when(repositorio.save(saldoEsperado1)).thenReturn(saldoEsperado1);
		Assertions.assertEquals(saldoEsperado1, controlador.guardarSaldo(saldoEsperado1));
	}
	
	@Test
	void obtenerSaldoPorIDTest() {
		Saldo saldoEsperado1 = new Saldo(1L, 1L, controlador.asSqlDate("2024-02-22"), 1000l, "A");
		Mockito.when(repositorio.findById(1L)).thenReturn(Optional.of(saldoEsperado1));
		Assertions.assertEquals(saldoEsperado1, controlador.obtenerSaldoPorID(1L).getBody());
	}
	
	@Test
	void eliminarSaldoTest() {
		Saldo saldoEsperado1 = new Saldo(1L, 1L, controlador.asSqlDate("2024-02-22"), 1000l, "A");
		Mockito.when(repositorio.findById(1L)).thenReturn(Optional.of(saldoEsperado1));
		Mockito.when(repositorio.findById(null)).thenThrow(new ResourcesNotFoundException("No existe el empleado con el ID : " + null));
		Assertions.assertEquals(Boolean.TRUE, controlador.eliminarSaldo(1L).getBody());
		Assertions.assertThrows(ResourcesNotFoundException.class, ()->{
			controlador.eliminarSaldo(null);
		});
	}
	
	@Test
	void asSqlDateTest() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			controlador.asSqlDate("");
		});
	}
}
