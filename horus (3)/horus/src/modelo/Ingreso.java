package modelo;

import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Ingreso {
    private int id;
    private String codIngreso;
    private Date fechaIngreso;
    private Double monto;
    private String descripcion;
    private int idMetodoPago;
    private int idEstado;
    private int idFuente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        this.id = id;
    }

    public String getCodIngreso() {
        return codIngreso;
    }

    public void setCodIngreso(String codIngreso) {
        if (codIngreso == null || codIngreso.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de ingreso no puede ser nulo o vacío.");
        }
        this.codIngreso = codIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        if (fechaIngreso == null) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser nula.");
        }
        this.fechaIngreso = fechaIngreso;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        if (monto == null || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser un número positivo.");
        }
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
        }
        this.descripcion = descripcion;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        if (idMetodoPago <= 0) {
            throw new IllegalArgumentException("El ID del método de pago debe ser un número positivo.");
        }
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        if (idEstado <= 0) {
            throw new IllegalArgumentException("El ID del estado debe ser un número positivo.");
        }
        this.idEstado = idEstado;
    }

    public int getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(int idFuente) {
        if (idFuente <= 0) {
            throw new IllegalArgumentException("El ID de la fuente debe ser un número positivo.");
        }
        this.idFuente = idFuente;
    }
}
