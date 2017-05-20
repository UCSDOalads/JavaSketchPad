package paintcomponents.data;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import paintcomponents.RectanglePaintComponent;
import paintcomponents.TextPaintComponent;
import painttools.tools.SelectToolInterface;
import settings.Defaults;
import painttools.tools.SelectTool;
import ui.PaintPanel;

/**
 * A text component with a bounding box, this.bounds is updated in paint method
 * 
 * @author chenzb
 *
 */
public class DataTextPaintComponent extends TextPaintComponent {

	private RectanglePaintComponent rect;

	public DataTextPaintComponent(String displayingText, int x, int y) {
		super(displayingText, x, y);
		rect = new RectanglePaintComponent(x, y, 0, 0);
	}

	@Override
	protected void paintNotSelected(Graphics g) {
		((Graphics2D) g).setStroke(new BasicStroke(1));
		super.paintNotSelected(g);
		updateAndPaintBoudingRectangle(g);

	}

	@Override
	protected void paintSelected(Graphics g) {
		((Graphics2D) g).setStroke(new BasicStroke(1));
		super.paintSelected(g);
		;
		updateAndPaintBoudingRectangle(g);
	}

	private void updateAndPaintBoudingRectangle(Graphics g) {
		rect.setWidth((int) getBounds().getWidth());
		rect.setHeight((int) getBounds().getHeight());
		rect.paint(g);
	}

	@Override
	public void translate(int i, int j) {
		super.translate(i, j);
		this.rect.translate(i, j);
	}

	@Override
	public void select(SelectToolInterface selectTool) {
		super.select(selectTool);
		// pass in null to prevent current selection from being modified
		// only causes changes in apperance
		rect.select(null);
	}

	@Override
	public void deselect(SelectToolInterface selectTool) {
		// TODO Auto-generated method stub
		super.deselect(selectTool);
		rect.deselect(null);
	}

	@Override
	public void saveToElement(Element rootElement, Document doc) {
		super.saveToElement(rootElement, doc);

		Element main = doc.createElement("datatextpaintcomponent");
		Element rectElem = doc.createElement("rect");

		rect.saveToElement(rectElem, doc);
		main.appendChild(rectElem);

		rootElement.appendChild(main);
	}

	public DataTextPaintComponent(Element rootElement, PaintPanel panel) {
		super(rootElement, panel);
		Element main = (Element) rootElement.getElementsByTagName(
				"datatextpaintcomponent").item(0);
		Element rectElem = (Element) main.getElementsByTagName("rect").item(0);
		this.rect = new RectanglePaintComponent(rectElem, panel);
	}

}
