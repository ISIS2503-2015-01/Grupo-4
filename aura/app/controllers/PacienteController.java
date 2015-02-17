package controllers;


import com.fasterxml.jackson.databind.JsonNode;
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


public class PacienteController extends Controller {

    // CRUD
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result create() {

        JsonNode j = Controller.request().body().asJson();

        String email=j.findPath("email").asText();
        String password = j.findPath("password").asText();
        Long docIdentidad = Long.parseLong(j.findPath("docIdentidad").asText());
        String name = j.findPath("nombre").asText();
        String fechaN = j.findPath("fechaN").asText();
        Integer genero = Integer.parseInt(j.findPath("genero").asText());


        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;


        try {
            fecha = formatoDelTexto.parse(fechaN);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            Paciente p = Paciente.create(email, password, docIdentidad, name, fecha, genero);
            JPA.em().persist(p);

        } catch (Exception e) {
            e.printStackTrace();
            return Results.ok("Error al crear el paciente");
        }

        return Results.created();
    }

    @Transactional
    public static Result delete(Long id){
        Paciente p = JPA.em().find(Paciente.class, id);
        JPA.em().remove(p);
        return Results.ok();
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();

        String email=j.findPath("email").asText();
        String password = j.findPath("password").asText();
        String name = j.findPath("nombre").asText();
        String fechaN = j.findPath("fechaN").asText();
        Integer genero = Integer.parseInt(j.findPath("genero").asText());


        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;


        try {
            fecha = formatoDelTexto.parse(fechaN);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Paciente p = JPA.em().getReference(Paciente.class, id);
        p.setEmail(email);
        p.setNombre(name);
        p.setPassword(password);
        p.setGenero(genero);
        p.setNombre(name);

        return Results.created();
    }

    @Transactional
    public static Result getOne(Long id) {
        Paciente p = JPA.em().getReference(Paciente.class, id);
        Hibernate.initialize(Paciente.class);
        return Results.ok(Json.toJson(p));
    }

    @Transactional
    public static Result getAll() {
        Query query = JPA.em().createQuery("SELECT p FROM Paciente p");
        Collection<Paciente> pacientes = query.getResultList();
        return Results.ok(Json.toJson(pacientes));
    }

    public static Result registrarEpisodioEscrito(Long idUrl, Date fechaPublicacion, int intensidad, int horasSuenio, boolean suenioRegular, int lugar, boolean episodioEstreCercano, Long pacienteID) {
        Episodio.create(idUrl, fechaPublicacion, intensidad,  horasSuenio, suenioRegular, lugar,  episodioEstreCercano,  pacienteID);
        return Results.created();
    }


}
