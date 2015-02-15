package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Paciente;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PacienteController extends Controller
{
    @BodyParser.Of(BodyParser.Json.class)
    public static Result crearPaciente() {

        JsonNode j=Controller.request().body().asJson();
        System.out.print(j);

        String email=j.findPath("email").asText();
        String password = j.findPath("password").asText();
        Long docIdentidad = Long.parseLong(j.findPath("docIdentidad").asText());
        String name = j.findPath("nombre").asText();
        String fechaN = j.findPath("fechaN").asText();
        Integer genero = Integer.parseInt(j.findPath("genero").asText());
        System.out.print(j);

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;


        try {
            fecha = formatoDelTexto.parse(fechaN);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Paciente paciente=new Paciente (email,password,docIdentidad,name,fecha,genero);
        Paciente.create(paciente);


        return Results.created();
    }
    public static Result eliminarPaciente(Long id){
        Paciente.delete(id);
        return Results.ok();
    }
    public static Result getAll()
    {
        List<Paciente> pacientes = Paciente.all();
        return Results.ok(Json.toJson(pacientes));
    }
    public static Result actualizarPaciente(){
    return null;
    }

    public static Result reportarEpisodioVoz(){
        return null;
    }

    public static Result reportarEpisodioEscrito(){
        return null;
    }
    public static Result buscarUnoId(Long id){
        Paciente paciente=Paciente.buscarUnoId(id);
       return Results.ok(Json.toJson(paciente));
    }
    public static Result verEpisodiosEscritos(){
        return null;
    }

    public static Result verEpisodiosVoz(){
        return null;
    }
}
