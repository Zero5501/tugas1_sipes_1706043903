package apap.tugas.sipes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.TipeDb;

@Service
@Transactional
public class TipeServiceImpl implements TipeService {
    @Autowired
    TipeDb tipeDb;

    @Override
    public List<TipeModel> getTipeList(){
        return tipeDb.findAllByOrderByIdTipeDesc();
    }
}
