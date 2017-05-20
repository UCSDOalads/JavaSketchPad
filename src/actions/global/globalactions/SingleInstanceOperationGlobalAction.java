package actions.global.globalactions;

import actions.global.GlobalPaintAction;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

public abstract class SingleInstanceOperationGlobalAction extends GlobalPaintAction<SingleInstanceOperationGlobalAction>{
	
	private PaintComponent operatingInstance;
	


	public PaintComponent getOperatingInstance() {
		return operatingInstance;
	}


	/**
	 * @param operatingInstance the operatingInstance to set
	 */
	public void setOperatingInstance(PaintComponent operatingInstance) {
		this.operatingInstance = operatingInstance;
	}

}
