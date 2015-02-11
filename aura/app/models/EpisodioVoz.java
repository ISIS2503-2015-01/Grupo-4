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

    public Long docIdentidad;
    public String nombre;
    public String password;
    public Date fechaNacimiento;
    

    public EpisodioVoz(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

}
