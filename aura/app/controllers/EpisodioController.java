package controllers;

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
