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

	public static ActionsMenuBarTitles Data() {
		return new ActionsMenuBarTitles("Data");
	}

	public static ActionsMenuBarTitles Edit() {
		return new ActionsMenuBarTitles("Edit");
	}

	/**
	 * Returns the string construct for Developer Only Feature
	 * 
	 * @param remainingTitle
	 *            the remaining menu titles, this should not begin with /
	 * @return
	 */
	public static ActionsMenuBarTitles Developer(String remainingTitle) {
		return new ActionsMenuBarTitles("Developer" + "/" + remainingTitle);
	}

	public static ActionsMenuBarTitles File() {
		return new ActionsMenuBarTitles("File");
	}

	public ActionsMenuBarTitles Save() {
		append("Save...");
		return this;
	}

	private void append(String str) {
		this.pending += "/" + str;
	}

	public ActionsMenuBarTitles Input_Box() {
		append("Input Box");
		return this;
	}

	public ActionsMenuBarTitles Display_Box() {
		append("Display Box");
		return this;
	}

	public ActionsMenuBarTitles Add() {
		append("Add");
		return this;
	}

	public ActionsMenuBarTitles Update() {
		append("Update");
		return this;
	}

	public ActionsMenuBarTitles Construct() {
		append("Construct");
		return this;
	}

	public ActionsMenuBarTitles Line_Segment() {
		append("Line Segment");
		return this;
	}

	public static ActionsMenuBarTitles Lazy() {
		return new ActionsMenuBarTitles("Lazy");
	}

	public static ActionsMenuBarTitles Script() {
		return new ActionsMenuBarTitles("Script");
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

	public ActionsMenuBarTitles Undo() {
		append("Undo");
		return this;
	}

	public ActionsMenuBarTitles Redo() {
		append("Redo");
		return this;
	}

	public ActionsMenuBarTitles Remove() {
		append("Remove");
		return this;
	}

	public ActionsMenuBarTitles Open() {
		append("Open...");
		return this;
	}

	public ActionsMenuBarTitles Font_Size() {
		append("Font Size...");
		return this;
	}

	public ActionsMenuBarTitles Point_Size() {
		append("Point Size...");
		return this;
	}

	public ActionsMenuBarTitles Zoom_In() {
		append("Zoom In");
		return this;
	}

	public ActionsMenuBarTitles Zoom_Out() {
		append("Zoom Out");
		return this;
	}

	public ActionsMenuBarTitles Enter_Script() {
		append("Enter Script");
		return this;
	}

	public ActionsMenuBarTitles Annotations() {
		append("Annotations");
		return this;
	}
	
	public ActionsMenuBarTitles RedoUndoHisotry(){
		append("Redo/Undo Hisotry");
		return this;
	}

	public ActionsMenuBarTitles Instance_Operation() {
		append("Instance Operation Component");
		return this;
	}

	public ActionsMenuBarTitles Add_Instance_Method() {
		append("Add Instance Method");
		return this;
	}

	public ActionsMenuBarTitles Annotation_Font_Size() {
		append("Annotation Font Size");

		return this;
	}
}
