package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Paciente;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Clau on 10/02/2015.
 */
public class EpisodioController extends Controller {

    // CRUD
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public static Result create(Long idPaciente) {

        e.horasSuenio = horasSuenio;
        e.suenioRegular = suenioRegular;
        e.lugar = lugar;
        e.episodioEstreCercano = episodioEstreCercano;
        e.pacienteID = pacienteID;

        JsonNode j = Controller.request().body().asJson();

        Long idUrl = j.findPath("URL").asLong();
        Date fechaPublicacion = new Date();
        int intensidad = j.findPath("intensidad").asInt();
        int horasSuenio = j.findPath("horasSuenio").asInt();
        boolean regularidad = j.findPath("regular").asBoolean();




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

















    public static Result reportarAlarma(Long idPaciente){
        return null;
    }
    public static Result reportarAnalisis(Long idPaciente){
        return null;
    }
    @BodyParser.Of(BodyParser.Json.class)
    public static Result crearEpisodioVoz( ){
        JsonNode j = Controller.request().body().asJson();
        System.out.print(j);
        String urlSonido = j.findPath("urlSonido").asText();
        String fechaP = j.findPath("fechaPublicacion").asText();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;


        try {
            fecha = formatoDelTexto.parse(fechaP);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    @BodyParser.Of(BodyParser.Json.class)
    public static Result crearEpisodioEscrito(Long idPaciente) {
        JsonNode j = Controller.request().body().asJson();
        System.out.print(j);
        String fechaP = j.findPath("fechaPublicacion").asText();
        Integer intensidad = Integer.parseInt(j.findPath("intensidad").asText());
        Double horasSuenio = Double.parseDouble(j.findPath("horasSuenio").asText());
        Boolean suenioRegular = Integer.parseInt(j.findPath("suenioRegular").asText()) == 1;
        Boolean sufrioEstresAntes = Integer.parseInt(j.findPath("sufrioEstresAntes").asText()) == 1;
        Integer lugar = Integer.parseInt(j.findPath("lugar").asText());


        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;


        try {
            fecha = formatoDelTexto.parse(fechaP);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Paciente p = JPA.em().getReference(Paciente.class, idPaciente);
        return null;
    }


    public static Result eliminarEpisodioVoz(Long id){
        return Results.ok();

    }

    public static Result eliminarEpisodioEscrito(Long id){
        return Results.ok();}
    public static Result actualizarEpisodioVoz(String urlSonido, Date fecha, Integer intensidad, Integer hora){
        return null;
    }

    public static Result actualizarEpisodioEscrito(Date fecha, Integer intensidad, Integer hora,Integer horasSuenio,Integer suenioRegular, Boolean sufrioEstresAntes, Integer localizacion){
        return null;
    }
}
