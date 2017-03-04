package actions.menu;

public class ActionsMenuBarTitles {
	
	public String pending;
	
	public ActionsMenuBarTitles(String string) {
		pending = string;
	}

	@Override
	public String toString() {
		return pending;
	}
	
	public static ActionsMenuBarTitles Data(){
		return new ActionsMenuBarTitles("Data");
	}
	
	/**
	 * Returns the string construct for Developer Only Feature
	 * @param remainingTitle the remaining menu titles, this should not begin with /
	 * @return
	 */
	public static ActionsMenuBarTitles Developer(String remainingTitle){
		return new ActionsMenuBarTitles("Developer" + "/" + remainingTitle);
	}

	private void append(String str){
		this.pending += "/" + str;
	}
	
	
	public ActionsMenuBarTitles Input_Box(){
		append("Input Box");
		return this;
	}
	
	public ActionsMenuBarTitles Display_Box(){
		append("Display Box");
		return this;
	}
	
	public ActionsMenuBarTitles Add(){
		append("Add");
		return this;
	}
	
	public ActionsMenuBarTitles Update(){
		append("Update");
		return this;
	}
	
	public ActionsMenuBarTitles Construct(){
		append("Construct");
		return this;
	}
	
	public ActionsMenuBarTitles Line_Segment(){
		append("Line Segment");
		return this;
	}

	public static ActionsMenuBarTitles Lazy() {
		return new ActionsMenuBarTitles("Lazy");
	}

	public ActionsMenuBarTitles Java_Class() {
		append("Java Class");
		return this;
	}

	public ActionsMenuBarTitles Java_Constructor() {
		append("Java Constructor");
		return this;
	}

	public ActionsMenuBarTitles Java_Method() {
		append("Java Method");
		return this;
	}

	public ActionsMenuBarTitles Java_Fields() {
		append("Java Fields");
		return this;
	}
	
	public ActionsMenuBarTitles Remove() {
		append("Remove");
		return this;
	}
	

}
