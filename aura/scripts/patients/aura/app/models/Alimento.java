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
    private Long id;

    private String nombre;

    private Integer cantidad;

    private Long episodioId;

    public Alimento() { }

    public static Alimento create(String nombre, Integer cantidad, Long episodioId) {
        Alimento a = new Alimento();
        a.nombre = nombre;
        a.cantidad = cantidad;
        a.episodioId = episodioId;
        return a;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(Long episodioId) {
        this.episodioId = episodioId;
    }
}
