package org.tableau.editor.build;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReplaceContent {
	
	public boolean replacecontent(String filename, String outputeditfile, String connectionFrom, 
			String connectionTo, String schemaFrom, String schemaTo) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docbuilder = factory.newDocumentBuilder();
			Document doc = docbuilder.parse(filename);
			doc.getDocumentElement().normalize();
			
			//System.out.println("Root: "+doc.getDocumentElement().getNodeName());
			//System.out.println("Attribute :"+doc.getElementsByTagName("connection"));
			
			
			NodeList nList = doc.getElementsByTagName("connection");
			
			for(int i=0; i<nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					System.out.println(">>>"+eElement.getAttribute("filename"));
					//System.out.println(connectionFrom+"----"+connectionTo);
					
					//eElement.setAttribute("filename", connectionTo);
					
					// if(connectionFrom != "" && connectionTo != "") {
						if(eElement.getAttribute("filename") == connectionFrom) {
							eElement.setAttribute("filename", connectionTo);
						}
					//}
					
					//if(schemaFrom != "" && schemaTo != "") {
						if(eElement.getAttribute("server") == connectionFrom) {
							eElement.setAttribute("server", connectionTo);
						}
					//}
					
				}
				
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(new File(outputeditfile));
			transformer.transform(domSource, streamResult);
			
			//new File(filename).delete();
			
			/*if(renameFile(new File(outputeditfile), new File(filename))) {
				return true;
			}else {
				return false;
			}*/
			
			
			
			return true;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean renameFile(File old_fname, File new_fname) {
		
		if(old_fname.renameTo(new_fname)) {
			return true;
		}else {
			return false;
		}
	}

}
