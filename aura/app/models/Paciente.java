package models;

/**
 * Created by Clau on 10/02/2015.
 */

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mindrot.jbcrypt.BCrypt;
import play.db.jpa.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paciente {

    public static final int MASCULINO = 1;
    public static final int FEMENINO = 1;

    @Id
    private Long docIdentidad;
    private String nombre;
    private String password;
    private Date fechaNacimiento;
    private String email;

    public int getGenero() {

        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public Long getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(Long docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int genero;

    public Paciente() {

    }

    public static Paciente create(String email, String password, Long docIdentidad,String nombre,Date fechaNacimiento, int genero) {
        // http://stackoverflow.com/questions/15657062/play-framework-2-best-way-to-store-password-hash-of-user
        Paciente p = new Paciente();
        p.docIdentidad = docIdentidad;
        p.nombre = nombre;
        p.password = BCrypt.hashpw(password, BCrypt.gensalt());
        p.fechaNacimiento = fechaNacimiento;
        p.email = email;
        return p;
    }

}
