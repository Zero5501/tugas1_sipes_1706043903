package apap.tugas.sipes.service;
import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){
        penerbanganDb.save(penerbangan);
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long id){
        return penerbanganDb.findById(id).get();
    }

    @Override
    public PenerbanganModel changePenerbangan(PenerbanganModel penerbangan){
        penerbanganDb.save(penerbangan);
        return penerbangan;
    }

    @Override
    public void deletePenerbangan(Long id){
        penerbanganDb.deleteById(id);
    }

    @Override
    public List<PenerbanganModel> getPenerbanganList(){
        return penerbanganDb.findAllByOrderByIdPenerbanganDesc();
    }
}
