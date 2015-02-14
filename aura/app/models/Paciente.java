package models;

/**
 * Created by Clau on 10/02/2015.
 */


import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Paciente extends Model {

    public static final int MASCULINO = 1;
    public static final int FEMENINO = 1;

    @Id
    public Long docIdentidad;
    public String nombre;
    public String password;
    public Date fechaNacimiento;
    public String email;
    public int genero;
    public int eps;
    public Medicamento medicamentos;



    public Paciente(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
