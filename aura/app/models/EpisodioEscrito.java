package models;

/**
 * Created by Clau on 10/02/2015.
 */

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class EpisodioEscrito extends Model {

    public static final int INTENSIDAD_NIVEL1 = 1;
    public static final int INTENSIDAD_NIVEL2 = 2;
    public static final int INTENSIDAD_NIVEL3 = 3;
    public static final int INTENSIDAD_NIVEL4 = 4;
    public static final int INTENSIDAD_NIVEL5 = 5;

    public static final int LUGAR_OSCURO = 1;
    public static final int LUGAR_ABIERTO_SOLEADO = 2;
    public static final int LUGAR_RUIDOSO = 3;
    public static final int LUGAR_CON_OLORES_FUERTES= 4;


    @Id
    public Long id;


    public Date fechaPublicacion;


    public int intensidad;

    public Double horasSuenio;

    public boolean suenioRegular;

    public int lugar;

    public boolean episodioEstreCercano;

    public static Finder<Long, EpisodioEscrito> find = new Finder(
            Long.class, EpisodioEscrito.class
    );

    public EpisodioEscrito() {

    }

    public EpisodioEscrito(Date fechaPublicacion, int intensidad, Double horasSuenio, boolean suenioRegular, int lugar, boolean episodioEstreCercano) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.intensidad = intensidad;
        this.horasSuenio = horasSuenio;
        this.suenioRegular = suenioRegular;
        this.lugar = lugar;
        this.episodioEstreCercano = episodioEstreCercano;
    }

    public static List<EpisodioEscrito> all() {
        return find.all();
    }

    public static void create(EpisodioEscrito task) {
        task.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public Double getHorasSuenio() {
        return horasSuenio;
    }

    public void setHorasSuenio(Double horasSuenio) {
        this.horasSuenio = horasSuenio;
    }

    public boolean isSuenioRegular() {
        return suenioRegular;
    }

    public void setSuenioRegular(boolean suenioRegular) {
        this.suenioRegular = suenioRegular;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public boolean isEpisodioEstreCercano() {
        return episodioEstreCercano;
    }

    public void setEpisodioEstreCercano(boolean episodioEstreCercano) {
        this.episodioEstreCercano = episodioEstreCercano;
    }
}
