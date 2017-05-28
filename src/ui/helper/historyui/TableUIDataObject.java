package ui.helper.historyui;

import java.security.UnrecoverableEntryException;

/**
 * 
 * HistoryDataOjbect
 *
 */
public class TableUIDataObject {
	
	String data;
	int row;
	boolean untraced;
	public TableUIDataObject(){
		
	}
	
	public TableUIDataObject(String data){
		this.data = data;
		untraced = false;
	}
	
	
	public String toString(){
		if(untraced){
			return "[untraced] " + data;
		}
		return data;
	}
	
	public void setRow(int i){
		row = i;
	}
	
	public int getRow(){
		return row;
	}

	public void setUntraced(boolean truth){
		untraced = truth;
	}
	
	public boolean getUntraced(){
		return untraced;
	}
}
