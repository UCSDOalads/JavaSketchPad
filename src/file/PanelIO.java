package file;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import paintcomponents.PaintComponent;
import ui.PaintPanel;

/**
 * This class constructs and deconstructs a PaintPanel with all the associate
 * elements
 * 
 * @author chenzb
 *
 */
public class PanelIO {

	private static final String TAG_NAME_TYPE = "type";
	private static final String TAG_NAME_PAINT_COMPONENT = "paintcomponent";

	/**
	 * Generate and save a document to path
	 * 
	 * @param panel
	 *            the paint panel
	 * @param path
	 *            the path to save
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public void constructDocumentFromPanel(PaintPanel panel, String path)
			throws ParserConfigurationException, TransformerException {
		// http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		// root
		Element rootElement = doc.createElement("document");
		doc.appendChild(rootElement);

		// application description
		Element application = doc.createElement("application");
		application.appendChild(doc.createTextNode("UCSDOalads/JavaSketchPad"));
		rootElement.appendChild(application);

		// version
		Element version = doc.createElement("version");
		version.appendChild(doc.createTextNode("1.0"));
		rootElement.appendChild(version);

		constructRootElementWithPanel(rootElement, doc, panel);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

	}

	/**
	 * Construct the xml element inside the given root element
	 * 
	 * @param rootElement
	 *            the root elemen
	 * @param doc
	 *            the document
	 * @param panel
	 *            the paint panel
	 */
	private void constructRootElementWithPanel(Element rootElement,
			Document doc, PaintPanel panel) {
		ArrayList<PaintComponent> paintComponents = panel.getPaintComponents();
		// assume only
		for (PaintComponent paintComponent : paintComponents) {
			Element elem = doc.createElement(TAG_NAME_PAINT_COMPONENT);
			rootElement.appendChild(elem);
			elem.setAttribute(TAG_NAME_TYPE, paintComponent.getClass().getName());
			elem.setAttribute("id", Long.toString(paintComponent.getComponentID()));
			// the root for a particular paint component
			paintComponent.saveToElement(elem, doc);
		}

	}
	
	/**
	 * ID mapping are used to describe relationship components that 
	 * depend on the component ID of selected component.
	 * This property is cleared on every open action. DataTextIOComponents
	 * may use this property to create a one-to-one mapping from the old id to the new id.
	 * <old, new>. Line segments may use this table when traversing the whole paint components
	 * to find the correct relationships.
	 */
	public static HashMap<Long, Long> idMapping;

	/**
	 * 
	 * Clears and reinitializes panel to be the content of the file specified by
	 * the path.
	 * 
	 * If the overwrite flag is set to true, the panel will be CLEARED whether
	 * or not the reading file succeeds.
	 * 
	 * 
	 * 
	 * @param panel the panel to be modified
	 * @param path the path of xml file
	 * @param overwrite
	 *            whether the panel should be cleared
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 */
	public void constructPanelFromDocument(PaintPanel panel, String path,
			boolean overwrite) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		//override idMapping
		idMapping = new HashMap<>();
		//clear if overwrite flag is set to true
		if(overwrite) panel.getPaintComponents().clear();
		
		//code take from http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/

		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		
		Element root = doc.getDocumentElement();
		System.out.println(root);
		
		deconstructDocumentForPanel(root, panel);


	}
	
	/**
	 * Deconstruct the document with the root element, add things to the panel
	 * @param root
	 * @param panel
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void deconstructDocumentForPanel(Element root, PaintPanel panel) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) node;
				if(elem.getTagName().equals(TAG_NAME_PAINT_COMPONENT)){
					String className = elem.getAttribute(TAG_NAME_TYPE);
					Class representingClass = Class.forName(className);
					Constructor constructor = representingClass.getConstructor(Element.class, PaintPanel.class);
					PaintComponent newInstance = (PaintComponent) constructor.newInstance(elem, panel);
					panel.addPaintComponent(newInstance);
					
					//update id Mapping
					long id = Long.parseLong(elem.getAttribute("id"));
					idMapping.put(id, newInstance.getComponentID());
				}
			}
			
		}
		
	}

}
