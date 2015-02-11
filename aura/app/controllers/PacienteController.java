package controllers;

import javax.xml.transform.Result;
import java.util.Date;

/**
 * Created by Clau on 10/02/2015.
 */
public class PacienteController
{
    public static Result crearPaciente(String nombre, Long id, String contrasenia, Date fechaNacimiento,String email,String foto, Integer genero, String eps){
        return null;
    }

    public static Result eliminarPaciente(Long id){
        return null;
    }

    public static Result actualizarPaciente(String nombre, Long id, String contrasenia, Date fechaNacimiento,String email,String foto, Integer genero, String eps){
    return null;
    }

    public static Result reportarEpisodioVoz(String urlSonido, Date fecha, Integer intensidad, Integer hora){
        return null;
    }

    public static Result reportarEpisodioEscrito(Date fecha, Integer intensidad, Integer hora,Integer horasSuenio,Integer suenioRegular, Boolean sufrioEstresAntes, Integer localizacion){
        return null;
    }

    public static Result verEpisodiosEscritos(){
        return null;
    }

    public static Result verEpisodiosVoz(){
        return null;
    }
}
