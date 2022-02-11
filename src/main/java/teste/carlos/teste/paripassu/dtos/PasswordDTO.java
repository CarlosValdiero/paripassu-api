package teste.carlos.teste.paripassu.dtos;

import java.util.Objects;
import java.util.UUID;

import teste.carlos.teste.paripassu.enums.PasswordType;
import teste.carlos.teste.paripassu.models.CurrentPassword;
import teste.carlos.teste.paripassu.utils.PasswordUtil;

public class PasswordDTO {

	private String uuidSequence;
	private String value;
	
	public PasswordDTO() {
		this(null);
	}

	public PasswordDTO(CurrentPassword currentPassword) {
		if (Objects.nonNull(currentPassword) && isValidValue(currentPassword.getValue())) {
			this.uuidSequence = currentPassword.getUuidSequence().toString();
			this.value = PasswordUtil.formatPassword(currentPassword.getPasswordType(), currentPassword.getValue());
		} else {
			this.value = "_____";
		}
	}
	
	private boolean isValidValue(Integer value) {
		return Objects.nonNull(value) && value > 0;
	}

	public PasswordDTO(UUID uuidSequence, PasswordType passwordType, Integer value) {
		this.uuidSequence = uuidSequence.toString();
		setValue(passwordType, value);
	}
	
	private void setValue( PasswordType passwordType, Integer value) {
		if(isValidValue(value)) {
			this.value = PasswordUtil.formatPassword(passwordType, value);			
		}
	}

	public String getUuidSequence() {
		return uuidSequence;
	}

	public String getValue() {
		return value;
	}
}
