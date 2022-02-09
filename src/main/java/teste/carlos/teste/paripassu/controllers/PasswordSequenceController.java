package teste.carlos.teste.paripassu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import teste.carlos.teste.paripassu.dtos.PasswordDTO;
import teste.carlos.teste.paripassu.enums.PasswordType;
import teste.carlos.teste.paripassu.services.PasswordSequenceService;


@RestController
@RequestMapping("passwords")
public class PasswordSequenceController {
	
	private PasswordSequenceService passwordSequenceService;
	
	@Autowired
	public PasswordSequenceController(PasswordSequenceService passwordSequenceService) {
		this.passwordSequenceService = passwordSequenceService;
	}

	@GetMapping("/current")
    public ResponseEntity<Object> getCurrentPassword() {
		try {
			PasswordDTO passwordDTO = passwordSequenceService.getCurrentPassword();
	        return new ResponseEntity<Object>(passwordDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
	
	@PostMapping("/reset-sequence")
    public ResponseEntity<Object> resetPasswordSequece() {
		try {
			passwordSequenceService.resetPasswords();
	        return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
	
	@PostMapping("/new-password")
    public ResponseEntity<Object> newPassword(
    		@RequestParam() String passwordType) {
		try {
			PasswordDTO passwordDTO = passwordSequenceService.getNewPassword(PasswordType.valueOf(passwordType));
	        return new ResponseEntity<Object>(passwordDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
	
	@PostMapping("/next-password")
    public ResponseEntity<Object> setNextCurrentPassword() {
		try {
			passwordSequenceService.setNextCurrentPassword();
	        return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
	
}
