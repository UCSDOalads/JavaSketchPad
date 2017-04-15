package actions.global.globalactions;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import actions.global.GlobalPaintAction;
import file.PanelIO;
import ui.PaintPanel;

public class FileSaveAsGlobalAction extends GlobalPaintAction {

	private String filePath;
	
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	@Override
	protected void execute(PaintPanel panel) {
		// TODO Auto-generated method stub
		//Copied from FileSaveAs.java
		PanelIO io = new PanelIO();
		try {
			io.constructDocumentFromPanel(panel, filePath);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, e.toString());
		}
		
		panel.repaint();

	}

}
