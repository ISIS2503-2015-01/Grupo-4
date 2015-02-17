package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result registrarEpisodio() {
        System.out.println("----------------------------");
        JsonNode j = Controller.request().body().asJson();

        System.out.println("++++++++++++----++++++++");
        Long idUrl=Long.parseLong(j.findPath("idUrl").asText());

        String fechaPublicacion=j.findPath("fechaPublicacion").asText();
        int intensidad=Integer.parseInt(j.findPath("intensidad").asText());
        int horasSuenio =Integer.parseInt(j.findPath("horasSuenio").asText());
        boolean suenioRegular =j.findPath("suenioRegular").asText().equals("1");
        int lugar =Integer.parseInt(j.findPath("lugar").asText());
        boolean episodioEstreCercano =j.findPath("episodioEstreCercano").asText().equals("1");
        Long pacienteID=Long.parseLong(j.findPath("pacienteID").asText());


        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;


        try {
            fecha = formatoDelTexto.parse(fechaPublicacion);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Episodio.create(idUrl, fecha, intensidad,  horasSuenio, suenioRegular, lugar,  episodioEstreCercano,  pacienteID);
        return Results.created();
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createEpisode() throws JSONException {
        JsonNode j = Controller.request().body().asJson();

        Long idUrl = j.findPath("idUrl").asLong();
        int intensidad = j.findPath("intensidad").asInt();
        int horasSuenio = j.findPath("horasSuenio").asInt();
        boolean regular = j.findPath("regular").asBoolean();
        int localizacion = j.findPath("localizacion").asInt();
        boolean estres = j.findPath("estres").asBoolean();
        Long paciente = j.findPath("paciente").asLong();

        Episodio e = Episodio.create(idUrl, new Date(), intensidad, horasSuenio, regular, localizacion, estres, paciente);
        Long episodioID = e.getId();
        JPA.em().persist(e);


        List<JsonNode> g = j.findValues("sintomas");
        if(g.size() > 0) {
            JsonNode values = g.get(0);
            for(JsonNode js : values) {
                int sintomAux = js.findPath("sintoma").asInt();
                Sintoma s = Sintoma.create(sintomAux, episodioID);
                JPA.em().persist(s);
            }
        }

        List<JsonNode> m = j.findValues("medicamentos");
        if(m.size() > 0) {
            JsonNode values = m.get(0);
            for(JsonNode js : values) {
                String nombre = js.findPath("nombre").asText();
                int horas = js.findPath("horas").asInt();
                Medicamento mAxux = Medicamento.create(nombre, horas, episodioID);
                JPA.em().persist(mAxux);
            }
        }

        List<JsonNode> med = j.findValues("actividades");
        if(med.size() > 0) {
            JsonNode values = med.get(0);
            for(JsonNode js : values) {
                int descripcion = js.findPath("descripcion").asInt();
                int inte = js.findPath("intensidad").asInt();
                int lugar = js.findPath("lugar").asInt();
                int clima = js.findPath("clima").asInt();
                boolean hidratacion = js.findPath("hidratacion").asBoolean();
                ActividadFisica af = ActividadFisica.create(descripcion, inte, lugar, clima, hidratacion, episodioID);
                JPA.em().persist(af);
            }
        }

        List<JsonNode> al = j.findValues("alimentos");
        if(al.size() > 0) {
            JsonNode values = al.get(0);
            for(JsonNode js : values) {
                String nombre = js.findPath("nombre").asText();
                int cant = js.findPath("cantidad").asInt();

                Alimento all = Alimento.create(nombre, cant, episodioID);
                JPA.em().persist(all);

            }
        }
        return Results.ok();
    }

}
