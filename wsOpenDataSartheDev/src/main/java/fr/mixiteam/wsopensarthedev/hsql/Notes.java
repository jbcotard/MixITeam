package fr.mixiteam.wsopensarthedev.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notes {

	public int getNote(String id) {
		Connection connexion = null;
		try {
			System.out.println("getNote");
			Class.forName("org.hsqldb.jdbcDriver");
 
			connexion = DriverManager.getConnection("jdbc:hsqldb:file:mydb", "sa", "");
			PreparedStatement  stat = connexion.prepareStatement("SELECT note FROM notes where evtId = ?");
			stat.setString(1, id);
			
			ResultSet res= stat.executeQuery();
			
			if (res.next()) {
				return res.getInt(1);
			}
			return -1;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	
	public void setNote(String id, int note) {
		if (getNote(id) == -1) {
			insertNote(id, note);
		} else {
			updateNote(id, note);
		}
	}

	private void updateNote(String id, int note) {
		Connection connexion = null;
		System.out.println("updateNote");
		try {
			Class.forName("org.hsqldb.jdbcDriver");
 
			connexion = DriverManager.getConnection("jdbc:hsqldb:file:mydb", "sa", "");
			PreparedStatement  stat = connexion.prepareStatement("UPDATE notes set note = ? where evtId = ? ");
			
			stat.setInt(1, note);
			stat.setString(2, id);

			stat.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void insertNote(String id, int note) {
		Connection connexion = null;
		System.out.println("insertNote");
		try {
			Class.forName("org.hsqldb.jdbcDriver");
 
			connexion = DriverManager.getConnection("jdbc:hsqldb:file:mydb", "sa", "");
			PreparedStatement  stat = connexion.prepareStatement("INSERT into notes values (?, ?) ");
			stat.setString(1, id);
			stat.setInt(2, note);
			stat.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
