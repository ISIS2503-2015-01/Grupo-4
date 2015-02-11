package controllers;

import play.mvc.Controller;

import javax.xml.transform.Result;
import java.util.Date;

/**
 * Created by Clau on 10/02/2015.
 */
public class DoctorController extends Controller {
    public static Result crearDoctor(String nombre, Long id, String contrasenia, Date fechaNacimiento,String email,String foto, Integer genero, String especialidad, String hospital, String fotoHospital)
    {
        return null;
    }

    public static Result eliminarDoctor(Long id){
        return null;
    }

    public static Result actualizarDoctor(String nombre, Long id, String contrasenia, Date fechaNacimiento,String email,String foto, Integer genero,String especialidad, String hospital, String fotoHospital){
        return null;
    }

    public static Result verEpisodiosId(Long idPaciente){
        return null;
    }

    public static Result verEpisodiosFecha(Long idPaciente,Date fechaInicial, Date fechaFinal){
        return null;
    }

    //Aca no se si hay alguna manera de sacar el id del episodio, o si lo buscamos por fecha y hora

    public static Result verEpisodioEscrito(Long idPaciente, Long idEpisodio){
        return null;
    }

    public static Result verEpisodioVoz(Long idPaciente, Long idEpisodio){
        return null;
    }


}
