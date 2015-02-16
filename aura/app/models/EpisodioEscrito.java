package models;

/**
 * Created by Clau on 10/02/2015.
 */

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class EpisodioEscrito {

    private static final int INTENSIDAD_NIVEL1 = 1;

    private static final int INTENSIDAD_NIVEL2 = 2;

    private static final int INTENSIDAD_NIVEL3 = 3;

    private static final int INTENSIDAD_NIVEL4 = 4;

    private static final int INTENSIDAD_NIVEL5 = 5;

    private static final int LUGAR_OSCURO = 1;

    private static final int LUGAR_ABIERTO_SOLEADO = 2;

    private static final int LUGAR_RUIDOSO = 3;

    private static final int LUGAR_CON_OLORES_FUERTES= 4;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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

    public EpisodioEscrito() {

    }

    public EpisodioEscrito(Date fechaPublicacion, int intensidad, Double horasSuenio, boolean suenioRegular, int lugar, boolean episodioEstreCercano, int pacienteID) {
        this.fechaPublicacion = fechaPublicacion;
        this.intensidad = intensidad;
        this.horasSuenio = horasSuenio;
        this.suenioRegular = suenioRegular;
        this.lugar = lugar;
        this.episodioEstreCercano = episodioEstreCercano;
        this.pacienteID = pacienteID;
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

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }
}
