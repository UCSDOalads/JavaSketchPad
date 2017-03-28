package interation;





// JList implementing
//
//
//  -----------------Ignoring Following ---------------------
//
//


import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class historyData<T> {
	JList<T> histList;
	DefaultListModel<T> listModel;
	JScrollPane listScroller;
	
	
	public historyData(){
		listModel = new DefaultListModel<>();
		histList = new JList<T>(listModel);
		histList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		histList.setLayoutOrientation(JList.VERTICAL);
		histList.setVisible(true);
		listScroller = new JScrollPane(histList);
		listScroller.setPreferredSize(new Dimension(250,80));
		histList.setVisibleRowCount(10);
		
	}
	
	
	/**
	 * add updated data to the list
	 */
	public void AddData(T data){
		listModel.addElement(data);
	}

	/**
	 * select data
	 */
	public T selectedData(){
		T selected = histList.getSelectedValue();
		
		return selected;
	}
}
