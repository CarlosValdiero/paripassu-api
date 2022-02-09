package teste.carlos.teste.paripassu.utils;

import teste.carlos.teste.paripassu.enums.PasswordType;

public class PasswordUtil {
	
	public static String formatPassword(PasswordType passwordType, Integer value) {
		return passwordType.toString() + String.format("%04d" , value);
	}

}
