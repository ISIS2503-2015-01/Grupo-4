package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import org.hibernate.Hibernate;
import org.json.JSONArray;
import org.json.simple.JSONObject;
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
    public static Json create(Long idPaciente) {

        Paciente p = JPA.em().getReference(Paciente.class, idPaciente);

        if(p != null) {

            JsonNode j = Controller.request().body().asJson();

            String idUrl = j.findPath("url").asText();
            Long url = idUrl.equals("-1") ? -1 : Long.parseLong(idUrl);
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
                return null;
            }

            Json js= getNotification(idPaciente,intensidad, horasSuenio, regularidad, localizacion, estres);
            return js;
        }

        return null;

    }

    @Transactional
    public static Result getAll(Long id) {
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.pacienteID = :id");
        query.setParameter("id", id);
        List episodios = query.getResultList();
        return Results.ok(Json.toJson(episodios));
    }

    @Transactional
    public static Result getOne(Long id1, Long id2) {
        Query query = JPA.em().createQuery("SELECT e FROM Episodio e WHERE e.pacienteID = :id1 and e.id = :id2");
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        Collection<Episodio> episodios = query.getResultList();
        return Results.ok(Json.toJson(episodios));
    }

    @Transactional
    public static Result get(Long id) {
        Episodio p = JPA.em().getReference(Episodio.class, id);
        Hibernate.initialize(Episodio.class);
        return Results.ok(Json.toJson(p));
    }

    @Transactional
    public static Result delete(Long id1, Long id2){
        Episodio p = JPA.em().find(Episodio.class, id2);
        if(p.getPacienteID().equals(id1)) {
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

            String idUrl = j.findPath("url").asText();
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

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addSymptom(Long idp, Long id1) {

        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            JsonNode j = Controller.request().body().asJson();

            int sintoma = j.findPath("sintoma").asInt();

            try {
                Sintoma s = new Sintoma();
                s.setEpisodioId(id1);
                s.setSintoma(sintoma);
                JPA.em().persist(s);

            } catch (Exception x) {
                x.printStackTrace();
                return Results.ok("Error al crear el sintoma");
            }
            return Results.created();
        }
        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result deleteSymptom(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            Sintoma s = JPA.em().getReference(Sintoma.class, id2);
            JPA.em().remove(s);
            Results.ok();
        }

        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result getOneSymptom(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            Sintoma s = JPA.em().getReference(Sintoma.class, id2);
            return Results.ok(Json.toJson(s));
        }

        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result getAllSymptom(Long idp, Long id) {
        Query query = JPA.em().createQuery("SELECT s FROM Sintoma s WHERE s.episodioId = :id");
        query.setParameter("id", id);
        Collection<Sintoma> sintomas = query.getResultList();
        for(Sintoma s : sintomas) {
            if(!s.getEpisodioId().equals(idp))
                return Results.ok("Error en los parametros");
        }
        return Results.ok(Json.toJson(sintomas));
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateSymptom(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            JsonNode j = Controller.request().body().asJson();

            int sintoma = j.findPath("sintoma").asInt();

            try {
                Sintoma s = JPA.em().getReference(Sintoma.class, id2);
                s.setSintoma(sintoma);
            } catch (Exception x) {
                x.printStackTrace();
                return Results.ok("Error al actualizar el sintoma");
            }
            return Results.created();
        }
        return Results.ok("El Episodio no existe");
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addFood(Long idp, Long id1) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            JsonNode j = Controller.request().body().asJson();

            String nombre = j.findPath("nombre").asText();
            int cantidad = j.findPath("cantidad").asInt();

            try {
                Alimento a = new Alimento();
                a.setNombre(nombre);
                a.setCantidad(cantidad);
                a.setEpisodioId(id1);
                JPA.em().persist(a);

            } catch (Exception x) {
                x.printStackTrace();
                return Results.ok("Error al crear el alimento");
            }
            return Results.created();
        }
        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result deleteFood(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            Alimento a = JPA.em().getReference(Alimento.class, id2);
            JPA.em().remove(a);
            Results.ok();
        }

        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result getOneFood(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            Alimento a = JPA.em().getReference(Alimento.class, id2);
            return Results.ok(Json.toJson(a));
        }

        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result getAllFood(Long idp, Long id) {
        Query query = JPA.em().createQuery("SELECT a FROM Alimento a WHERE a.episodioId = :id");
        query.setParameter("id", id);
        Collection<Alimento> alimentos = query.getResultList();
        for(Alimento a : alimentos) {
            if(!a.getEpisodioId().equals(idp))
                return Results.ok("Error en los parametros");
        }
        return Results.ok(Json.toJson(alimentos));
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateFood(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            JsonNode j = Controller.request().body().asJson();

            String nombre = j.findPath("nombre").asText();
            int cantidad = j.findPath("cantidad").asInt();

            try {
                Alimento a = JPA.em().getReference(Alimento.class, id2);
                a.setCantidad(cantidad);
                a.setNombre(nombre);

            } catch (Exception x) {
                x.printStackTrace();
                return Results.ok("Error al actualizar el alimento");
            }
            return Results.created();
        }
        return Results.ok("El Episodio no existe");
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addActivity(Long idp, Long id1) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            JsonNode j = Controller.request().body().asJson();

            int descripcion = j.findPath("descripcion").asInt();
            int intensidad = j.findPath("intensidad").asInt();
            int lugar = j.findPath("lugar").asInt();
            int clima = j.findPath("clima").asInt();
            boolean hidratacion = j.findPath("hidratacion").asBoolean();

            try {

                ActividadFisica a = new ActividadFisica();
                a.setClima(clima);
                a.setDescripcion(descripcion);
                a.setIntensidad(intensidad);
                a.setLugar(lugar);
                a.setHidratacion(hidratacion);
                a.setEpisodioId(id1);


            } catch (Exception x) {
                x.printStackTrace();
                return Results.ok("Error al crear la actividad");
            }
            return Results.created();
        }
        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result deleteActivity(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            ActividadFisica a = JPA.em().getReference(ActividadFisica.class, id2);
            JPA.em().remove(a);
            Results.ok();
        }

        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result getOneActivity(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            ActividadFisica a = JPA.em().getReference(ActividadFisica.class, id2);
            return Results.ok(Json.toJson(a));
        }

        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result getAllActivity(Long idp, Long id) {
        Query query = JPA.em().createQuery("SELECT a FROM ActividadFisica a WHERE a.episodioId = :id");
        query.setParameter("id", id);
        Collection<ActividadFisica> actividades = query.getResultList();
        for(ActividadFisica a : actividades) {
            if(!a.getEpisodioId().equals(idp))
                return Results.ok("Error en los parametros");
        }
        return Results.ok(Json.toJson(actividades));
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateActivity(Long idp, Long id1, Long id2) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            JsonNode j = Controller.request().body().asJson();

            int descripcion = j.findPath("descripcion").asInt();
            int intensidad = j.findPath("intensidad").asInt();
            int lugar = j.findPath("lugar").asInt();
            int clima = j.findPath("clima").asInt();
            boolean hidratacion = j.findPath("hidratacion").asBoolean();

            try {
                ActividadFisica a = JPA.em().getReference(ActividadFisica.class, id2);
                a.setDescripcion(descripcion);
                a.setIntensidad(intensidad);
                a.setLugar(lugar);
                a.setClima(clima);
                a.setHidratacion(hidratacion);

            } catch (Exception x) {
                x.printStackTrace();
                return Results.ok("Error al actualizar la actividad");
            }
            return Results.created();
        }
        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Json getNotification(Long idP,int intensidad, int horasSuenio, boolean suenioRegular, int lugar, boolean episodioEstreCercano) {

    return null;
    }

    @Transactional
    public static Result getAnalisis(Long idP) {

        return Results.TODO;
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addMedicine(Long idp, Long id1) {
        Episodio e = JPA.em().getReference(Episodio.class, id1);

        if(e != null && e.getPacienteID().equals(idp)) {
            JsonNode j = Controller.request().body().asJson();

            String nombre = j.findPath("nombre").asText();
            int horasTomadoAntes = j.findPath("horas").asInt();

            try {
                Medicamento m = new Medicamento();
                m.setNombre(nombre);
                m.setHorasTomadoAntes(horasTomadoAntes);


            } catch (Exception x) {
                x.printStackTrace();
                return Results.ok("Error al crear la actividad");
            }
            return Results.created();
        }
        return Results.ok("El Episodio no existe");
    }

    @Transactional
    public static Result deleteMedicine(Long idp, Long id1, Long id2) {
        return Results.TODO;
    }

    @Transactional
    public static Result getOneMedicine(Long idp, Long id1, Long id2) {
        return Results.TODO;
    }

    @Transactional
    public static Result getAllMedicine(Long idp, Long id) {
        return Results.TODO;
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateMedicine(Long idp, Long id1, Long id2) {
        return Results.TODO;
    }

    @Transactional
    public static Result fetch(Long idP, Long id) {
        Episodio e = JPA.em().getReference(Episodio.class, id);
        if(!e.getPacienteID().equals(idP))
            return null;
        JSONObject result = new JSONObject();
        result.put("id", id);

        result.put("idUrl", e.getIdUrl());
        result.put("fechaPublicacion", e.getFechaPublicacion().toString());
        result.put("intensidad", e.getIntensidad());
        result.put("horasSuenio", e.getHorasSuenio());
        result.put("regular", e.isSuenioRegular());
        result.put("localizacion", e.getLugar());
        result.put("estres", e.isEpisodioEstreCercano());
        result.put("paciente", e.getPacienteID());

        Query query = JPA.em().createQuery("SELECT s FROM Sintoma s WHERE s.episodioId = :id");
        query.setParameter("id", id);
        Collection<Sintoma> sintomas = query.getResultList();
        JSONArray sintomasJson = new JSONArray();
        for(Sintoma s : sintomas) {
            Long sId = s.getId();
            int ss = s.getSintoma();
            Long ep = s.getEpisodioId();
            JSONObject simple = new JSONObject();
            simple.put("id", sId);
            simple.put("sintoma", ss);
            simple.put("episodioId", ep);
            sintomasJson.put(simple);
        }


        return Results.ok(Json.toJson(result));
    }
}
