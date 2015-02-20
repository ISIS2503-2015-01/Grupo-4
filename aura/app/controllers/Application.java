package controllers;

import org.json.simple.JSONObject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {


    public static Result index() {
        JSONObject jsonPuntos=new JSONObject();
        jsonPuntos.put("i","3");
        String a=Json.toJson(jsonPuntos).toString();
        return ok(index.render(a));
    }

}
