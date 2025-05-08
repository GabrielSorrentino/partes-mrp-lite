package unpsjb.labprog.backend.business.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import unpsjb.labprog.backend.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>, PagingAndSortingRepository<Cliente, Integer>{
    @Query("SELECT c FROM Cliente c WHERE c.cuit = ?1")
    Optional<Cliente> findByCuit(long cuit);

    @Query("SELECT c FROM Cliente c WHERE UPPER(c.razonSocial) LIKE UPPER(?1)")
    List<Cliente> search(String term);
}
