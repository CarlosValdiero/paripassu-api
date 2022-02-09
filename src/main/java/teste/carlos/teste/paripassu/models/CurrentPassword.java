package teste.carlos.teste.paripassu.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import teste.carlos.teste.paripassu.enums.PasswordType;

@Entity
public class CurrentPassword {

	@Id
	private UUID uuidSequence;
	private PasswordType passwordType;
	private Integer value;
	
	public CurrentPassword() {}

	public CurrentPassword(PasswordSequence passwordSequence) {
		this.uuidSequence = passwordSequence.getUuid();
		this.passwordType = passwordSequence.getPasswordType();
		this.value = passwordSequence.getCurrentValue();
	}

	public UUID getUuidSequence() {
		return uuidSequence;
	}

	public void setUuidSequence(UUID uuidSequence) {
		this.uuidSequence = uuidSequence;
	}

	public PasswordType getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(PasswordType passwordType) {
		this.passwordType = passwordType;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
