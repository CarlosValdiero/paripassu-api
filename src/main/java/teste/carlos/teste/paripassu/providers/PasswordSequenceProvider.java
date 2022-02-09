package teste.carlos.teste.paripassu.providers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import teste.carlos.teste.paripassu.enums.PasswordType;
import teste.carlos.teste.paripassu.models.PasswordSequence;

@Repository
public interface PasswordSequenceProvider extends JpaRepository<PasswordSequence, String> {
	
	Optional<PasswordSequence> findByPasswordType(PasswordType passwordType);
	
	@Query("SELECT p from PasswordSequence p where p.value > p.currentValue")
	List<PasswordSequence> findByValueMoreThanCurrentValue();
}
