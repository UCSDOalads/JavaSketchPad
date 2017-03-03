package file;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ui.PaintPanel;

/**
 * This class constructs and deconstructs a PaintPanel with all the associate elements
 * @author chenzb
 *
 */
public class PanelIO {

	public void constructDocumentFromPanel(PaintPanel panel, String path) throws ParserConfigurationException, TransformerException{
		//http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		//root
		Element rootElement = doc.createElement("document");
		doc.appendChild(rootElement);
		
		//application description
		Element application = doc.createElement("application");
		application.appendChild(doc.createTextNode("UCSDOalads/JavaSketchPad"));
		rootElement.appendChild(application);
		
		
		//version
		Element version = doc.createElement("version");
		version.appendChild(doc.createTextNode("1.0"));
		rootElement.appendChild(version);
		
		constructRootElementWithPanel(rootElement, panel);
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		
		
	}

	private void constructRootElementWithPanel(Element rootElement,
			PaintPanel panel) {
		// TODO Auto-generated method stub
		
	}
}
