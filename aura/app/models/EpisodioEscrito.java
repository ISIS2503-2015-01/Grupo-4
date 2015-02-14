package models;

/**
 * Created by Clau on 10/02/2015.
 */

import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.*;

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
    private Long id;

    @Constraints.Required
    private Date fechaPublicacion;

    @Constraints.Required
    private int intensidad;

    private Double horasSuenio;

    private boolean suenioRegular;

    private int localizacion;

    private boolean episodioEstresCercano;

    public EpisodioEscrito() {

    }

    public EpisodioEscrito(int intensidad, double horasSuenio, boolean suenioRegular, int localizacion, boolean episodioEstres) {
        this.fechaPublicacion = new Date();
        this.intensidad = intensidad;
        this.horasSuenio = horasSuenio;
        this.suenioRegular = suenioRegular;
        this.localizacion = localizacion;
        this.episodioEstresCercano = episodioEstres;
    }

    public static Finder<Long, EpisodioEscrito> find = new Finder(
            Long.class, EpisodioEscrito.class
    );

    public static List<EpisodioEscrito> all() {
        return find.all();
    }

    public static void create(EpisodioEscrito e) {
        e.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public void setHorasSuenio(Double horasSuenio) {
        this.horasSuenio = horasSuenio;
    }

    public void setSuenioRegular(boolean suenioRegular) {
        this.suenioRegular = suenioRegular;
    }

    public void setLocalizacion(int localizacion) {
        this.localizacion = localizacion;
    }

    public void setEpisodioEstresCercano(boolean episodioEstresCercano) {
        this.episodioEstresCercano = episodioEstresCercano;
    }

    public Date getFechaPublicacion() {

        return fechaPublicacion;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public Double getHorasSuenio() {
        return horasSuenio;
    }

    public Long getId() {
        return id;
    }

    public boolean isSuenioRegular() {
        return suenioRegular;
    }

    public int getLocalizacion() {
        return localizacion;
    }

    public boolean isEpisodioEstresCercano() {
        return episodioEstresCercano;
    }
}
