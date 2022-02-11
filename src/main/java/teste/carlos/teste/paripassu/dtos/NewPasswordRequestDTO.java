package teste.carlos.teste.paripassu.dtos;

import teste.carlos.teste.paripassu.enums.PasswordType;

public class NewPasswordRequestDTO {

	private PasswordType passwordType;

	public PasswordType getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(PasswordType passwordType) {
		this.passwordType = passwordType;
	}
	
}
