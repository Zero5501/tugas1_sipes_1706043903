package apap.tugas.sipes.service;
import apap.tugas.sipes.model.PenerbanganModel;
import java.util.List;

public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);
    PenerbanganModel getPenerbanganById(Long id);
    List<PenerbanganModel> getPenerbanganList();
    PenerbanganModel changePenerbangan(PenerbanganModel penerbangan);
    void deletePenerbangan(Long id);
}
