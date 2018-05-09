/**
 *  GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

Tableau Editor  Copyright (C) 2018  Rishu Kumar Shrivastava (rishu.shrivastava@gmail.com)
 */
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

/**
 * Replace the contents of the xml file of the tableau
 * @author Rishu Shrivastava (rishu.shrivastava@gmail.com)
 * @version 1.0.0
 * 
 */

public class ReplaceContent {

	public boolean replacecontent(String filename, String outputeditfile, String connectionFrom, 
			String connectionTo, String schemaFrom, String schemaTo) {

		ArrayList<String> xml_param = new ArrayList<>();
		xml_param.add(0,"/workbook/datasources/datasource/connection/named-connections/named-connection/connection/@server");
		xml_param.add(1,"/workbook/datasources/datasource/connection/named-connections/named-connection/connection/@schema");
		xml_param.add(2,"/workbook/datasources/datasource/connection/named-connections/named-connection/@caption");

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

					//System.out.println(nNode.getNodeType());
					if(nNode.getNodeValue().equals(connectionFrom)) {
						nNode.setNodeValue(connectionTo);
					}
					if(nNode.getNodeValue().equals(schemaFrom)) {
						nNode.setNodeValue(schemaTo);
					}
				}	
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(new File(outputeditfile));
			transformer.transform(domSource, streamResult);

			//System.out.println("Deleting Old File");
			boolean delfile = deleteFile(new File(filename));
			if(!delfile) {
				return false;
			}

			//System.out.println("Renaming New File");
			boolean renameFile = renameFile(new File(outputeditfile), new File(filename));
			if(!renameFile) {	
				return false;
			}

			return true;

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		} catch (XPathExpressionException e) {
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

	public boolean deleteFile(File old_fname) {

		return old_fname.delete();
	}

}
