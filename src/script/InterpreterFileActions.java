package script;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import file.PanelIO;
import ui.PaintPanel;

public class InterpreterFileActions {
	
	private static final String SAVE = "save";
  private static final String SAVE_AS = "saveAs";
  private static final String OPEN = "open";
	private PaintPanel panel;
	
	public InterpreterFileActions(Tokenizer tokenizer, PaintPanel panel)
			throws ExecutionErrorException {
		this.panel = panel;

		if (tokenizer.hasNext()) {
			switch (tokenizer.next()) {
			case OPEN:
				performOpen(panel);
				break;

			case SAVE_AS:
				performSaveAs(panel);
				break;
				
			case SAVE:
				performSaveAs(panel);
				break;
				
			default:
				throw new ExecutionErrorException("invalid script");
			}
		} else {
			throw new ExecutionErrorException("incomplete script");
		}
	}

	private void performOpen(PaintPanel panel2) {
		String filePath = JOptionPane.showInputDialog("Please input file path");
		PanelIO io = new PanelIO();
		try {
			io.constructPanelFromDocument(panel, filePath, true);
			panel.repaint();
		} catch (ParserConfigurationException | SAXException | IOException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
	}

	private void performSaveAs(PaintPanel panel2) {
		String filePath = JOptionPane.showInputDialog("Please input file path");
		PanelIO io = new PanelIO();
		try {
			io.constructDocumentFromPanel(panel, filePath);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
	}
}
