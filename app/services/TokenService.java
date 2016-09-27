package services;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import model.TokenEntity;

public class TokenService {
	private String tokengenerator(String u , String p){
		return u.substring(0,u.length()-2)+23+p.substring(0,p.length()-2);
	}

	public String createtoken(String username,String password){
		MongoClient client;
		String token=null;
		try {
			client = new MongoClient("localhost",27017);
			Morphia morphia = new Morphia();
			Datastore ds = morphia.createDatastore(client,"facebook1");
			TokenEntity te = new TokenEntity();
			 token = tokengenerator(username,password);
			te.setUsername(username);
			te.setPassword(password);
			te.setToken(token);
			ds.save(te);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	
	public TokenEntity gettoken(String usertoken){
			MongoClient client;
			TokenEntity token=null;
				try {
					client = new MongoClient("localhost",27017);
					Morphia morphia = new Morphia();
					Datastore ds = morphia.createDatastore(client,"facebook1");
					List<TokenEntity> tokens =ds.find(TokenEntity.class).asList();
					Iterator<TokenEntity> it = tokens.iterator();
					while(it.hasNext()){
						TokenEntity te = it.next();
						if(te.getToken().equals(usertoken)){
							token = te;
						}
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return token;
	}
}
