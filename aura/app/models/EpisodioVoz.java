package models;

/**
 * Created by Clau on 10/02/2015.
 */


import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EpisodioVoz extends Model {

    @Id
    public Long id;
    public Long idUrl;

    public EpisodioVoz(Long idUrl) {
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
}
