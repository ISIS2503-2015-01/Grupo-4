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
import java.util.List;

/**
 * Created by Clau on 10/02/2015.
 */
public class EpisodioController extends Controller {

    // CRUD
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result create(Long idPaciente) {

        Paciente p = JPA.em().getReference(Paciente.class, idPaciente);

        if(p != null) {
            JsonNode j = Controller.request().body().asJson();
            System.out.println(j);

            String idUrl = j.findPath("URL").asText();
            Long url = idUrl.equals("") ? -1 : Long.parseLong(idUrl);
            int intensidad = j.findPath("intensidad").asInt();
            int horasSuenio = j.findPath("horasSuenio").asInt();
            boolean regularidad = j.findPath("regular").asBoolean();
            int localizacion = j.findPath("localizacion").asInt();
            boolean estres = j.findPath("estres").asBoolean();

            try {

                Episodio e = Episodio.create(url, new Date(), intensidad, horasSuenio, regularidad, localizacion, estres, idPaciente);
                JPA.em().persist(e);

            } catch (Exception e) {
                e.printStackTrace();
                return Results.ok("Error al crear el episodio");
            }

            return Results.created();

        }

        return Results.ok("El paciente no existe");

    }

    @Transactional
    public static Result getAll(Long id) {
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.pacienteID = :id");
        query.setParameter("id", id);
        List episodios = query.getResultList();
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
    public static Result get(long id) {
        Episodio p = JPA.em().getReference(Episodio.class, id);
        Hibernate.initialize(Episodio.class);
        return Results.ok(Json.toJson(p));
    }

    @Transactional
    public static Result delete(Long id1, Long id2){
        Episodio p = JPA.em().find(Episodio.class, id2);
        if(p.getPacienteID() == id1) {
            JPA.em().remove(p);
            return Results.ok();
        }
        return Results.ok("Error de argumentos");
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result update(Long id1, Long id2) {

        Paciente p = JPA.em().getReference(Paciente.class, id1);

        if(p != null) {
            JsonNode j = Controller.request().body().asJson();
            System.out.println(j);

            String idUrl = j.findPath("URL").asText();
            Long url = idUrl.equals("") ? -1 : Long.parseLong(idUrl);
            int intensidad = j.findPath("intensidad").asInt();
            int horasSuenio = j.findPath("horasSuenio").asInt();
            boolean regularidad = j.findPath("regular").asBoolean();
            int localizacion = j.findPath("localizacion").asInt();
            boolean estres = j.findPath("estres").asBoolean();

            try {

                Episodio e = JPA.em().getReference(Episodio.class, id2);
                e.setEpisodioEstreCercano(estres);
                e.setIdUrl(url);
                e.setIntensidad(intensidad);
                e.setSuenioRegular(regularidad);
                e.setHorasSuenio(horasSuenio);
                e.setLugar(localizacion);


            } catch (Exception e) {
                e.printStackTrace();
                return Results.ok("Error al crear el episodio");
            }

            return Results.created();

        }

        return Results.ok("El paciente no existe");
    }
    @Transactional
    public static Result getPerDates(Long idP, String f1, String f2) {
        Date d1 = parseDate(f1);
        Date d2 = parseDate(f2);
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.fechaPublicacion >= :d1 AND e.fechaPublicacion <= :d2");
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        Collection<Episodio> episodios = query.getResultList();
        return Results.ok(Json.toJson(episodios));
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

    public static Result getNotification(Long idP) {
        return null;
    }

    public static Result getAnalisis(Long idP) {
        return null;
    }

}
