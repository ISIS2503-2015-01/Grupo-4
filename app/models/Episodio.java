package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by scvalencia on 2/16/15.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Episodio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long idUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;

    private int intensidad;

    private int horasSuenio;

    private boolean suenioRegular;

    private int lugar;

    private boolean episodioEstreCercano;

    private Long pacienteID;

    //@ManyToOne
    //@JoinColumn(name="idPerson", referencedColumnName="id")
    //public Paciente paciente;

    public Episodio() {

    }

    public static Episodio create(Long idUrl, Date fechaPublicacion,  int intensidad, int horasSuenio, boolean suenioRegular, int lugar, boolean episodioEstreCercano, Long pacienteID) {
        Episodio e = new Episodio();

        e.idUrl = idUrl;
        e.fechaPublicacion = fechaPublicacion;
        e.intensidad = intensidad;
        e.horasSuenio = horasSuenio;
        e.suenioRegular = suenioRegular;
        e.lugar = lugar;
        e.episodioEstreCercano = episodioEstreCercano;
        e.pacienteID = pacienteID;
        return e;
    }

    public Long getId() {
        return id;
    }

    public Long getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(Long idUrl) {
        this.idUrl = idUrl;
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

    public int getHorasSuenio() {
        return horasSuenio;
    }

    public void setHorasSuenio(int horasSuenio) {
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

    public Long getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(Long pacienteID) {
        this.pacienteID = pacienteID;
    }
}
