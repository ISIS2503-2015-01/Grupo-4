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

    private int horasTomadoAntes;

    private Long episodioId;

    public Medicamento() { }

    public static Medicamento create(String nombre, int horasTomadoAntes, Long episodioId) {
        Medicamento m = new Medicamento();
        m.nombre = nombre;
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

    public int getHorasTomadoAntes() {
        return horasTomadoAntes;
    }

    public void setHorasTomadoAntes(int horasTomadoAntes) {
        this.horasTomadoAntes = horasTomadoAntes;
    }

    public Long getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(Long episodioId) {
        this.episodioId = episodioId;
    }
}
