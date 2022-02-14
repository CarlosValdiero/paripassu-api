package teste.carlos.teste.paripassu.services;

import teste.carlos.teste.paripassu.dtos.PasswordDTO;
import teste.carlos.teste.paripassu.enums.PasswordType;

public interface PasswordSequenceService {

	PasswordDTO getCurrentPassword();

	PasswordDTO getNewPassword(PasswordType passwordType) throws Exception;

	void resetPasswords();

	void setNextCurrentPassword();

}
