package udb.edu.sv.Desafio2.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.sql.Date;
import java.time.LocalDate;

public class UsuariosDTo {
    @NotBlank(message = "El nombre es un campo obligatorio")
    @Size(min = 2, max = 15, message = "El nombre debe de tener 15 caracteres como maximo")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "No se admiten símbolos o caracteres especiales")
    private String nombre;

    @NotBlank(message = "El apellido es un campo obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "No se admiten símbolos o caracteres especiales")
    private String apellido;

    @NotBlank(message = "El email es un campo obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento no puede ser en el futuro")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
