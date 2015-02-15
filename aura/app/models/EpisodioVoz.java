package models;

/**
 * Created by Clau on 10/02/2015.
 */


import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EpisodioVoz extends Model {

    @Id
    public Long id;
    public Date fechaPublicacion;
    public int intensidad;

}
