package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

public class MariadbConnection extends Controller{
	@Inject
	private Database database;
	
	
	public Result usertoken(){
		String username = "username";
		String token = "token";
		Connection con = this.database.getConnection(true);
		String sql = "select * from usertoken where username=?";
	    try {
			PreparedStatement stmnt = con.prepareStatement(sql);
			 stmnt.setString(1,"harsha");
			 ResultSet rs = stmnt.executeQuery();
			 while(rs.next()){
				 username= rs.getString(1);
				 token = rs.getString(2);
			 }
			 stmnt.close();
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return ok(username + " "+ token);
	}
}
