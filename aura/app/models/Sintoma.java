package models;

/**
 * Created by Clau on 10/02/2015.
 */


import javax.persistence.*;

@Entity
public class Sintoma{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int sintoma;

    private int episodioId;

    public Sintoma() { }

    public Long getId() {
        return id;
    }

    public int getSintoma() {
        return sintoma;
    }

    public void setSintoma(int sintoma) {
        this.sintoma = sintoma;
    }

    public int getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(int episodioId) {
        this.episodioId = episodioId;
    }
}
