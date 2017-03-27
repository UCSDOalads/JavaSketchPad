package actions.menu;

public class ActionsPopupMenuTitles {
	public String pending;
	
	public ActionsPopupMenuTitles(String string) {
		pending = string;
	}

	@Override
	public String toString() {
		return pending;
	}
	
	public static ActionsPopupMenuTitles Remove(){
		return new ActionsPopupMenuTitles("Remove");
	}
	public static ActionsPopupMenuTitles Constructor(){
		return new ActionsPopupMenuTitles("Constructor");
	}
	public static ActionsPopupMenuTitles Method(){
		return new ActionsPopupMenuTitles("Method");
	}
	public static ActionsPopupMenuTitles Fields(){
		return new ActionsPopupMenuTitles("Fields");
	}
	public ActionsPopupMenuTitles append(String str) {
		pending += "/" + str;
		return this;
	}

}
