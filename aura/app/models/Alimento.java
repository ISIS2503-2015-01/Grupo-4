package models;

/**
 * Created by Clau on 10/02/2015.
 */

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alimento extends Model
{
    @Id
    public Long id;
    public String nombre;
    public Integer cantidad;

    public Alimento (String nombre, Integer cantidad){
        this.nombre=nombre;
        this.cantidad=cantidad;
    }
}
