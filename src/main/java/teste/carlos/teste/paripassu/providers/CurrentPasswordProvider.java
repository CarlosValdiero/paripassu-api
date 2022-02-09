package teste.carlos.teste.paripassu.providers;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teste.carlos.teste.paripassu.models.CurrentPassword;

@Repository
public interface CurrentPasswordProvider extends JpaRepository<CurrentPassword, UUID>{

}
