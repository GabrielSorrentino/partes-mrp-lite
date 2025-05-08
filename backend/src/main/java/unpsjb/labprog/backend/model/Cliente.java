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
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @Column(nullable=false, unique=true)
    private long cuit;

    // Getters
    public int getId(){
        return id;
    }

    public String getRazonSocial(){
        return razonSocial;
    }

    public long getCuit(){
        return cuit;
    }
}
