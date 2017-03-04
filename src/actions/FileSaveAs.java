package actions;

import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import actions.menu.ActionsMenuBarTitles;
import file.PanelIO;
import ui.PaintPanel;

public class FileSaveAs extends PaintAction {

	public FileSaveAs(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		String filePath = JOptionPane.showInputDialog("Please input file path");
		PanelIO io = new PanelIO();
		try {
			io.constructDocumentFromPanel(panel, filePath);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.File().Save().toString();
	}

}
