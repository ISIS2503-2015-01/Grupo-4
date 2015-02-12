package models;

/**
 * Created by Clau on 10/02/2015.
 */

import play.data.validation.Constraints;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class EpisodioEscrito extends Model {

    public static final int INTENSIDAD_NIVEL1 = 1;
    public static final int INTENSIDAD_NIVEL2 = 2;
    public static final int INTENSIDAD_NIVEL3 = 3;
    public static final int INTENSIDAD_NIVEL4 = 4;
    public static final int INTENSIDAD_NIVEL5 = 5;

    public static final int LUGAR_OSCURO = 1;
    public static final int LUGAR_ABIERTO_SOLEADO = 2;
    public static final int LUGAR_RUIDOSO = 3;
    public static final int LUGAR_CON_OLORES_FUERTES= 4;


    @Id
    public Long id;

    @Constraints.Required
    public Date fechaPublicacion;

    @Constraints.Required
    public int intensidad;


    public Double horasSuenio;

    public boolean suenioRegular;

    public int lugar;

    public boolean episodioEstreCercano;
}
