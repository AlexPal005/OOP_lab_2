package Main;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import javax.xml.validation.Validator;

public class XMLValidator {
    public static boolean validate(String xmlLink, String xsdLink ){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdLink));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlLink)));
        } catch (IOException | SAXException e) {
            return false;
        }
        return true;
    }
}
