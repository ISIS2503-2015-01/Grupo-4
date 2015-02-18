package controllers;

import models.Episodio;
import models.Medicamento;
import org.json.simple.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.persistence.Query;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
        int c=0;
        while(e.hasNext())
        {
            Episodio ep=e.next();
            Integer i=ep.getIntensidad();
            Integer h=ep.getHorasSuenio();
            Date f=ep.getFechaPublicacion();
            jsonPuntos.put("intensidad"+c,i);
            jsonPuntos.put("horas"+c,h);
            jsonPuntos.put("fecha"+c,f.toString());
            c++;
        }
        return Results.ok(Json.toJson(jsonPuntos));
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
}
