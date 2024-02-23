package com.gestion.cajas.modelo;

import java.sql.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gestion.cajas.repositorio.SaldoRepositorio;

class SaldoTests {
	

	@Test
    void testSaldoConstructor() {
		Saldo saldo = new Saldo(1L, 1L, new Date(new java.util.Date().getTime()), 1l, "A");
    	Assert.assertNotNull(saldo);
    }
	
	@Test
    void testSaldoGettersYSetters() {
		 Long a = 1L;
		 String b = "A";
		 Date c = new Date(new java.util.Date().getTime());
		 Saldo saldo = new Saldo();
		 saldo.setCantidad(a);
		 saldo.setDesc(b);
		 saldo.setFecha(c);
		 saldo.setId(a);
		 saldo.setIdCaja(a);
		 Assert.assertEquals(saldo.getCantidad(), a);
		 Assert.assertEquals(saldo.getIdCaja(), a);
		 Assert.assertEquals(saldo.getId(), a);
		 Assert.assertEquals(saldo.getDesc(), b);
		 Assert.assertEquals(saldo.getFecha(), c);
		 Assert.assertNotNull(saldo);
    }
	
}
