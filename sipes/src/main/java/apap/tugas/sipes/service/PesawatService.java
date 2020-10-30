package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import java.util.List;

import java.util.Optional;
public interface PesawatService {
    void addPesawat(PesawatModel pesawat);
    List<PesawatModel> getPesawatList();
    Optional<PesawatModel> getPesawatByIdPesawat(Long id);
    PesawatModel updatePesawat(PesawatModel pesawat);
    void deletePesawat(Long id);
}
