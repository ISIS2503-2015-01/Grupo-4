package controllers;

import models.*;
import org.json.simple.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.persistence.Query;
import java.util.*;

/**
 * Created by Clau on 17/02/2015.
 */
public class AnalisisController extends Controller
{
    @Transactional
    public static Result createAnalisisIntensidadHoras(Long idP, String f1, String f2){
        JSONObject jsonPuntos=new JSONObject();
        System.out.println();
        Collection<Episodio>episodios=EpisodioController.getPerDatesAnalisis(idP, f1, f2);
       Iterator<Episodio> e= episodios.iterator();
        List<AnalisisIntensidadHoras> lista=new ArrayList<AnalisisIntensidadHoras>();
        while(e.hasNext())
        {
            Episodio ep=e.next();
            Integer i=ep.getIntensidad();
            Integer h=ep.getHorasSuenio();
            Date f=ep.getFechaPublicacion();
            AnalisisIntensidadHoras analisis=new AnalisisIntensidadHoras(i,f.toString(),h);
            lista.add(analisis);
        }

        System.out.println("-------------------------------------------------------- ");
        return ok(Json.toJson(lista)).as("application/json");

    }
    @Transactional
    public static Result createAnalisisIntensidadMedicamentos(Long idP, String f1, String f2){
        JSONObject jsonPuntos=new JSONObject();
        System.out.println();
        Collection<Episodio>episodios=EpisodioController.getPerDatesAnalisis(idP, f1, f2);
        Iterator<Episodio> e= episodios.iterator();
        int c=0;

        while(e.hasNext())
        {
            Episodio ep=e.next();
            Integer i=ep.getIntensidad();
            Long id=ep.getId();
            Query query = JPA.em().createQuery("SELECT a FROM Medicamento a WHERE a.episodioId = :id");
            query.setParameter("id", id);
            Collection<Medicamento> med = query.getResultList();
            Iterator<Medicamento> medi= med.iterator();
            while(medi.hasNext()) {
                Medicamento m=medi.next();
                jsonPuntos.put(""+i +"."+ c, m.getNombre());

                c++;
            }
        }
        return Results.ok(Json.toJson(jsonPuntos));
    }

    @Transactional
    public static Result createAnalisisIntensidadAlimentos(Long idP, String f1, String f2){
        JSONObject jsonPuntos=new JSONObject();
        System.out.println();
        Collection<Episodio>episodios=EpisodioController.getPerDatesAnalisis(idP, f1, f2);
        Iterator<Episodio> e= episodios.iterator();
        int c=0;

        while(e.hasNext())
        {
            Episodio ep=e.next();
            Integer i=ep.getIntensidad();
            Long id=ep.getId();
            Query query = JPA.em().createQuery("SELECT a FROM Alimento a WHERE a.episodioId = :id");
            query.setParameter("id", id);
            Collection<Alimento> med = query.getResultList();
            Iterator<Alimento> medi= med.iterator();
            while(medi.hasNext()) {
                Alimento m=medi.next();
                jsonPuntos.put(""+i +"."+ c+"-"+m.getCantidad(), m.getNombre());

                c++;
            }
        }
        return Results.ok(Json.toJson(jsonPuntos));
    }

    @Transactional
    public static Result createAnalisisIntensidadActividadFisica(Long idP, String f1, String f2){
        JSONObject jsonPuntos=new JSONObject();
        System.out.println();
        Collection<Episodio>episodios=EpisodioController.getPerDatesAnalisis(idP, f1, f2);
        Iterator<Episodio> e= episodios.iterator();
        int c=0;

        while(e.hasNext())
        {
            Episodio ep=e.next();
            Integer i=ep.getIntensidad();
            Long id=ep.getId();
            Query query = JPA.em().createQuery("SELECT a FROM ActividadFisica a WHERE a.episodioId = :id");
            query.setParameter("id", id);
            Collection<ActividadFisica> med = query.getResultList();
            Iterator<ActividadFisica> medi= med.iterator();
            while(medi.hasNext()) {
                ActividadFisica m=medi.next();
                jsonPuntos.put(""+i +"."+ c+"-"+m.getIntensidad(), m.getDescripcion());

                c++;
            }
        }
        return ok();

    }



}
