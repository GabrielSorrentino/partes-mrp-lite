package unpsjb.labprog.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tipos_equipo")
public class TipoEquipo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(unique=true, nullable=false)
    private String nombre;

    //Getters
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }
}
