package com.gestion.cajas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GestorCajasBackendApplicationTests {
	
	@Test
	void mainTest() {
		Assertions.assertDoesNotThrow(()->{
			String[] args = {"--spring.output.ansi.enabled=always"};
			GestorCajasBackendApplication.main(args);
		}); 
	}
	
}
