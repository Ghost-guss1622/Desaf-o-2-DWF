package udb.edu.sv.Desafio2.Dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SuscripcionesDTo {
    @NotBlank(message = "El nombre del plan es obligatorio.")
    private String nombre;

    @NotBlank(message = "El tipo de plan es obligatorio.")
    private String tipo;

    @NotNull(message = "La duración en meses es obligatoria.")
    private Integer duracionMeses;

    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a cero.")
    private BigDecimal precio;

    @NotBlank(message = "El estado del plan es obligatorio.")
    private String estado;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDuracionMeses() {
        return duracionMeses;
    }
    public void setDuracionMeses(Integer duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
