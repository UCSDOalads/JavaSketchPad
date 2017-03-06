package paintcomponents.haskell;

import java.util.NoSuchElementException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;
import ui.PaintPanel;

public class EvaluateHaskellPaintComponent extends DataDisplayPaintComponent{

	HaskellEngine engine;
	public EvaluateHaskellPaintComponent(String displayingText, int x, int y) {
		super(displayingText, x, y);
		engine = new HaskellEngine();
		
	}

	@Override
	public void updateDisplayText()
			throws NoSuchElementException, NoConnectingLineSegmentException,
			DataFromPointNoDataProviderException,
			DataFromPointProviderCannotProvideDataException {
		super.updateDisplayText();
		setDisplayingText(engine.valueForExpression(this.getDisplayingText()));
		
	}
	
	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);
		//the default implementation does nothing`
	}

	public EvaluateHaskellPaintComponent(Element rootElement,
			PaintPanel panel) {
		super(rootElement, panel);
		this.engine = new HaskellEngine();
	}
	
	
}
