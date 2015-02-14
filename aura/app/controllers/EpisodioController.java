package controllers;

import models.EpisodioEscrito;
import org.codehaus.jackson.JsonNode;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Results;

import javax.xml.transform.Result;
import java.util.Date;

/**
 * Created by Clau on 10/02/2015.
 */
public class EpisodioController
{
    public static Result reportarAlarma(Long idPaciente){
        return null;
    }
    public static Result reportarAnalisis(Long idPaciente){
        return null;
    }
    public static Result crearEpisodioVoz(String urlSonido, Date fecha, Integer intensidad, Integer hora){
        return null;
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result crearEpisodioEscrito(){
        com.fasterxml.jackson.databind.JsonNode jsonNode = Controller.request().body().asJson();
        String url = jsonNode.findPath("fecha").asText();
        EpisodioEscrito ee = null;
        System.out.print(url);
        //ee.save();
        return Results.created();
    }

    public static Result crearEpisodioEscrito(Date fecha, Integer intensidad, Integer hora,Integer horasSuenio,Integer suenioRegular, Boolean sufrioEstresAntes, Integer localizacion){
    return null;
    }
    public static Result eliminarEpisodioVoz(Long id){return null;}
    public static Result eliminarEpisodioEscrito(Long id){return null;}
    public static Result actualizarEpisodioVoz(String urlSonido, Date fecha, Integer intensidad, Integer hora){
        return null;
    }

    public static Result actualizarEpisodioEscrito(Date fecha, Integer intensidad, Integer hora,Integer horasSuenio,Integer suenioRegular, Boolean sufrioEstresAntes, Integer localizacion){
        return null;
    }
}
