package models;

/**
 * Created by Clau on 10/02/2015.
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sintoma{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int sintoma;

    private Long episodioId;

    public Sintoma() { }

    public static Sintoma create(int sintoma, Long episodioId) {
        Sintoma s = new Sintoma();
        s.sintoma = sintoma;
        s.episodioId = episodioId;
        return s;
    }

    public Long getId() {
        return id;
    }

    public int getSintoma() {
        return sintoma;
    }

    public void setSintoma(int sintoma) {
        this.sintoma = sintoma;
    }

    public Long getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(Long episodioId) {
        this.episodioId = episodioId;
    }
}
