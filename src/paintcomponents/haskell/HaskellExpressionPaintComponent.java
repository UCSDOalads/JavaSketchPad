package paintcomponents.haskell;

import java.util.NoSuchElementException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataFromPointDataProvider;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import paintcomponents.data.DataTextIOPaintComponent;
import paintcomponents.data.DataToPoint;
import typesystem.HaskellType;
import ui.PaintPanel;

public class HaskellExpressionPaintComponent extends DataTextIOPaintComponent implements DataFromPointDataProvider {

	private HaskellTypeParser typeParser;
	private String displayingExpr;

	public HaskellExpressionPaintComponent(String displayingText, int x,
			int y) {
		super(displayingText, x, y);
		this.displayingExpr = displayingText;
		this.typeParser = new HaskellTypeParser(displayingExpr);
		init();
	}

	private void init() {

		// parse the type first
		boolean success = typeParser.parseType();
		if (!success) {
			setDisplayingText(typeParser.getMessage());
			return;
		}

		// build UI
		/*
		 * Line 0 : name of command Line 1 : command output as a whole, on the
		 * right Line 2 ~ 2 + n - 1: arguments Line 2 + n : return type
		 * 
		 */

		StringBuilder builder = new StringBuilder();
		builder.append(this.displayingExpr + "\n");
		builder.append(this.displayingExpr + " :: "
				+ typeParser.getDisplayingExprType() + " >>>>" + "\n");
		addFromPoint(this , 1, typeParser.getDisplayingExprType());

		// arguments
		for (int i = 0; i < typeParser.getArguments().size(); i++) {
			builder.append("arg" + i + " :: " + typeParser.getArguments().get(i)
					+ "\n");
			addToPoint(i + 2, new HaskellType(typeParser.getArguments().get(i)));

		}
		// return value
		builder.append("Return Value :: " + this.typeParser.getReturnType()
				+ " >>> " + "\n");
		addFromPoint(this, 2 + typeParser.getArguments().size(),
				this.typeParser.getReturnType());
		setDisplayingText(builder.toString());

	}

	@Override
	public Object provideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		// TODO implement this
		if(getFromPoints().indexOf(dataFromPoint)==0){
				return displayingExpr;
		} else if (getFromPoints().indexOf(dataFromPoint) == 1){
				String expr = displayingExpr;
				for (DataToPoint toPoint : getToPoints()) {
					try {
						// TODO condsider the validity of to string
						// treating every argument as a string
						String arg = toPoint.fetchData().toString();
						// if string contains space, wrap in parens (pattern
						// match against a string not followed by a right
						// parenthesis
						if (arg.split(" (?![^(]*\\))").length != 1) {
							arg = "(" + arg + ")";
						}

						expr += " " + arg;

					} catch (NoSuchElementException
							| NoConnectingLineSegmentException
							| DataFromPointNoDataProviderException
							| DataFromPointProviderCannotProvideDataException e) {
						// break on the first occurance, to allow for currying
						break;
					}

				}
				return expr;
		} else {
			return null;
		}
	}

	@Override
	public boolean canProvideInformationToDataFromPoint(
			DataFromPoint dataFromPoint) {
		return true;
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		
		//create and structure
		Element main = doc.createElement("haskellexprcomponent");
		Element expressionElem = doc.createElement("expression");
		
		main.appendChild(expressionElem);
		rootElement.appendChild(main);
		
		//insert data
		expressionElem.appendChild(doc.createTextNode(displayingExpr));
	}

	public HaskellExpressionPaintComponent(Element rootElement,
			PaintPanel panel) {
		super(rootElement, panel);
		//use the same routine as the designated initializer
		Element main = (Element) rootElement.getElementsByTagName("haskellexprcomponent").item(0);
		Element expressionElem = (Element) main.getElementsByTagName("expression").item(0);
		this.displayingExpr = expressionElem.getTextContent();
		this.setDisplayingText(displayingExpr);
		this.typeParser = new HaskellTypeParser(displayingExpr);
		init();
		linkPoints(rootElement);
		
	}
	
	
}
