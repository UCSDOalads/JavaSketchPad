package painttools.tools;

import java.util.ArrayList;

import paintcomponents.PaintComponent;

public class SmartTool implements SelectToolInterface{

	@Override
	public ArrayList<PaintComponent> getSelectedComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectComponent(PaintComponent comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deselectComponent(PaintComponent comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearSelection() {
		// TODO Auto-generated method stub
		
	}

	public boolean addSelectionToolListener(SelectionToolListener e) {
		return false;
	}

}
