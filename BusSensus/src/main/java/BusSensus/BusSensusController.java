package BusSensus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@EnableAutoConfiguration

public class BusSensusController {

	public static Connection xconn = null ;
    public static Statement stmt = null;
	private String insUpd ;
	public static String numberbus, routebus, stationbus ;
	public ArrayList<String> listNB ;
	public ArrayList<String> listRB ;
	public ArrayList<String> listSB ;


	@GetMapping("/init")
	public void in( Model model ) {
	}

	@PostMapping("/initNB")
	public String initNB( Model model ) {

		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			insUpd = "INSERT INTO numberbus (numberbus) VALUES (?)" ;
			PreparedStatement iS = xconn.prepareStatement(insUpd);
			iS.setString(1, numberbus);
			iS.executeUpdate();

			stmt = xconn.createStatement();
			ResultSet rsL = stmt.executeQuery("SELECT numberbus FROM numberbus order by numberbus") ;
			listNB = new ArrayList<String>() ;
			while(rsL.next()) { 
				listNB.add(rsL.getString("numberbus")) ;
			}
			
		} catch(SQLException se) { 
	//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:init" ;
	}

	@PostMapping("/initRB")
	public String initRB( Model model ) {

		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			insUpd = "INSERT INTO routebus (routebus) VALUES (?)" ;
			PreparedStatement iS = xconn.prepareStatement(insUpd);
			iS.setString(1, routebus);
			iS.executeUpdate();

			stmt = xconn.createStatement();
			ResultSet rsL = stmt.executeQuery("SELECT routebus FROM routebus order by routebus") ;
			listRB = new ArrayList<String>() ;
			while(rsL.next()) { 
				listRB.add(rsL.getString("routebus")) ;
			}
			
		} catch(SQLException se) { 
	//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:init" ;
	}

	@PostMapping("/initSB")
	public String valIn( Model model ) {

		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			insUpd = "INSERT INTO numberbus (routebus, stationbus) VALUES (?, ?)" ;
			PreparedStatement iS = xconn.prepareStatement(insUpd);
			iS.setString(1, routebus);
			iS.setString(2, stationbus);
			iS.executeUpdate();

			stmt = xconn.createStatement();
			ResultSet rsL = stmt.executeQuery("SELECT stationbus FROM stationbus order by stationbus") ;
			listSB = new ArrayList<String>() ;
			while(rsL.next()) { 
				listSB.add(rsL.getString("stationbus")) ;
			}
			
		} catch(SQLException se) { 
	//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:init" ;
	}

}
