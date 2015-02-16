package models;

/**
 * Created by Clau on 10/02/2015.
 */



import javax.persistence.*;

@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String dosis;

    private int horasTomadoAntes;

    private int episodioId;

    public Medicamento() { }

    public static Medicamento create(String nombre, String dosis, int horasTomadoAntes, int episodioId) {
        Medicamento m = new Medicamento();
        m.nombre = nombre;
        m.dosis = dosis;
        m.horasTomadoAntes = horasTomadoAntes;
        m.episodioId = episodioId;
        return m;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public int getHorasTomadoAntes() {
        return horasTomadoAntes;
    }

    public void setHorasTomadoAntes(int horasTomadoAntes) {
        this.horasTomadoAntes = horasTomadoAntes;
    }

    public int getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(int episodioId) {
        this.episodioId = episodioId;
    }
}
