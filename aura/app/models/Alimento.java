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

    private int episodioId;

    public Alimento() { }

    public Alimento(String nombre, Integer cantidad, int episodioId) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.episodioId = episodioId;
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

    public int getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(int episodioId) {
        this.episodioId = episodioId;
    }
}
