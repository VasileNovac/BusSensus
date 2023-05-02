package BusSensus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection; 
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException; 
import java.sql.Statement;
//import java.sql.ResultSet; 

@SpringBootApplication
public class BusSensusApplication {

	private static String sql ;
	public static String luna, an, denLuna ;
//JDBC driver name and database URL 
	static final String JDBC_DRIVER = "org.h2.Driver";   
//	static final String DB_URL = "jdbc:h2:~/test;IFEXISTS=TRUE;DB_CLOSE_ON_EXIT=FALSE"; 
	static final String DB_URL = "jdbc:h2:~/test;IFEXISTS=TRUE"; 

//Database credentials 
	static final String USER = "sa"; 
	static final String PASS = ""; 

	public static Connection conn = null; 
	public static Statement stmt = null; 

	public static void main(String[] args) {
		SpringApplication.run(BusSensusApplication.class, args);
		
		try { 
//			Register JDBC driver 
		        Class.forName(JDBC_DRIVER); 
//			Open a connection 
		        conn = DriverManager.getConnection(DB_URL,USER,PASS);

//				Drop table utilizat in procesul de testare
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS bs" ;
//		        stmt.executeUpdate(sql) ;
//			Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS bs " +
		        		"( numberbus varchar(10)," +
		        		"routebus varchar(10)," +
		        		"stationbus varchar(100)," +
		        		"traveler integer," +
		        		"travelerup integer," +
		        		"travelerdown integer," +
		        		"userid varchar(30)," +
		        		"data varchar(10)," +
		        		"ora varchar(10) )" ;
		        stmt.executeUpdate(sql) ;

//				Drop table utilizat in procesul de testare
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS numberbus" ;
//		        stmt.executeUpdate(sql) ;
//			Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS numberbus " +
		        		"( numberbus varchar(10)," +
		        		"PRIMARY KEY (numberbus) ) ";
		        stmt.executeUpdate(sql) ;

//				Drop table utilizat in procesul de testare
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS routebus" ;
//		        stmt.executeUpdate(sql) ;
//  Exemple: 
//			Transport urban: L01 -tur, L01 -retur, L02 -tur, L02 -retur, L02B-tur, L02B-retur, 
//		        L03 -tur, L03 -retur, L04 -tur, L04 -retur, L05 -tur, L05 -retur, L05M-tur, L05M-retur,
//		        L06 -tur, L06 -retur, L07 -tur, L07 -retur, L08 -tur, L08 -retur, L09 -tur, L09 -retur,
//		        L10 -tur, L10 -retur, L14 -tur, L14 -retur, L15 -tur, L15 -retur, L16 -tur, L16 -retur,
//		        L17 -tur, L17 -retur, L17B-tur, L17B-retur, L18 -tur, L18 -retur, L20 -tur, L20 -retur,
//		        L20B-tur, L20B-retur, L21 -tur, L21 -retur, L22 -tur, L22 -retur, L23 -tur, L23 -retur,
//		        L23B-tur, L23B-retur, L24 -tur, L24 -retur, L25 -tur, L25 -retur, L28 -tur, L28 -retur,
//				L29 -tur, L29 -retur, L31 -tur, L31 -retur, L32 -tur, L32 -retur, L33 -tur, L33 -retur,
//		        L34 -tur, L34 -retur, L34B-tur, L34B-retur, L35 -tur, L35 -retur, L36 -tur, L36 -retur,
//		        L37 -tur, L37 -retur, L40 -tur, L40 -retur, L41 -tur, L41 -retur, L50 -tur, L50 -retur,
//		        L52 -tur, L52 -retur, L53 -tur, L53 -retur, L54 -tur, L54 -retur, L60 -tur, L60 -retur 
//			Transport metropolitan: M110-tur, M110-retur, M130-tur, M130-retur, M131-tur, M131-retur,
//		        M210-tur, M210-retur, M220-tur, M220-retur, M310-tur, M310-retur, M320-tur, M320-retur,
//		        M410-tur, M410-retur, M411-tur, M411-retur, M412-tur, M412-retur, M420-tur, M420-retur,
//				M511-tur, M511-retur, M520-tur, M520-retur, M540-tur, M540-retur, M610-tur, M610-retur,
//		        M611-tur, M611-retur, M612-tur, M612-retur, M620-tur, M620-retur, M810-tur, M810-retur 
//			Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS routebus " +
		        		"( routebus varchar(10), " +
		        		"PRIMARY KEY (routebus) )";
		        stmt.executeUpdate(sql) ;

//				Drop table utilizat in procesul de testare
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS stationbus" ;
//		        stmt.executeUpdate(sql) ;
//Exemple:
//		        routebus: L05 -tur
//		        stationbus: stadMunicipal, BisericaBartolomeu, Carierei, Memorandului, BisericiiRomane, 
//		        Astra, Dramatic, Patria, HidroA, HidroB, SpitalulJudetean, LiceulInformatic, Berzei, Pompieri, 
//		        Metrom, Poienelor, Roman
//		        routebus: L05 -retur
//		        stationbus: Roman, Soarelui, Berzei, LiceulInformatic, SpitalulJudetean, Toamnei, LiceulMesota, CameraComert,
//		        Sanitas, Primarie, Astra, BisericiiRomane, Memorandului, Carierei, BartolomeuGara, stadMunicipal
//
//				routebus: L04 -tur
//		        stationbus: GaraBrasov, Dacia, Infostar, CameraComert, Sanitas, Primarie, LivadaPostei, BisericaNeagra, Brancoveanu, PiataUnirii, Tocile
//		        routebus: L04 -retur
//		        stationbus: Tocile, PiataUnirii, LiceulSaguna, LivadaPostei, Dramatic, Patria, HidroA, Infostar, Rapid, GaraBrasov 
//		        Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS stationbus " +
		        		"( routebus varchar(10)," +
		        		"stationbus varchar(100), " +
		        		"PRIMARY KEY (routebus, stationbus) )";
		        stmt.executeUpdate(sql) ;

		} catch(SQLException se) { 
//			Handle errors for JDBC 
		        se.printStackTrace(); 
		    } catch(Exception e) { 
//			Handle errors for Class.forName 
		        e.printStackTrace(); 
		    } finally { }
	}

}
