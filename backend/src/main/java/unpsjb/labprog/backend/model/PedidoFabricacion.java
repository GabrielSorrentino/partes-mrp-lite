package unpsjb.labprog.backend.model;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pedidos_fabricacion")
public class PedidoFabricacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;

    @Column(name = "fecha_entrega", nullable = false)
    private Date fechaEntrega;

    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pedido_fabricacion_id", nullable = true) // una orden de trabajo puede estar en una planificación y no en un pedido de fabricación
    private Collection<OrdenTrabajo> ordenesTrabajo;
}
