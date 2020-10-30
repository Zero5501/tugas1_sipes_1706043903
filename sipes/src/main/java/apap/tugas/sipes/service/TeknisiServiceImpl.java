package apap.tugas.sipes.service;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.TeknisiDb;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TeknisiServiceImpl implements TeknisiService {
    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public List<TeknisiModel> getTeknisiList(){
        return teknisiDb.findAllByOrderByIdTeknisiDesc();
    }
}
