package teste.carlos.teste.paripassu.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import teste.carlos.teste.paripassu.enums.PasswordType;

public class PasswordSequenceTest {
	
	private static final int INITIAL_VALUE = 0;
	
	
	@Test
	@DisplayName("Expected to generate new sequence")
	public void generateNewSequence() {
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P);
		
		assertNotNull(passwordSequence.getUuid());
		assertEquals(PasswordType.P, passwordSequence.getPasswordType());
		assertEquals(INITIAL_VALUE, passwordSequence.getValue());
		assertEquals(INITIAL_VALUE, passwordSequence.getCurrentValue());
		
	}
	
	@Test
	@DisplayName("Expected to find new value")
	public void findNextValue() {
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P);
		Integer nextValue = INITIAL_VALUE + 1;
		
		assertEquals(nextValue, passwordSequence.nextPassword());
	}
	
	@Test
	@DisplayName("Expected to find new current value when less than value")
	public void findNextCurrentValueWhenLessThanValue() {
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P);
		Integer nextCurrentValue = INITIAL_VALUE + 1;
		passwordSequence.nextPassword();
		
		assertEquals(nextCurrentValue, passwordSequence.nextCurrentPassword());
	}
	
	@Test
	@DisplayName("Expected to find new current value equals null when equals value")
	public void findNextCurrentValueEqualsNullWhenEqualsValue() {
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P);
		
		assertNull( passwordSequence.nextCurrentPassword());
	}
	
	@Test
	@DisplayName("Expected false when current value equals value")
	public void ExpectedFalseWhenCurrentValueEqualsValue() {
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P);
		
		assertFalse( passwordSequence.hasNextCurrentPassword());
	}
	
	@Test
	@DisplayName("Expected true when current value less than value")
	public void ExpectedTrueWhenCurrentValueLessThanValue() {
		PasswordSequence passwordSequence = new PasswordSequence(PasswordType.P);
		passwordSequence.nextPassword();
		
		assertTrue( passwordSequence.hasNextCurrentPassword());
	}

}
