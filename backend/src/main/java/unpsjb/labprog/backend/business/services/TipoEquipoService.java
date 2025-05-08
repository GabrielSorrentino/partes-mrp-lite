package unpsjb.labprog.backend.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import unpsjb.labprog.backend.business.repositories.TipoEquipoRepository;
import unpsjb.labprog.backend.model.TipoEquipo;

@Service
public class TipoEquipoService {
    @Autowired
    TipoEquipoRepository repository;

    public List<TipoEquipo> findAll(){
        List<TipoEquipo> result = new ArrayList<>();
        repository.findAll().forEach(c -> result.add(c));
        return result;
    }

    public TipoEquipo findById(int id){
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public TipoEquipo save(TipoEquipo c){
        return repository.save(c);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }

    public Page<TipoEquipo> findByPage(int page, int size){
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<TipoEquipo> search(String term){
        return repository.search("%" + term.toUpperCase() + "%");
    }
}
