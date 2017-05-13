package actions.global.globalactions;

import actions.global.GlobalPaintAction;
import paintcomponents.PaintComponent;
import ui.PaintPanel;

public abstract class SingleInstanceOperationGlobalAction<T extends GlobalPaintAction<?>> extends GlobalPaintAction<T>{
	
	private PaintComponent operatingInstance;
	

	@Override
	protected void execute(PaintPanel panel) {
		execute(panel, operatingInstance);
		
	}



	public abstract void execute(PaintPanel panel, PaintComponent instance);



	/**
	 * @param operatingInstance the operatingInstance to set
	 */
	public void setOperatingInstance(PaintComponent operatingInstance) {
		this.operatingInstance = operatingInstance;
	}

}
