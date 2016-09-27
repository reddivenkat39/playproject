package controllers;

import java.net.UnknownHostException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import model.FormEntity;
import model.UserGivenDetails;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class FormController extends Controller{

public Result patientform(){
	MongoClient client;
	 JsonNode form=null;
	DBObject dbobj =null;
	try {
		client = new MongoClient("localhost",27017);
		  Morphia morphia = new Morphia();
		  Datastore ds = morphia.createDatastore(client,"facebook1");
		 
		  DBCollection collection = ds.getCollection(FormEntity.class);
		  dbobj = collection.findOne();
		  form = Json.toJson(dbobj);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ok(form);
}


public Result formsave(String FirstName, String LastName, String Gender){
	MongoClient client;
	try {
		client = new MongoClient("localhost",27017);
		Morphia morphia = new Morphia();
		  Datastore ds = morphia.createDatastore(client,"facebook1");
		  UserGivenDetails ugd = new UserGivenDetails();
		  ugd.setFirstname(FirstName);
		  ugd.setSecondname(LastName);
		  ugd.setGender(Gender);
		  ds.save(ugd);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ok("saved in usergivendetails");
}
}
