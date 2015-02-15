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
    public Long id;
    public Date fechaPublicacion;
    public Long idUrl;
    public EpisodioVoz(Long idUrl, Date f) {
        this.idUrl = idUrl;
        this.fechaPublicacion = f;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(Long idUrl) {
        this.idUrl = idUrl;
    }

    public static List<EpisodioVoz> all() {
        //TODO
        return null;
    }

    public static void create(EpisodioVoz ev) {
        //TODO
    }

    public static void delete(Long id) {
        // TODO
    }
    public static EpisodioVoz buscarUnoId(Long id){
        // TODO
        return null;
    }
}
