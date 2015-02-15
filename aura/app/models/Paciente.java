package models;

/**
 * Created by Clau on 10/02/2015.
 */


import play.db.ebean.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class Paciente extends Model {

    public static final int MASCULINO = 1;
    public static final int FEMENINO = 1;

    @Id
    public Long docIdentidad;
    public String nombre;
    public String password;
    public Date fechaNacimiento;
    public String email;
    public int genero;
    public static Finder<Long,Paciente> find = new Finder(
            Long.class, Paciente.class
    );

    public static List<Paciente> all() {
        return find.all();
    }

    public static void create(Paciente paciente) {
        paciente.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }
    public static Paciente buscarUnoId(Long id){
       return find.byId(id);
    }



    public Paciente(String email, String password, Long docIdentidad,String nombre,Date fechaNacimiento, int genero) {
        this.email = email;
        this.password = password;
        this.docIdentidad=docIdentidad;
        this.nombre=nombre;
        this.fechaNacimiento=fechaNacimiento;
        this.genero=genero;
    }

}
