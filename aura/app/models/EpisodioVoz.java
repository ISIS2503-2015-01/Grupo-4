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

    public Long id;
    public Date fechaPublicacion;
    public Long code;

}
