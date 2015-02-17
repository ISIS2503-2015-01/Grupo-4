package models;

/**
 * Created by Clau on 10/02/2015.
 */



import javax.persistence.*;

@Entity
public class ActividadFisica {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer descripcion;

    private Integer intensidad;

    private Integer lugar;

    private Integer clima;

    private Boolean hidratacion;

    private Long episodioId;

    public ActividadFisica() { }

    public static ActividadFisica create(Integer descripcion, Integer intensidad, Integer lugar, Integer clima, Boolean hidratacion, Long episodioId) {
        ActividadFisica a = new ActividadFisica();
        a.descripcion = descripcion;
        a.intensidad = intensidad;
        a.lugar = lugar;
        a.clima = clima;
        a.hidratacion = hidratacion;
        a.episodioId = episodioId;
        return a;
    }

    public Long getId() {

        return id;
    }

    public Long getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(Long episodioId) {
        this.episodioId = episodioId;
    }

    public Integer getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Integer descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(Integer intensidad) {
        this.intensidad = intensidad;
    }

    public Integer getLugar() {
        return lugar;
    }

    public void setLugar(Integer lugar) {
        this.lugar = lugar;
    }

    public Integer getClima() {
        return clima;
    }

    public void setClima(Integer clima) {
        this.clima = clima;
    }

    public Boolean getHidratacion() {
        return hidratacion;
    }

    public void setHidratacion(Boolean hidratacion) {
        this.hidratacion = hidratacion;
    }
}
