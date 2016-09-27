package controllers;
import play.mvc.*;

public class AnotherController extends Controller{

	 public  Result anotherpage(String name) {
	        return ok(views.html.anotherpage.render(name));
	    }
}
