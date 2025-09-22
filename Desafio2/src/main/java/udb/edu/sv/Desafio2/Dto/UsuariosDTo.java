package udb.edu.sv.Desafio2.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuariosDTo {
    @NotBlank(message = "El nombre es un campo obligatorio")
    @Size(min = 6, max = 15, message = "El nombre debe de tener 15 caracteres como maximo")
    private String nombre;

    @NotBlank(message = "El apellido es un campo obligatorio")
    private String apellido;

    @NotBlank(message = "El email es un campo obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

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
}
