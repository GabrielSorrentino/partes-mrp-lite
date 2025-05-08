package unpsjb.labprog.backend.business.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import unpsjb.labprog.backend.model.TipoEquipo;

@Repository
public interface TipoEquipoRepository extends CrudRepository<TipoEquipo, Integer>, PagingAndSortingRepository<TipoEquipo, Integer>{
    @Query("SELECT t FROM TipoEquipo t WHERE UPPER(t.nombre) LIKE UPPER(?1)")
    List<TipoEquipo> search(String term);
}
