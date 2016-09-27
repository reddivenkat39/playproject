package services;

import play.mvc.Http;
import play.mvc.Security;

public class Fbauthenticator extends Security.Authenticator{

	@Override
	public String getUsername(Http.Context ctx){
		String token = getTokenFromHeader(ctx);
		if(token!=null){
			return "venkata";
		}
		return null;
	}
	
	private String getTokenFromHeader(Http.Context ctx){
		String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
		if((authTokenHeaderValues!=null)&&(authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)){
			return authTokenHeaderValues[0];
		}
		return null;
	}
}
