package actions.global.globalactions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import actions.FileOpen;
import actions.global.GlobalPaintAction;
import file.PanelIO;
import ui.PaintPanel;

public class FileOpenGlobalAction extends GlobalPaintAction<FileOpenGlobalAction> {

	private String filePath;

	/**
	 * @param filePath the filePath to set
	 */
	public void setFileToOpen(String filePath){
		//Use JFileChooser.APPROVE_OPTION by default
		this.filePath = filePath;
	}
	
	@Override
	protected void execute(PaintPanel panel) {
		
		//This is copied directly from FileOpen.java performAction method
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
		
		panel.repaint();
	}

}
