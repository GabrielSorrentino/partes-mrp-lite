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
import unpsjb.labprog.backend.business.services.ClienteService;
import unpsjb.labprog.backend.model.Cliente;

@RestController
@RequestMapping("clientes")
public class ClientePresenter {
    @Autowired
    ClienteService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> findAll(){
        return Response.ok(service.findAll());
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable("id") int id){
        Cliente cl = service.findById(id);
        return (cl != null)
            ? Response.ok(cl)
            : Response.notFound("No se encontró al cliente con ID " + id + ".");
    }

    @RequestMapping(value = "/cuit/{cuit}", method = RequestMethod.GET)
    public ResponseEntity<Object> findByCuit(@PathVariable("cuit") long cuit){
        Cliente cl = service.findByCuit(cuit);
        return (cl != null) ? Response.ok(cl) :
        Response.notFound("No se encontró al cliente con CUIT " + cuit + ".");
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Object> findByPage(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size
    ){
        return Response.ok(service.findByPage(page, size));
    }

    @RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
    public ResponseEntity<Object> search(@PathVariable("term") String term){
        return Response.ok(service.search(term));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Cliente cl){
        if (cl.getId() <= 0){
            return Response.error(cl,
                "Debe especificar un ID válido para poder modificar un cliente.");
        }
        // Intenta guardar al cliente
        try {
            return Response.ok(service.save(cl));
        } catch (DataIntegrityViolationException e) {
            // CUIT duplicado
            return Response.dbError("Error. Ese es el CUIT de un cliente ya existente.");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Cliente cl){
        // Verifica que el cliente con dicho ID no exista ya.
        if (cl.getId() != 0){
            return Response.error(cl,
                "Está intentando crear un cliente. Éste no puede tener un ID ya definido.");
        }
        // Intenta guardar al cliente
        try{
            Cliente savedCliente = service.save(cl);
            String message = String.format("Cliente %s (%d) registrado correctamente",
                savedCliente.getRazonSocial(), savedCliente.getCuit());
            return Response.ok(savedCliente, message);
        } catch(DataIntegrityViolationException e){
            // CUIT duplicado
            return Response.dbError("Error. Ese es el CUIT de un cliente ya existente.");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") int id){
        service.delete(id);
        return Response.ok("Cliente " + id + " eliminado con éxito.");
    }
}
