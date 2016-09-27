package controllers;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import model.PersistUser;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Http.Cookie;
import play.mvc.Result;
import services.TokenService;
import services.details;

public class PersistController extends Controller{
	public Result persistdata(String uname,String password){
		details udetail = new details();
		udetail.setPassword(password);
		udetail.setUname(uname);
		MongoClient client;
		int flag=0;
		try {
			client = new MongoClient("127.0.0.1", 27017);
			Morphia morphia = new Morphia();
			Datastore ds = morphia.createDatastore(client, "facebook1");
			morphia.map(PersistUser.class);
			PersistUser ps = new PersistUser();
			ps.setUsername(uname);
			ps.setPassword(password);
			
			List<PersistUser> userlist = ds.find(PersistUser.class).asList();
			if(userlist!=null){
			Iterator<PersistUser> it = userlist.iterator();
			while(it.hasNext()){
				if(it.next().getUsername().equals(uname)){
				flag = 1;
				}
			}
			}
			if(flag!=1){
			ds.save(ps);
			TokenService ts = new TokenService();
			ts.createtoken(uname, password);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(flag==1){
			return ok("<h1>user already exists</h1>");
		}
		else
		return ok(views.html.details.render(udetail));
	}
	
	
public Result signin(String uname , String password){
	String success = "Sorry Try again";
	Cookie ck =null;
	try {
		MongoClient client = new MongoClient("127.0.0.1",27017);
		Morphia morphia = new Morphia();
		Datastore ds = morphia.createDatastore(client,"facebook1");
		List<PersistUser> userlist = ds.find(PersistUser.class).asList();
		if(userlist!=null){
		Iterator<PersistUser> it = userlist.iterator();
		while(it.hasNext()){
			PersistUser pu = it.next();
			if(pu.getPassword().equals(password)&& pu.getUsername().equals(uname)){
				success = "validated";
				TokenService ts = new TokenService();
			String token = 	ts.createtoken(uname, password);
			response().setHeader("X-AUTH-TOKEN",token);
			ck= new Cookie("X-AUTH-TOKEN",token,3600,"/","localhost.com:9000",false,true);
			response().setCookie(ck);
			
		}
		}
	}
	}catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ok(views.html.signinsuccess.render(success));
}
}
