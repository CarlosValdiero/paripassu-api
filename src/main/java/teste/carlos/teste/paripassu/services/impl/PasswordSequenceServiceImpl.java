package teste.carlos.teste.paripassu.services.impl;

import java.util.Arrays;
import java.util.List;

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

@Service
public class PasswordSequenceServiceImpl implements PasswordSequenceService {

	private PasswordSequenceProvider passwordSequenceProvider;
	private CurrentPasswordProvider currentPasswordProvider;

	@Autowired
	public PasswordSequenceServiceImpl(PasswordSequenceProvider passwordSequenceProvider,
			CurrentPasswordProvider currentPasswordProvider) {
		this.passwordSequenceProvider = passwordSequenceProvider;
		this.currentPasswordProvider = currentPasswordProvider;
	}

	@Override
	public PasswordDTO getCurrentPassword() {
		CurrentPassword currentPassword = currentPasswordProvider.findAll().stream().findFirst()
				.orElse(new CurrentPassword());

		return new PasswordDTO(currentPassword);
	}
	
	@Override
	@Transactional
	public PasswordDTO getNewPassword(PasswordType passwordType) {
		PasswordSequence passwordSequence = passwordSequenceProvider.findByPasswordType(passwordType)
				.orElse(new PasswordSequence(passwordType));
		
		passwordSequence.nextPassword();
		passwordSequenceProvider.save(passwordSequence);
		return new PasswordDTO(passwordSequence.getUuid(), passwordSequence.getPasswordType(), passwordSequence.getValue());		
	}

	@Override
	@Transactional
	public void setNextCurrentPassword() {
		List<PasswordSequence> passwordSequences = passwordSequenceProvider.findByValueMoreThanCurrentValue();
		currentPasswordProvider.deleteAll();

		if (passwordSequences.isEmpty()) {
			return;
		}

		PasswordSequence passwordSequence = passwordSequences.stream()
				.filter(sequence -> sequence.getPasswordType().equals(PasswordType.P)).findFirst()
				.orElse(passwordSequences.get(0));

		passwordSequence.nextCurrentPassword();
		CurrentPassword currentPassword = new CurrentPassword(passwordSequence);

		passwordSequenceProvider.save(passwordSequence);
		currentPasswordProvider.save(currentPassword);
	}

	@Override
	@Transactional
	public void resetPasswords() {
		passwordSequenceProvider.deleteAll();
		currentPasswordProvider.deleteAll();

		List<PasswordSequence> passwordSequences = Arrays.asList(new PasswordSequence(PasswordType.N),
				new PasswordSequence(PasswordType.P));
		passwordSequenceProvider.saveAll(passwordSequences);
	}
}