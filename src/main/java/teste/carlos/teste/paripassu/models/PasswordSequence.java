package teste.carlos.teste.paripassu.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import teste.carlos.teste.paripassu.enums.PasswordType;

@Entity
public class PasswordSequence {
	
	@Id
	private UUID uuid;
	private PasswordType passwordType;
	private Integer value;
	private Integer currentValue;
	
	private static final int INITIAL_VALUE = 0;
	
	public PasswordSequence() {}
	
	public PasswordSequence( PasswordType passwordType) {
		this.uuid = UUID.randomUUID();
		this.passwordType = passwordType;
		this.value = INITIAL_VALUE;
		this.currentValue = INITIAL_VALUE;
	}
	
	public boolean hasNextCurrentPassword() {
		return !value.equals(currentValue);
	}
	
	public Integer nextCurrentPassword() {
		if(currentValue.equals(value)) {
			return null;
		}
		
		currentValue = currentValue + 1;
		return currentValue;
	}
	
	public Integer nextPassword() {
		value = value + 1;
		return value; 
	}

	public UUID getUuid() {
		return uuid;
	}

	public PasswordType getPasswordType() {
		return passwordType;
	}

	public Integer getValue() {
		return value;
	}

	public Integer getCurrentValue() {
		return currentValue;
	}

}
