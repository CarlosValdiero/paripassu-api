package teste.carlos.teste.paripassu.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teste.carlos.teste.paripassu.dtos.PasswordDTO;
import teste.carlos.teste.paripassu.enums.PasswordType;
import teste.carlos.teste.paripassu.models.CurrentPassword;
import teste.carlos.teste.paripassu.models.PasswordSequence;
import teste.carlos.teste.paripassu.providers.CurrentPasswordProvider;
import teste.carlos.teste.paripassu.providers.PasswordSequenceProvider;
import teste.carlos.teste.paripassu.services.PasswordSequenceService;
import teste.carlos.teste.paripassu.services.WebSocketService;

@Service
public class PasswordSequenceServiceImpl implements PasswordSequenceService {

	private PasswordSequenceProvider passwordSequenceProvider;
	private CurrentPasswordProvider currentPasswordProvider;
	private WebSocketService webSocketService;

	@Autowired
	public PasswordSequenceServiceImpl(PasswordSequenceProvider passwordSequenceProvider,
			CurrentPasswordProvider currentPasswordProvider, WebSocketService webSocketService) {
		this.passwordSequenceProvider = passwordSequenceProvider;
		this.currentPasswordProvider = currentPasswordProvider;
		this.webSocketService = webSocketService;
	}

	@Override
	public PasswordDTO getCurrentPassword() {
		Optional<CurrentPassword> currentPassword = currentPasswordProvider
				.findAll().stream().findFirst();
		
		if(currentPassword.isPresent()) {
			return new PasswordDTO(currentPassword.get());
		}
		
		return new PasswordDTO();
	}
	
	@Override
	@Transactional
	public PasswordDTO getNewPassword(PasswordType passwordType) throws Exception {
		PasswordSequence passwordSequence = passwordSequenceProvider.findByPasswordType(passwordType)
				.orElseThrow(() -> new Exception("Erro ao buscar nova senha. Entre em contato com atendimento."));
		
		passwordSequence.nextPassword();
		passwordSequenceProvider.save(passwordSequence);
		return new PasswordDTO(passwordSequence.getSequenceCode(), passwordSequence.getPasswordType(), passwordSequence.getValue());		
	}

	@Override
	@Transactional
	public void setNextCurrentPassword() {
		List<PasswordSequence> passwordSequences = passwordSequenceProvider.findByValueMoreThanCurrentValue();
		currentPasswordProvider.deleteAll();

		CurrentPassword currentPassword;
		if ( passwordSequences.size() > 0) {
			
			PasswordSequence passwordSequence = passwordSequences.stream()
					.filter(sequence -> sequence.getPasswordType().equals(PasswordType.P)).findFirst()
					.orElse(passwordSequences.get(0));
	
			passwordSequence.nextCurrentPassword();
			currentPassword = new CurrentPassword(passwordSequence);
	
			passwordSequenceProvider.save(passwordSequence);
			currentPasswordProvider.save(currentPassword);
		
		} else {
			currentPassword = new CurrentPassword();
		}
		
		webSocketService.sendMessage("/password/current", new PasswordDTO(currentPassword));
	}

	@Override
	@Transactional
	public void resetPasswords() {
		passwordSequenceProvider.deleteAll();
		currentPasswordProvider.deleteAll();

		UUID sequenceCode = UUID.randomUUID();
		List<PasswordSequence> passwordSequences = Arrays.asList(new PasswordSequence(PasswordType.N, sequenceCode),
				new PasswordSequence(PasswordType.P, sequenceCode));
		
		passwordSequenceProvider.saveAll(passwordSequences);
		
		webSocketService.sendMessage("/password/current", new PasswordDTO());
	}
}
