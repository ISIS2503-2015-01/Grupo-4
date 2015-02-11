package models;

/**
 * Created by Clau on 10/02/2015.
 */


import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class EpisodioVoz extends Model {

    public static final int INTENSIDAD_NIVEL1 = 1;
    public static final int INTENSIDAD_NIVEL2 = 2;
    public static final int INTENSIDAD_NIVEL3 = 3;
    public static final int INTENSIDAD_NIVEL4 = 4;
    public static final int INTENSIDAD_NIVEL5 = 5;


    public Long id;
    public Date fechaPublicacion;
    public int intensidad;
    public String password;
    public Date fechaNacimiento;


    public EpisodioVoz(String email, String password, String fullname) {

       
    }

}
