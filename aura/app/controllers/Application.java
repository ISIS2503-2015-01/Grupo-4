package controllers;

import org.codehaus.jackson.JsonNode;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    @BodyParser.Of(Json.class)
    public static index sayHello() {
        String name = json.findPath("name").getTextValue();
        if(name == null) {
            return badRequest("Missing parameter [name]");
        } else {
            return ok("Hello " + name);
        }
    }

}
