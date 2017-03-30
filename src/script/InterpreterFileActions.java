package script;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import actions.AddLazyJavaClassAction;
import actions.FileOpen;
import actions.FileSaveAs;
import file.PanelIO;
import ui.PaintPanel;

/**
 * Interpret and execute 'file' scripts 
 * @author Xiaoquan Jiang
 */
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
		FileOpen action = new FileOpen(panel);
		action.performAction();
	}

	private void performSaveAs(PaintPanel panel2) {
		FileSaveAs action = new FileSaveAs(panel);
		action.performAction();
	}
}
