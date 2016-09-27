package controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.UserTokens;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class MariadbWithJpa extends Controller{
@Inject 
JPAApi api;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Result tokens()
	{
		EntityManager em = api.em();
		Query query = em.createNativeQuery("select * from usertoken where username='harsha'");
		List<UserTokens> tokens = query.getResultList();
		
		return ok(tokens.get(0).getToken()+ " "+tokens.get(0).getUsername());
	}
}
