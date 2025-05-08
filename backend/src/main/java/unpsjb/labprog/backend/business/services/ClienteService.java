package unpsjb.labprog.backend.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.backend.business.repositories.ClienteRepository;
import unpsjb.labprog.backend.model.Cliente;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;

    public List<Cliente> findAll(){
        List<Cliente> result = new ArrayList<>();
        repository.findAll().forEach(c -> result.add(c));
        return result;
    }

    public Cliente findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Cliente findByCuit(long cuit){
        return repository.findByCuit(cuit).orElse(null);
    }

    @Transactional
    public Cliente save(Cliente c){
        return repository.save(c);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }

    public List<Cliente> search(String term){
        return repository.search("%" + term.toUpperCase() + "%");
    }

    public Page<Cliente> findByPage(int page, int size){
        return repository.findAll(PageRequest.of(page, size));
    }
}
