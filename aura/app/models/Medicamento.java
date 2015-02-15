package models;

/**
 * Created by Clau on 10/02/2015.
 */


import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Medicamento extends Model {
    @Id
    public Long id;
}
