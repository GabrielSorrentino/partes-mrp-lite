package unpsjb.labprog.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String nombre;

    private int orden;

    private int tiempo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "tipo_equipo_id")
    private TipoEquipo tipoEquipo;
}
