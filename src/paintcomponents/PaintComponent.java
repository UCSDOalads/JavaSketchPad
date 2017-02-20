package paintcomponents;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class PaintComponent {
	
	private int x;
	private int y;
	private boolean selected;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	public PaintComponent(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g){
		if(selected){
			paintSelected(g);
		} else {
			paintNotSelected(g);
		}
		
	}
		
	protected abstract void paintNotSelected(Graphics g) ;
		

	protected abstract void paintSelected(Graphics g) ;
		

	public void select(){
		selected = true;
	}
	public void deselect(){
		selected = false;
	}
	
	public void toggleSelect() {
		selected = !selected;
	}
	
	public boolean isSelected(){
		return selected;
	}

	public void translate(int i, int j) {
		this.x+=i;
		this.y+=j;
		
	}


	public abstract boolean contains(int x2, int y2); 


}
