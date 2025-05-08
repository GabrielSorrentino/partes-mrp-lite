package unpsjb.labprog.backend.model;

import java.sql.Time;

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
@Table(name = "planificaciones")
public class Planificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private Time inicio;

    private Time fin;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id")
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = true)
    private Equipo equipo;
}
