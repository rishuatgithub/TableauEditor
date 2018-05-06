package org.tableau.editor.build;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReplaceContent {
	
	public boolean replacecontent(String filename, String outputeditfile, String connectionFrom, 
			String connectionTo, String schemaFrom, String schemaTo) {

		ArrayList<String> xml_param = new ArrayList<>();
		xml_param.add(0,"/workbook/datasources/datasource/connection/named-connections/named-connection/connection/@filename");
		xml_param.add(1,"/workbook/datasources/datasource/connection/named-connections/named-connection/connection/@server");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docbuilder = factory.newDocumentBuilder();
			Document doc = docbuilder.parse(filename);
			doc.getDocumentElement().normalize();
			
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xp = xpf.newXPath();
			
			for(int j=0; j<xml_param.size(); j++) {
				
				XPathExpression xe = xp.compile(xml_param.get(j));
				
				NodeList nl = (NodeList) xe.evaluate(doc, XPathConstants.NODESET);
				
				for(int i=0; i<nl.getLength(); i++) {
					Node nNode = nl.item(i);
									
					System.out.println(">>>"+nNode.getNodeName());
					if(nNode.getNodeName() == connectionFrom) {
						
						nNode.getNodeName().replace(connectionFrom,connectionTo);
						
					}
					if(nNode.getNodeName() == connectionFrom) {
						nNode.getNodeName().replace(connectionFrom,connectionTo);
					}
				}
					
			}
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(doc);
				StreamResult streamResult = new StreamResult(new File(outputeditfile));
				transformer.transform(domSource, streamResult);
			
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
		} catch (XPathExpressionException e) {
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
