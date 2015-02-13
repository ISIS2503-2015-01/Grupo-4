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
    public Long id;
    public Integer descripcion;
    public Integer intensidad;
    public Integer lugar;
    public Integer clima;
    public Boolean hidratacion;

    public ActividadFisica(Integer descripcion, Integer intensidad,Integer lugar,Integer clima,Boolean hidratacion) {
        this.descripcion = descripcion;
        this.intensidad = intensidad;
        this.lugar=lugar;
        this.clima=clima;
        this.hidratacion=hidratacion;
    }
}
