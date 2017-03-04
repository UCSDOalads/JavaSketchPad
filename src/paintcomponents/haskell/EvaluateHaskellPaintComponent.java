package paintcomponents.haskell;

import java.util.NoSuchElementException;

import paintcomponents.NoConnectingLineSegmentException;
import paintcomponents.data.DataDisplayPaintComponent;
import paintcomponents.data.DataFromPointNoDataProviderException;
import paintcomponents.data.DataFromPointProviderCannotProvideDataException;

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
		setDisplayingText(engine.valueForExpression(this.displayingText));
		
	}
}
