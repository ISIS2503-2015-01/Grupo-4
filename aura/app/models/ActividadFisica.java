package models;

/**
 * Created by Clau on 10/02/2015.
 */



import javax.persistence.*;

@Entity
public class ActividadFisica {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;
    public Integer descripcion;
    public Integer intensidad;
    public Integer lugar;
    public Integer clima;
    public Boolean hidratacion;

    public ActividadFisica() {

    }

    public ActividadFisica(Integer descripcion, Integer intensidad,Integer lugar,Integer clima,Boolean hidratacion) {
        this.descripcion = descripcion;
        this.intensidad = intensidad;
        this.lugar=lugar;
        this.clima=clima;
        this.hidratacion=hidratacion;
    }
}
