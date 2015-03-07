package models;

/**
 * Created by Clau on 19/02/2015.
 */
public class AnalisisIntensidadHoras
{
    public int intensidad;
    public String fecha;
    public int horas;
    public AnalisisIntensidadHoras(int intensidadn,String fechan,int horasn){
        intensidad=intensidadn;
        fecha=fechan;
        horas=horasn;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public int getHoras() {
        return horas;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIntensidad() {
        return intensidad;
    }
}
