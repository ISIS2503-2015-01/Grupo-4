package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Doctor;
import models.Episodio;
import models.Paciente;
import org.hibernate.Hibernate;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DoctorController extends Controller {

    //public static Long prueba = 0L;

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result create()
    {

        JsonNode j = Controller.request().body().asJson();
        //Para prueba crear
        //Random random = new Random();
        //long docIdentidad = Math.abs(random.nextLong()*1000000000);

        //Para prueba eliminar
        //Long docIdentidad = prueba;
        //prueba++;
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
        //Long docIdentidad = prueba;
        //prueba--;
        //Doctor d = JPA.em().find(Doctor.class, docIdentidad);
        Doctor d = JPA.em().getReference(Doctor.class, id);
        JPA.em().remove(d);
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
    public static Result getAllEpisodes(Long id) {
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.pacienteID = :id");
        query.setParameter("id", id);
        List<Episodio> episodios = query.getResultList();
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
    public static Result getOne(Long id) {

        Doctor p = JPA.em().getReference(Doctor.class, id);
        Hibernate.initialize(Doctor.class);
        return Results.ok(Json.toJson(p));
    }

    @Transactional

    public static Result getAll() {
        Query query = JPA.em().createQuery("SELECT d FROM Doctor d");
        Collection<Doctor> doctores = query.getResultList();
        return Results.ok(Json.toJson(doctores));
    }

    private static Date parseDate(String representation) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;

        try {
            fecha = formatoDelTexto.parse(representation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

}