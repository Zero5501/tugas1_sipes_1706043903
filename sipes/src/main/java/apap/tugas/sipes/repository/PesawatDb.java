package apap.tugas.sipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.tugas.sipes.model.PesawatModel;
import java.util.Optional;
import java.util.List;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long> {
    Optional<PesawatModel> findById(Long idPesawat);

    List<PesawatModel> findAllByOrderByIdPesawatDesc();
}
