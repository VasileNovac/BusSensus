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
	public static String luna, an, denLuna, ziua, ora, min ;
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

//		BusSensus
//				Drop table utilizat in procesul de testare
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS bs" ;
//		        stmt.executeUpdate(sql) ;
//			Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS bs " +
		        		"( numberbus varchar(10)," +
		        		"routebus varchar(100)," +
		        		"stationbus varchar(100)," +
		        		"traveler integer," +
		        		"travelerup integer," +
		        		"travelerdown integer," +
		        		"userid varchar(30)," +
		        		"data varchar(10)," +
		        		"ora varchar(10) )" ;
		        stmt.executeUpdate(sql) ;

//		NumberBus
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

//		RouteBus
//				Drop table utilizat in procesul de testare
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS routebus" ;
//		        stmt.executeUpdate(sql) ;
//  Exemple: 
//			Transport urban: 
//		        L01 LivadaPostei-Triaj, L01 Triaj-LivadaPostei,	L02 LivadaPostei-Rulmentul, L02 Rulmentul-LivadaPostei, L02B LivadaPostei-Rulmentul, L02B Rulmentul-LivadaPostei, 
//	    	    L03 stad.Tineretului-ValeaCetatii, L03 ValeaCetatii-stad.Tineretului, L04 PeTocile-GaraBrasov, L04 GaraBrasov-PeTocile, L05 stad.Municipal-Roman, L05 Roman-stad.Municipal, 
//	    	    L05M stad.Municipal-Magurele, L05M Magurele-stad.Municipal, L06 LivadaPostei-Saturn, L06 Saturn-LivadaPostei, L07 Rulmentul-Roman, L07 Roman-Rulmentul, 
//	        	L08 Rulmentul-Saturn, L08 Saturn-Rulmentul, L09 Rulmentul-stad.Municipal, L09 stad.Municipal-Rulmentul, L10 ValeaCetatii-Triaj, L10 Triaj-ValeaCetatii, 
//			    L14 LivadaPostei-FabricaVar, L14 FabricaVar-LivadaPostei, L15 Avantgarden-Triaj, L15 Triaj-Avantgarden, L16 LivadaPostei-Caramidariei, L16 Caramidariei-LivadaPostei,
//   			L17 LivadaPostei-Noua, L17 Noua-LivadaPostei, L17B GaraBrasov-TimisulJos, L17B TimisulJos-GaraBrasov, L18 BarieraBartolomeu-IARGhimbav, L18 IARGhimbav-BarieraBartolomeu, 
//  			L20 LivadaPostei-PoianaBrasov, L20 PoianaBrasov-LivadaPostei, L20B ValeaCetatii-Belvedere, L20B Belvedere-ValeaCetatii, L21 Triaj-Noua, L21 Noua-Triaj, 
//    			L22 Saturn-stad.Tineretului, L22 stad.Tineretului-Saturn, L23 Saturn-stad.Municipal, L23 stad.Municipal-Saturn, L23B Triaj-stad.Municipal, L23B stad.Municipal-Triaj, 
//    			L24 LivadaPostei-StupiniiNoi, L24 StupiniiNoi-LivadaPostei, L25 Avantgarden-Roman, L25 Roman-Avantgarden, L28 LivadaPostei-IARGhimbav, L28 IARGhimbav-LivadaPostei,
//				L29 ParcInd.Ghimbav-GaraBrasov, L29 GaraBrasov-ParcInd.Ghimbav, L31 ValeaCetatii-LivadaPostei, L31 LivadaPostei-ValeaCetatii, L32 ValeaCetatii-Coresi, L32 Ceresi-ValeaCetatii, 
//				L33 ValeaCetatii-Roman, L33 Roman-ValeaCetatii, L34 LivadaPostei-TimisTriaj, L34 TimisTriaj-LivadaPostei, L34B Izvor-LivadaPostei, L34B LivadaPostei-Izvor, 
//    			L35 GaraBrasov-Noua, L35 Noua-GaraBrasov, L36 LivadaPostei-Independentei, L36 Independentei-LivadaPostei, L37 Craiter-HidroA, L37 HidroA-Craiter, 
//    			L40 GaraBrasov-Lujerului, L40 Lujerului-GaraBrasov, L41 LivadaPostei-StupiniLujerului, L41 StupiniLujerului-LivadaPostei, L50 Solomon-CameraComert, L50 CameraComert-Solomon,
//		        L52 PeTocile-Roman, L52 Roman-PeTocile, L53 FacultateaConstructii-Panselelor, L53 Panselelor-FacultateaConstructii, L54 Triaj-HidroA, L54 HidroA-Triaj

//			Transport metropolitan: 
//		        M110 stad.Municipal-Cristian, M110 Cristian-stad.Municipal, M130 stad.Municipal-Rasnov, M130 Rasnov-stad.Municipal, M131 stad.Municipal-RasnovRomacril, M131 RasnovRomacril-stad.Municipal, 
//				M210 stad.Municipal-Ghimbav, M210 Ghimbav-stad.Municipal, M220 stad.Municipal-Codlea, M220 Codlea-stad.Municipal, M310 GaraBrasov-SatuNou, M310 SatuNou-GaraBrasov, 
//				M320 GaraBrasov-Feldioara, M320 Feldioara-GaraBrasov, M410 RulmentulSubcetate, M410 Subcetate-Rulmentul, M411 Rulmentul-SanpetruResidece, M411 SanpetruResidence-Rulmentul, 
//				M412 Rulmentul-MoriiSanpetru, M412 MoriiSanpetru-Rulmentul, M420 Rulmentul-Bod, M420 Bod-Rulmentul, M511 Triaj-PoduOltului, M511 PoduOltului-Triaj,
//				M520 Triaj-Prejmer, M520 Prejmer-Triaj, M540 BrasovCaprioara-VamaBuzaului, M540 VamaBuzaului-BrasovCaprioara, M610 Roman-Purcareni, M610 Purcareni-Roman, 
//				M611 Roman-Tarlungeni, M611 Tarlungeni-Roman, M612 Roman-Purcareni, M612 Purcareni-Roman, M620 Roman-Budila, M620 Budila-Roman, 
//				M810 Roman-Predeal, M810 Predeal-Roman
//			Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS routebus " +
		        		"( routebus varchar(100), " +
		        		"PRIMARY KEY (routebus) )";
		        stmt.executeUpdate(sql) ;

//		StationBus
//				Drop table utilizat in procesul de testare
//		        stmt = conn.createStatement();
//		        sql = "DROP TABLE IF EXISTS stationbus" ;
//		        stmt.executeUpdate(sql) ;
//Exemple:
//		        routebus: L05 Roman-stad.Municipal
//		        stationbus: stad.Municipal, BisericaBartolomeu, Carierei, Memorandului, BisericiiRomane, 
//		        Astra, Dramatic, Patria, HidroA, HidroB, SpitalulJudetean, LiceulInformatic, Berzei, Pompieri, 
//		        Metrom, Poienelor, Roman
//		        routebus: L05 stad.Municipal-Roman
//		        stationbus: Roman, Soarelui, Berzei, LiceulInformatic, SpitalulJudetean, Toamnei, LiceulMesota, CameraComert,
//		        Sanitas, Primarie, Astra, BisericiiRomane, Memorandului, Carierei, BartolomeuGara, stad.Municipal
//
//				routebus: L04 PeTocile-GaraBrasov
//		        stationbus: GaraBrasov, Dacia, Infostar, CameraComert, Sanitas, Primarie, LivadaPostei, BisericaNeagra, Brancoveanu, PiataUnirii, PeTocile
//		        routebus: L04 GaraBrasov-PeTocileTocile
//		        stationbus: PeTocile, PiataUnirii, LiceulSaguna, LivadaPostei, Dramatic, Patria, HidroA, Infostar, Rapid, GaraBrasov 
//		        Create table
		        stmt = conn.createStatement() ;
		        sql = "CREATE TABLE IF NOT EXISTS stationbus " +
		        		"( routebus varchar(100)," +
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
