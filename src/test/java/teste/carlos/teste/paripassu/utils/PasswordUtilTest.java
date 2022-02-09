package teste.carlos.teste.paripassu.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import teste.carlos.teste.paripassu.enums.PasswordType;

public class PasswordUtilTest {

	@Test
	@DisplayName("Expected to format password with type and four digits")
	public void ExpectedFormatPassword() {
		String expectedPassword = "P0001";
		
		assertEquals(expectedPassword, PasswordUtil.formatPassword(PasswordType.P, 1));
	}
}
