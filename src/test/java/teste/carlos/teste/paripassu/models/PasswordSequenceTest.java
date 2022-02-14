package teste.carlos.teste.paripassu.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import teste.carlos.teste.paripassu.enums.PasswordType;

public class PasswordSequenceTest {
	
	private static final int INITIAL_VALUE = 0;
	private PasswordSequence passwordSequence;
	
	@BeforeEach
	public void setup() {
		UUID sequenceCode = UUID.randomUUID();
		passwordSequence = new PasswordSequence(PasswordType.P, sequenceCode);
	}
	
	
	@Test
	@DisplayName("Expected to generate new sequence")
	public void generateNewSequence() {
		
		assertNotNull(passwordSequence.getUuid());
		assertEquals(PasswordType.P, passwordSequence.getPasswordType());
		assertEquals(INITIAL_VALUE, passwordSequence.getValue());
		assertEquals(INITIAL_VALUE, passwordSequence.getCurrentValue());
		
	}
	
	@Test
	@DisplayName("Expected to find new value")
	public void findNextValue() {
		Integer nextValue = INITIAL_VALUE + 1;
		
		assertEquals(nextValue, passwordSequence.nextPassword());
	}
	
	@Test
	@DisplayName("Expected to find new current value when less than value")
	public void findNextCurrentValueWhenLessThanValue() {
		Integer nextCurrentValue = INITIAL_VALUE + 1;
		passwordSequence.nextPassword();
		
		assertEquals(nextCurrentValue, passwordSequence.nextCurrentPassword());
	}
	
	@Test
	@DisplayName("Expected to find new current value equals null when equals value")
	public void findNextCurrentValueEqualsNullWhenEqualsValue() {
		assertNull( passwordSequence.nextCurrentPassword());
	}
	
	@Test
	@DisplayName("Expected false when current value equals value")
	public void ExpectedFalseWhenCurrentValueEqualsValue() {
		
		assertFalse( passwordSequence.hasNextCurrentPassword());
	}
	
	@Test
	@DisplayName("Expected true when current value less than value")
	public void ExpectedTrueWhenCurrentValueLessThanValue() {
		passwordSequence.nextPassword();
		
		assertTrue( passwordSequence.hasNextCurrentPassword());
	}

}
