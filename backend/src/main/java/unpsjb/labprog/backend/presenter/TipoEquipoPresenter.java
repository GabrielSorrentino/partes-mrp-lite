package unpsjb.labprog.backend.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unpsjb.labprog.backend.Response;
import unpsjb.labprog.backend.business.services.TipoEquipoService;
import unpsjb.labprog.backend.model.TipoEquipo;

@RestController
@RequestMapping("tipos-equipo")
public class TipoEquipoPresenter {
    @Autowired
    TipoEquipoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> findAll(){
        return Response.ok(service.findAll());
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        TipoEquipo te = service.findById(id);
        return (te != null)
            ? Response.ok(te)
            : Response.notFound("No se encontró al tipo de equipo con ID " + id + ".");
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Object> findByPage(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size
    ){
        return Response.ok(service.findByPage(page, size));
    }
        
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody TipoEquipo te){
        if (te.getId() <= 0){
            return Response.error(te,
                "Debe especificar un ID válido para poder modificar un tipo de equipo.");
        }
        // Intenta guardar al tipo de equipo
        try {
            return Response.ok(service.save(te));
        } catch (DataIntegrityViolationException e) {
            // nombre duplicado
            return Response.dbError("Error. Ese es el nombre de un tipo de equipo ya existente.");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody TipoEquipo te){
        // Verifica que el tipo de equipo con dicho ID no exista ya.
        if (te.getId() != 0){
            return Response.error(te,
                "Está intentando crear un tipo de equipo. Éste no puede tener un ID ya definido.");
        }
        // Intenta guardar al tipo de equipo
        try{
            TipoEquipo savedTipoEquipo = service.save(te);
            String message = String.format("Tipo de equipo %s registrado correctamente",
                savedTipoEquipo.getNombre());
            return Response.ok(savedTipoEquipo, message);
        } catch(DataIntegrityViolationException e){
            // nombre duplicado
            return Response.dbError("Error. Ese es el nombre de un tipo de equipo ya existente.");
        }
    }

    @RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
    public ResponseEntity<Object> search(@PathVariable("term") String term){
        return Response.ok(service.search(term));
    }
}
