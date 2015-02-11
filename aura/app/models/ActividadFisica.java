package models;

/**
 * Created by Clau on 10/02/2015.
 */

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActividadFisica extends Model {

    @Id
    public String id;

    public String contents;

}
