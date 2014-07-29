package org.trusst.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLSerializer {
	
	public static Object deserializeXMLToObject(String xmlFilePath, Class instance){
		File xmlFile = new File(xmlFilePath);
		Object unMarshalledObject = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(instance);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			unMarshalledObject =  jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return unMarshalledObject;
	}
	
	public static File serializeObjectToXML(Class instance, String xmlFilePath){
		File xmlFile = new File(xmlFilePath);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(instance);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(instance, xmlFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlFile;
	}

}
