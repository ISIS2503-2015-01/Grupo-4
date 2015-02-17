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

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result create()
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
            return Results.ok("Error al crear el doctor");
        }
        return Results.created();
    }
    
    @Transactional
    public static Result delete(Long id){
        Doctor d = JPA.em().find(Doctor.class, id);
        JPA.em().remove(p);
        return Results.ok();
    }
    
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result update(Long id) {
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

        Paciente p = JPA.em().getReference(Paciente.class, id);
        Doctor d = JPA.em().getReference(Doctor.class, docIdentidad);
        d.setdocIdentidad(docIdentidad);
        d.setNombre(nombre);
        d.setEmail(email);
        d.setPassword(password);
        d.setGenero(genero);
        d.setEspecialidad(especialidad);
        d.setFechaNacimiento(fechaNacimiento);

        return Results.created();
    }

    @Transactional
    public static Result getAll(Long id) {
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.pacienteID = :id");
        query.setParameter("id", id);
        List episodios = query.getResultList();
        return Results.ok(Json.toJson(episodios));
    }

    @Transactional
    public static Result getPerDates(Long idP, String f1, String f2) {
        Date d1 = parseDate(f1);
        Date d2 = parseDate(f2);
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.fechaPublicacion >= :d1 AND e.fechaPublicacion <= :d2 AND e.pacienteID = idP");
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        query.setParameter("idP", idP);
        Collection<Episodio> episodios = query.getResultList();
        return Results.ok(Json.toJson(episodios));
    }

    @Transactional
    public static Result getOne(long id1, long id2) {
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.pacienteID = :id1 and e.id = :id2");
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        Collection<Episodio> episodios = query.getResultList();
        return Results.ok(Json.toJson(episodios));
    }
    
    @Transactional
    public static Result getAll() {
        Query query = JPA.em().createQuery("SELECT d FROM Doctor d");
        Collection<Paciente> pacientes = query.getResultList();
        return Results.ok(Json.toJson(pacientes));
    }


}
