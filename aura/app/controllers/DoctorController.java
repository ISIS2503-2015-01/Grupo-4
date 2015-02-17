package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Doctor;
import play.db.jpa.JPA;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.libs.Json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Query;
import java.util.*;

public class DoctorController extends Controller {


    @BodyParser.Of(BodyParser.Json.class)
    public static Result crearDoctor()
    {
        JsonNode j = Controller.request().body().asJson();

        Long docIdentidad = Long.parseLong(j.findPath("docIdentidad").asText());
        String nombre = j.findPath("nombre").asText();
        String email=j.findPath("Email").asText();
        String password = j.findPath("contrasenia").asText();
        Integer genero = Integer.parseInt(j.findPath("genero").asText());
        Integer especialidad = Integer.parseInt(j.findPath("especialidad").asText());
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        String fechaNacimientoString = j.findPath("fechaNacimiento").asText();
        Date fechaNacimiento =  null;
        try
        {
            fechaNacimiento = formatoDelTexto.parse(fechaNacimientoString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        try
        {

            Doctor d = Doctor.create(email, password, docIdentidad, nombre, fechaNacimiento, genero, especialidad);
            JPA.em().persist(d);

        } catch (Exception e) {
            e.printStackTrace();
            return Results.ok();
        }
        return Results.created();
    }

    public static Result eliminarDoctor(Long id)
    {
        Doctor d = JPA.em().find(Doctor.class, id);
        JPA.em().remove(d);
        return Results.ok();
    }

    public static Result actualizarDoctor()
    {
        JsonNode j = Controller.request().body().asJson();

        Long docIdentidad = Long.parseLong(j.findPath("docIdentidad").asText());
        String nombre = j.findPath("nombre").asText();
        String email=j.findPath("Email").asText();
        String password = j.findPath("contrasenia").asText();
        Integer genero = Integer.parseInt(j.findPath("genero").asText());
        Integer especialidad = Integer.parseInt(j.findPath("especialidad").asText());
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        String fechaNacimientoString = j.findPath("fechaNacimiento").asText();
        Date fechaNacimiento =  null;
        try
        {
            fechaNacimiento = formatoDelTexto.parse(fechaNacimientoString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }


        Doctor d = JPA.em().getReference(Doctor.class, docIdentidad);
        d.setdocIdentidad(docIdentidad);
        d.setNombre(nombre);
        d.setEmail(email);
        d.setPassword(password);
        d.setGenero(genero);
        d.setEspecialidad(especialidad);
        d.setFechaNacimiento(fechaNacimiento);

        return Results.ok();

    }

    public static Result verEpisodiosId(Long idPaciente)
    {
        //TODO-mirar diferencias entre episodios escritos y voz en DB
        Query query = JPA.em().createQuery("SELECT * FROM Episodios WHERE idPaciente = '"+idPaciente);
        Collection<EpisodioVoz> episodiosVoz = query.getResultList();
        return Results.ok(Json.toJson(episodiosVoz));
        //Collection<EpisodioEscrito> episodiosEscritos = query.getResultList();
        //return Results.ok(Json.toJson(episodiosEscritos));

    }

    public static Result verEpisodiosFecha(Long idPaciente,Date fechaInicial, Date fechaFinal)
    {
        //TODO-mirar diferencias entre episodios escritos y voz en DB
        Query query = JPA.em().createQuery("SELECT e FROM Episodios WHERE idPaciente = '"+idPaciente+" AND fecha >= "+fechaInicial+" AND fecha <= " + fechaFinal);
        Collection<EpisodioVoz> episodiosVoz = query.getResultList();
        return Results.ok(Json.toJson(episodiosVoz));
        ///Collection<EpisodioEscrito> episodiosEscritos = query.getResultList();
        //return Results.ok(Json.toJson(episodiosEscritos));
    }

    //Aca no se si hay alguna manera de sacar el id del episodio, o si lo buscamos por fecha y hora

    public static Result verEpisodioEscrito(Long idPaciente, Long idEpisodio)
    {
        //TODO-de lista de episodios, se selecciona. Se sabe el id del Episodio
        //TODO-mirar diferencias entre episodios escritos y voz en DB
        Query query = JPA.em().createQuery("SELECT * FROM Episodios WHERE id = "+idEpisodio);
        Collection<EpisodioEscrito> episodiosEscrito = query.getResultList();
        return Results.ok(Json.toJson(episodiosEscrito));
    }

    public static Result verEpisodioVoz(Long idPaciente, Long idEpisodio)
    {
        Query query = JPA.em().createQuery("SELECT * FROM Episodios WHERE id = "+idEpisodio);
        Collection<EpisodioVoz> episodiosVoz = query.getResultList();
        return Results.ok(Json.toJson(episodiosVoz));
    }


}
