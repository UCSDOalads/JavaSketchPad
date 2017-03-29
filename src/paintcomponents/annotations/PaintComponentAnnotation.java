package paintcomponents.annotations;

import java.awt.Paint;

import paintcomponents.PaintComponent;

/**
 * Annotations is part of the paint panel that is attached to a particular paint component
 * Annotations does not mainly impact the behavior of underlying paint components, but 
 * extends the component to be more extensible
 * @author chenzb
 *
 */
public abstract class PaintComponentAnnotation extends PaintComponent{

	private PaintComponent attachedComponent;

	public PaintComponentAnnotation(PaintComponent attachedComponent) {
		super(attachedComponent.getX(), attachedComponent.getY());
		this.setAttachedComponent(attachedComponent);
		attachedComponent.setOptionalAnnotation(this);
	}

	/**
	 * @return the attachedComponent
	 */
	public PaintComponent getAttachedComponent() {
		return attachedComponent;
	}

	/**
	 * @param attachedComponent the attachedComponent to set
	 */
	public void setAttachedComponent(PaintComponent attachedComponent) {
		this.attachedComponent = attachedComponent;
	}
	
	
	




	

}
