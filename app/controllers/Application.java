package controllers;

import org.json.simple.JSONObject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.test;

public class Application extends Controller {

    public static class Login {

        public String email;
        public String password;

    }

    public static Result index() {
        JSONObject jsonPuntos=new JSONObject();
        jsonPuntos.put("i","3");
        String a=Json.toJson(jsonPuntos).toString();
        return ok(index.render(a));
    }

    public static Result test() {
        return ok(
                test.render(form(Login.class))
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        return ok();
    }

}


