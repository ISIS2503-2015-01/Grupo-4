package models;

/**
 * Created by Clau on 10/02/2015.
 */



import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class EpisodioVoz {

    @Id
    private Long id;

    private Date fechaPublicacion;

    private Long idUrl;

    private int pacienteID;


    public EpisodioVoz() {

    }

    public EpisodioVoz(Long id, Date fechaPublicacion, Long idUrl) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.idUrl = idUrl;
    }

    public Long getId() {
        return id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Long getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(Long idUrl) {
        this.idUrl = idUrl;
    }

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }
}
