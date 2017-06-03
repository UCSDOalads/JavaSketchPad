package actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import actions.global.ActionName;
import actions.global.GlobalPaintActionExecuter;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.global.globalactions.FileOpenGlobalAction;
import actions.menu.ActionsMenuBarTitles;
import file.PanelIO;
import ui.PaintPanel;

import javax.swing.JFileChooser;
import java.io.File;

public class FileOpen extends MenuBarPaintAction {

	public FileOpen(PaintPanel panel) {
		super(panel);
	}

	@Override
	public boolean canPerformAction() {
		return true;
	}

	@Override
	public void performAction() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select the file to open");
		int userSelection = fileChooser.showOpenDialog(panel);
		
		String filePath;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    filePath = fileToSave.getAbsolutePath();
		} else {
			return;
		}
		
		FileOpenGlobalAction associatedAction 
		= (FileOpenGlobalAction) ActionName.FILE_OPEN_GLOBAL_ACTION
				.getAssociatedAction();
		associatedAction.setFileToOpen(filePath);
		GlobalPaintActionExecuter.getSharedInstance().execute(associatedAction, panel);
	}

	@Override
	public String locationString() {
		return ActionsMenuBarTitles.File().Open().toString();
	}

}
