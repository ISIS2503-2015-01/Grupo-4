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

    private int episodioId;

    public ActividadFisica() { }

    public ActividadFisica(Integer descripcion, Integer intensidad, Integer lugar, Integer clima, Boolean hidratacion, int episodioId) {
        this.descripcion = descripcion;
        this.intensidad = intensidad;
        this.lugar = lugar;
        this.clima = clima;
        this.hidratacion = hidratacion;
        this.episodioId = episodioId;
    }

    public Long getId() {

        return id;
    }

    public int getEpisodioId() {
        return episodioId;
    }

    public void setEpisodioId(int episodioId) {
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
