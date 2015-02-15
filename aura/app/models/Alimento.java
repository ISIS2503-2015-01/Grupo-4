package models;

/**
 * Created by Clau on 10/02/2015.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alimento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;
    public String nombre;
    public Integer cantidad;

    public Alimento() {

    }

    public Alimento (String nombre, Integer cantidad){
        this.nombre=nombre;
        this.cantidad=cantidad;
    }
}
