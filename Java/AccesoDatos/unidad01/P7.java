package U1;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class P7 {

	public static void main(String[]args) {
		
		 try {
	         File inputFile = new File("DAM_AD_UD01_P6_GOT_Ini.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler();
	         saxParser.parse(inputFile, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}
	
}

class UserHandler extends DefaultHandler {
	   
	   boolean bCharacter = false;
	   boolean bId = false;
	   boolean bName = false;
	   boolean bGender = false;
	   boolean bCulture = false;
	   boolean bBorn = false;
	   boolean bDied = false;
	   boolean bAlive = false;
	   boolean bTitles = false;
	   boolean bTitle = false;
	   boolean bAliases = false;
	   boolean bAlias = false;
	   boolean bFather = false;
	   boolean bMother = false;
	   boolean bSpouse = false;
	   boolean bAllegiances = false;
	   boolean bAllegiance = false;
	   boolean bBooks = false;
	   boolean bBook = false;
	   boolean bTvSeries = false;
	   boolean bSeason = false;

	   @Override
	   public void startElement(
	      String uri, String localName, String qName, Attributes attributes)
	      throws SAXException {
	      
		  if(qName.equalsIgnoreCase("GOT")) {
			  System.out.println("--->" + qName );
		  } else if (qName.equalsIgnoreCase("character")) {
			  System.out.println("----->" + qName);
	    	  bCharacter = true;
	      } else if (qName.equalsIgnoreCase("id")) {
	    	  System.out.println("----->" + qName);
	    	  bId = true;
	      } else if (qName.equalsIgnoreCase("name")) {
	    	  System.out.println("----->" + qName);
	    	  bName = true;
	      } else if (qName.equalsIgnoreCase("gender")) {
	    	  System.out.println("----->" + qName);
		      bGender = true;
	      } else if (qName.equalsIgnoreCase("culture")) {
	    	  System.out.println("----->" + qName);
	    	  bCulture = true;
	      } else if (qName.equalsIgnoreCase("born")) {
	    	  System.out.println("----->" + qName);
	    	  bBorn = true;
	      } else if (qName.equalsIgnoreCase("died")) {
	    	  System.out.println("----->" + qName );
	    	  bDied = true;
	      } else if (qName.equalsIgnoreCase("alive")) {
	    	  System.out.println("----->" + qName);
	    	  bAlive = true;
	      } else if (qName.equalsIgnoreCase("titles")) {
	    	  System.out.println("----->" + qName);
	    	  bTitles = true;
	      } else if (qName.equalsIgnoreCase("title")) {
	    	  bTitle = true;
	      } else if (qName.equalsIgnoreCase("aliases")) {
	    	  System.out.println("----->" + qName);
	    	  bAliases = true;
	      } else if (qName.equalsIgnoreCase("alias")) {
	    	  bAlias = true;
	      } else if (qName.equalsIgnoreCase("father")) {
	    	  System.out.println("----->" + qName);
	    	  bFather = true;
	      } else if (qName.equalsIgnoreCase("mother")) {
	    	  System.out.println("----->" + qName);
	    	  bMother = true;
	      } else if (qName.equalsIgnoreCase("spouse")) {
	    	  System.out.println("----->" + qName);
	    	  bSpouse = true;
	      } else if (qName.equalsIgnoreCase("allegiances")) {
	    	  System.out.println("----->" + qName);
	    	  bAllegiances = true;
	      } else if (qName.equalsIgnoreCase("allegiance")) {
	    	  bAllegiance = true;
	      } else if (qName.equalsIgnoreCase("books")) {
	    	  System.out.println("----->" + qName);
	    	  bBooks = true;
	      } else if (qName.equalsIgnoreCase("book")) {
	    	  bBook = true;
	      } else if (qName.equalsIgnoreCase("tvSeries")) {
	    	  System.out.println("----->" + qName);
	    	  bTvSeries = true;
	      } else if (qName.equalsIgnoreCase("season")) {
	    	  bSeason = true;
	      }
	   }

	   @Override
	   public void endElement(String uri, 
	      String localName, String qName) throws SAXException {
	      
	      if (qName.equalsIgnoreCase("character")) {
	         System.out.println("\n");
	      }
	   }

	   @Override
	   public void characters(char ch[], int start, int length) throws SAXException {
		        
		
	      if (bId) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bId = false;
	      } else if (bName) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bName = false;
	      } else if (bGender) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bGender = false;
	      } else if (bCulture) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bCulture = false;
	      } else if (bBorn) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bBorn = false;
	      } else if (bDied) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bDied = false;
	      } else if (bAlive) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bAlive = false;
	      } else if (bTitle) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bTitle = false;
	      } else if (bAlias) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bAlias = false;
	      } else if (bFather) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bFather = false;
	      } else if (bMother) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bMother = false;
	      } else if (bSpouse) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bSpouse = false;
	      } else if (bAllegiances) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bAllegiances = false;
	      } else if (bBook) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bBook = false;
	      } else if (bSeason) {
	    	  System.out.println("--------->" + new String(ch, start, length));
	    	  bSeason = false;
	      }
	      
	   }
}
