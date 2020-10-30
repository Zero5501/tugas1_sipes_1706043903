package apap.tugas.sipes.service;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService {
    @Autowired
    PesawatDb pesawatDb;

    @Override
    public void addPesawat(PesawatModel pesawat){
        pesawat.nomorGenerator();
        pesawatDb.save(pesawat);
    }

    @Override
    public List<PesawatModel> getPesawatList(){
        return pesawatDb.findAllByOrderByIdPesawatDesc();
    }

    @Override
    public Optional<PesawatModel> getPesawatByIdPesawat(Long id){
        return pesawatDb.findById(id);
    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat){
        pesawat.nomorGenerator();
        pesawatDb.save(pesawat);
        return pesawat;
    }

    @Override
    public void deletePesawat(Long id){
        pesawatDb.deleteById(id);
    }

    
}

