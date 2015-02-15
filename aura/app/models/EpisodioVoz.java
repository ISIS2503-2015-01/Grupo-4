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
public class EpisodioVoz extends Model {

    @Id
    public Long id;
    public Date fechaPublicacion;
    public Long idUrl;
    public static Finder<Long,EpisodioVoz> find = new Finder(
            Long.class, EpisodioVoz.class
    );
    public EpisodioVoz(Long idUrl, Date f) {
        this.idUrl = idUrl;
        this.fechaPublicacion = f;
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
        return find.all();
    }

    public static void create(EpisodioVoz ev) {
        ev.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }
    public static EpisodioVoz buscarUnoId(Long id){
        return find.byId(id);
    }
}
