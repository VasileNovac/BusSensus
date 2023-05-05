package BusSensus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.LinkedHashMap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@EnableAutoConfiguration

public class BusSensusController {

	public static Connection xconn = null ;
    public static Statement stmt = null;
    
	public boolean erori = false ;
	private String ynumberBus = "" ;
	private String yrouteBus = "" ;
	private String ystationBus = "" ;
	private String insUpd, sql, texterr ;
	public static String numberbus, routebus, stationbus ;
	public ArrayList<String> listNB ;
	public ArrayList<String> listRB ;
	public ArrayList<InitSB> listSB ;
	public ArrayList<String> rbList ;


	@GetMapping("/initnb")
	public void initNB( Model model ) {
		model.addAttribute("ymodul", "initNB") ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("texterr", texterr) ;
		InitNB initNB = new InitNB(ynumberBus) ;
		model.addAttribute("initNB", initNB) ;
	}

	@PostMapping("/addInitNB")
	public String addInitNB( @ModelAttribute InitNB initNB, Model model ) {
		erori = false ;
		texterr = "Exista in baza de date" ;
//		initNBRepository.save(initNB) ;
		
		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			sql = "SELECT numberbus FROM numberbus WHERE numberbus=?" ;
			PreparedStatement sqlNB = xconn.prepareStatement(sql);
			sqlNB.setString(1, initNB.getNumberBus());
			ResultSet rs = sqlNB.executeQuery();
			if ( rs.next()) {
				ynumberBus = initNB.getNumberBus();
				erori = true ;
			} else {
				insUpd = "INSERT INTO numberbus (numberbus) VALUES (?)" ;
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initNB.getNumberBus());
				iS.executeUpdate();
				iS.close();
				
				stmt = xconn.createStatement(); 
				ResultSet sql = stmt.executeQuery("SELECT numberbus FROM numberbus order by numberbus") ;
				listNB = new ArrayList<String>() ;
				while(sql.next()) {
					InitNB initnb = new InitNB(sql.getString("numberbus")) ;
					listNB.add(initnb.getNumberBus()) ;
				}
				ynumberBus = "" ;
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:initnb" ;

	}

	@PostMapping("/delInitNB")
	public String delInitNB( @ModelAttribute InitNB initNB, Model model ) {
		erori = false ;
		texterr = "NU exista in baza de date" ;
		
		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			sql = "SELECT numberbus FROM numberbus WHERE numberbus=?" ;
			PreparedStatement sqlNB = xconn.prepareStatement(sql);
			sqlNB.setString(1, initNB.getNumberBus());
			ResultSet rs = sqlNB.executeQuery();
			if ( rs.next()) {
				insUpd = "DELETE FROM numberbus WHERE numberbus=?" ;
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initNB.getNumberBus());
				iS.executeUpdate();
				iS.close();
				
				stmt = xconn.createStatement(); 
				ResultSet sql = stmt.executeQuery("SELECT numberbus FROM numberbus order by numberbus") ;
				listNB = new ArrayList<String>() ;
				while(sql.next()) {
					InitNB initnb = new InitNB(sql.getString("numberbus")) ;
					listNB.add(initnb.getNumberBus()) ;
				}
				ynumberBus = "" ;
			} else {
				ynumberBus = initNB.getNumberBus();
				erori = true ;
			}
			
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:initnb" ;

	}

	@GetMapping("/initrb")
	public void initRB( Model model ) {
		model.addAttribute("ymodul", "initRB") ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("texterr", texterr) ;
		InitRB initRB = new InitRB(yrouteBus) ;
		model.addAttribute("initRB", initRB) ;
	}

	@PostMapping("/addInitRB")
	public String addInitRB( @ModelAttribute InitRB initRB, Model model ) {
		erori = false ;
		texterr = "Exista in baza de date" ;
//		initRBRepository.save(initRB) ;
		
		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			sql = "SELECT routebus FROM routebus WHERE routebus=?" ;
			PreparedStatement sqlNB = xconn.prepareStatement(sql);
			sqlNB.setString(1, initRB.getRouteBus());
			ResultSet rs = sqlNB.executeQuery();
			if ( rs.next()) {
				yrouteBus = initRB.getRouteBus();
				erori = true ;
			} else {
				insUpd = "INSERT INTO routebus (routebus) VALUES (?)" ;
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initRB.getRouteBus());
				iS.executeUpdate();
				iS.close();
				
				stmt = xconn.createStatement(); 
				ResultSet sql = stmt.executeQuery("SELECT routebus FROM routebus order by routebus") ;
				listRB = new ArrayList<String>() ;
				while(sql.next()) {
					InitRB initrb = new InitRB(sql.getString("routebus")) ;
					listRB.add(initrb.getRouteBus()) ;
				}
				yrouteBus = "" ;
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:initrb" ;

	}

	@PostMapping("/delInitRB")
	public String delInitRB( @ModelAttribute InitRB initRB, Model model ) {
		erori = false ;
		texterr = "Nu exista in baza de date" ;
		
		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			sql = "SELECT routebus FROM routebus WHERE routebus=?" ;
			PreparedStatement sqlNB = xconn.prepareStatement(sql);
			sqlNB.setString(1, initRB.getRouteBus());
			ResultSet rs = sqlNB.executeQuery();
			if ( rs.next()) {
				insUpd = "DELETE FROM routebus WHERE routebus=?" ;
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initRB.getRouteBus());
				iS.executeUpdate();
				iS.close();
				
				stmt = xconn.createStatement(); 
				ResultSet sql = stmt.executeQuery("SELECT routebus FROM routebus order by routebus") ;
				listRB = new ArrayList<String>() ;
				while(sql.next()) {
					InitRB initrb = new InitRB(sql.getString("routebus")) ;
					listRB.add(initrb.getRouteBus()) ;
				}
				yrouteBus = "" ;
			} else {
				yrouteBus = initRB.getRouteBus();
				erori = true ;
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:initrb" ;

	}

	@GetMapping("/initsb")
	public void initSB( Model model ) {
		model.addAttribute("ymodul", "initSB") ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("texterr", texterr) ;
		InitSB initSB = new InitSB(yrouteBus, ystationBus) ;
		model.addAttribute("initSB", initSB) ;
		model.addAttribute("rbList", listRB) ;
	}

	@PostMapping("/addInitSB")
	public String addInitSB( @ModelAttribute InitSB initSB, Model model ) {
		erori = false ;
		texterr = "Exista in baza de date" ;
//		initSBRepository.save(initSB) ;
			
		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			sql = "SELECT routebus, stationbus FROM stationbus WHERE routebus=? AND stationbus=?" ;
			PreparedStatement sqlNB = xconn.prepareStatement(sql);
			sqlNB.setString(1, initSB.getRouteBus());
			sqlNB.setString(2, initSB.getStationBus());
			ResultSet rs = sqlNB.executeQuery();
			if ( rs.next()) {
				yrouteBus = initSB.getRouteBus();
				erori = true ;
			} else {
				insUpd = "INSERT INTO stationbus (routebus, stationbus) VALUES (?, ?)" ;
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initSB.getRouteBus());
				iS.setString(2, initSB.getStationBus());
				iS.executeUpdate();
				iS.close();
				yrouteBus = initSB.getRouteBus();
				
				stmt = xconn.createStatement(); 
				ResultSet sql = stmt.executeQuery("SELECT routebus, stationbus FROM stationbus order by routebus") ;
				listSB = new ArrayList<InitSB>() ;
				while(sql.next()) {
					InitSB initsb = new InitSB(sql.getString("routebus"), sql.getString("stationbus")) ;
					listSB.add(initsb) ;
				}
				ystationBus = "" ;
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:initsb" ;
	}

	@PostMapping("/delInitSB")
	public String delInitSB( @ModelAttribute InitSB initSB, Model model ) {
		erori = false ;
		texterr = "Nu exista in baza de date" ;
			
		xconn = BusSensusApplication.conn ;
		try {
			stmt = xconn.createStatement();
			sql = "SELECT routebus, stationbus FROM stationbus WHERE routebus=? AND stationbus=?" ;
			PreparedStatement sqlNB = xconn.prepareStatement(sql);
			sqlNB.setString(1, initSB.getRouteBus());
			sqlNB.setString(2, initSB.getStationBus());
			ResultSet rs = sqlNB.executeQuery();
			if ( rs.next()) {
				insUpd = "DELETE FROM stationbus WHERE routebus=? AND stationbus=?" ;
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, initSB.getRouteBus());
				iS.setString(2, initSB.getStationBus());
				iS.executeUpdate();
				iS.close();
				yrouteBus = initSB.getRouteBus();
				
				stmt = xconn.createStatement(); 
				ResultSet sql = stmt.executeQuery("SELECT routebus, stationbus FROM stationbus order by routebus") ;
				listSB = new ArrayList<InitSB>() ;
				while(sql.next()) {
					InitSB initsb = new InitSB(sql.getString("routebus"), sql.getString("stationbus")) ;
					listSB.add(initsb) ;
				}
				ystationBus = "" ;
			} else {
				yrouteBus = initSB.getRouteBus();
				erori = true ;
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:initsb" ;
	}

}
