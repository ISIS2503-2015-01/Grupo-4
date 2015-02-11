package models;

/**
 * Created by Clau on 10/02/2015.
 */


import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EpisodioVoz extends Model {

    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;

    public EpisodioVoz(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

}
