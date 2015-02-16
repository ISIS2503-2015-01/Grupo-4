package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by scvalencia on 2/16/15.
 */
public class Episodio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long idUrl;

    private Date fechaPublicacion;

    private int intensidad;

    private Double horasSuenio;

    private boolean suenioRegular;

    private int lugar;

    private boolean episodioEstreCercano;

    private int pacienteID;

    //@ManyToOne
    //@JoinColumn(name="idPerson", referencedColumnName="id")
    //public Paciente paciente;

    public Episodio() {

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

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }
}
