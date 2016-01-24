package fr.mixiteam.wsopensarthedev.hsql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

public class InitDatabase extends javax.servlet.http.HttpServlet {

	@Override
	public void init() throws ServletException {
	    super.init();
	    Connection connexion = null;
	    try {
	        System.out.println("Starting Database");
	        HsqlProperties p = new HsqlProperties();
	        p.setProperty("server.database.0", "file:~/mydb");
	        p.setProperty("server.dbname.0", "mydb");
	        p.setProperty("server.port", "9001");
	        Server server = new Server();
	        server.setProperties(p);
	        server.setLogWriter(null); // can use custom writer
	        server.setErrWriter(null); // can use custom writer
	        server.start();
	    
	        Class.forName("org.hsqldb.jdbcDriver");
	        
	        connexion = DriverManager.getConnection("jdbc:hsqldb:file:mydb", "sa", "");
	        
	        Statement stat = connexion.createStatement();
	        stat.executeUpdate("CREATE TABLE notes (evtId VARCHAR(100), note INT)");
	        
	        stat.close();
	        System.out.println("CREATE");
//	    } catch (IOException ioex) {
//	        throw new ServletException(ioex);
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	    	System.out.println("Database starting");
	    }
	}
	
}
