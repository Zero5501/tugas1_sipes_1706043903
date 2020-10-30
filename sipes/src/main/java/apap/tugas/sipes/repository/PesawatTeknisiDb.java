package apap.tugas.sipes.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.tugas.sipes.model.PesawatTeknisiModel;

@Repository
public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel, Long> {
    
}
