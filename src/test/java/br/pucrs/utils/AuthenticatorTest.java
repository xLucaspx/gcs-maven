package br.pucrs.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthenticatorTest {
	Authenticator authenticator = new Authenticator();

	@Test
	@DisplayName("Deve retornar true com credenciais de acesso válidas")
	void authCenario1() {
		assertTrue(authenticator.auth("admin", "admin"));
	}

	@Test
	@DisplayName("Deve retornar false com nome de usuário incorreto")
	void authCenario2() {
		assertFalse(authenticator.auth("", "admin"));
	}

	@Test
	@DisplayName("Deve retornar false com senha incorreta")
	void authCenario4() {
		assertFalse(authenticator.auth("admin", "123"));
	}

	@Test
	@DisplayName("Deve retornar false com nome de usuário nulo")
	void authCenario5() {
		assertFalse(authenticator.auth(null, "admin"));
	}

	@Test
	@DisplayName("Deve retornar false com senha nula")
	void authCenario6() {
		assertFalse(authenticator.auth("admin", null));
	}
}
