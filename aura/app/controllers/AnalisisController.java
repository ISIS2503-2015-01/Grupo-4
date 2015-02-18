package controllers;

import models.Episodio;
import org.json.simple.JSONObject;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Clau on 17/02/2015.
 */
public class AnalisisController extends Controller
{
    @Transactional
    public static Result createAnalisis(Long idP, String f1, String f2){
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
            jsonPuntos.put("intensidad"+c,i);
            jsonPuntos.put("horas"+c,h);
            c++;
        }
        return Results.ok(Json.toJson(jsonPuntos));
    }
}
