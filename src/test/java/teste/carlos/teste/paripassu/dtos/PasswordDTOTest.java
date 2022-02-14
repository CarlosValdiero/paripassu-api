package teste.carlos.teste.paripassu.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import teste.carlos.teste.paripassu.enums.PasswordType;
import teste.carlos.teste.paripassu.models.CurrentPassword;
import teste.carlos.teste.paripassu.models.PasswordSequence;


public class PasswordDTOTest {
	
	private final String DEFAULT_VALUE = "_____";

	@Test
	@DisplayName("Expected new PasswordDTO with default value when Current Password not exists.")
	public void ExpectedNewPasswordDTOWithNullValuesWhenCurrentPasswordIsNull() {
		PasswordDTO passwordDTO = new PasswordDTO();
		
		assertNull(passwordDTO.getUuidSequence());
		assertEquals(DEFAULT_VALUE, passwordDTO.getValue());
	}
	
	@Test
	@DisplayName("Expected new PasswordDTO when Current Password is not null and value more than 0")
	public void ExpectedNewPasswordDTOWhenCurrentPasswordIsNotNullAndValueMoreThan0() {
		UUID sequence = UUID.randomUUID();
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P, sequence);
		passwordSequence.nextPassword();
		passwordSequence.nextCurrentPassword();
		CurrentPassword currentPassword = new CurrentPassword(passwordSequence);
		PasswordDTO passwordDTO = new PasswordDTO(currentPassword);
		
		assertNotNull(passwordDTO.getUuidSequence());
		assertNotNull(passwordDTO.getValue());
	}
	
	@Test
	@DisplayName("Expected new PasswordDTO when Current Password is not null and value less than 1")
	public void ExpectedNewPasswordDTOWhenCurrentPasswordIsNotNullAndValueLessThan1() {
		UUID sequence = UUID.randomUUID();
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P, sequence);
		CurrentPassword currentPassword = new CurrentPassword(passwordSequence);
		PasswordDTO passwordDTO = new PasswordDTO(currentPassword);
		
		assertNull(passwordDTO.getUuidSequence());
		assertEquals(DEFAULT_VALUE,passwordDTO.getValue());
	}
	
	@Test
	@DisplayName("Expected new PasswordDTO with value more than 0")
	public void ExpectedNewPasswordDTOWithValueMoreThan0() {
		PasswordDTO passwordDTO = new PasswordDTO(UUID.randomUUID(), PasswordType.P, 1);
		
		assertNotNull(passwordDTO.getUuidSequence());
		assertNotNull(passwordDTO.getValue());
	}
	
	@Test
	@DisplayName("Expected new PasswordDTO with value less than 1")
	public void ExpectedNewPasswordDTOWithValueLessThan1() {
		PasswordDTO passwordDTO = new PasswordDTO(UUID.randomUUID(), PasswordType.P, 0);
		
		assertNotNull(passwordDTO.getUuidSequence());
		assertEquals(DEFAULT_VALUE,passwordDTO.getValue());
	}
}
