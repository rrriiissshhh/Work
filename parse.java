package Parser;
import java.io.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class parse {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{

		String inp = "scenario-";
		for(int i=0;i < 1;i++){
			File inputFile = new File(inp + String.valueOf(i) + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			 
			doc.getDocumentElement().normalize();
			
			//Statistics of pass or fail
			Element stats = (Element) doc.getElementsByTagName("statistics").item(0);
			Element passORfail = (Element) stats.getElementsByTagName("suite").item(0);
			if(passORfail.getAttribute("fail").equals("0"))// ALL PASS
				continue;
			
			NodeList nList = doc.getElementsByTagName("test");
			 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Element eElement = (Element) nList.item(temp);
				Element status = (Element) eElement.getElementsByTagName("status").item(1);
				
				if (status.getAttribute("status").equals("PASS"))
					continue;
				System.out.println(status.getNodeName());
				System.out.println(status.getParentNode().getNodeName());
				
				
				System.out.println("Action id : " + eElement.getAttribute("id"));
				System.out.println("Message : " + status.getParentNode().getNextSibling().getTextContent());
			}	
		}
	}
}