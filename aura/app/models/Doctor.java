package models;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Doctor{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public static Long id;

    public static final int MASCULINO = 0;
    public static final int FEMENINO = 1;

    //Especialidades
    public static final int CARDIOLOGO = 0;

    private Long docIdentidad;
    private String nombre;
    private String email;
    private String password;
    private Date fechaNacimiento;
    private int genero;
    private int especialidad;


    public Doctor()
    {

    }
    public static Doctor create(String emailP, String passwordP, Long docIdentidadP,String nombreP, Date fechaNacimientoP, int generoP, int especialidadP)
    {
        // http://stackoverflow.com/questions/15657062/play-framework-2-best-way-to-store-password-hash-of-user
        Doctor d  = new Doctor();
        d.id = id;
        d.docIdentidad = docIdentidadP;
        d.nombre = nombreP;
        d.password = BCrypt.hashpw(passwordP, BCrypt.gensalt());
        d.fechaNacimiento = fechaNacimientoP;
        d.email = emailP;
        d.genero = generoP;
        d.especialidad = especialidadP;
        return d;
    }

    public Long getdocIdentidad() {
        return docIdentidad;
    }
    public void setdocIdentidad(Long docIdentidadP) {
        this.docIdentidad = docIdentidadP;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombreP) {
        this.nombre = nombreP;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String emailP) {
        this.email = emailP;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String passwordP) {
        this.password = passwordP;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimientoP) {
        this.fechaNacimiento = fechaNacimientoP;
    }

    public int getGenero() {
        return genero;
    }
    public void setGenero(int generoP) {
        this.genero = generoP;
    }

    public int getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(int especialidadP) {
        this.especialidad = especialidadP;
    }
}

