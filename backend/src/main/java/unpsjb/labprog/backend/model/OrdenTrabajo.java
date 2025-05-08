package unpsjb.labprog.backend.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private int numero;

    @OneToMany (mappedBy = "ordenTrabajo")
    private Collection<Planificacion> planificaciones;
}
