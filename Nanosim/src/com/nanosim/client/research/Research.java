package com.nanosim.client.research;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Composite that represents a collection of <code>Task</code> items.
 */
public class Research extends Composite {

	public Research() {
		SimplePanel panel = new SimplePanel();
		VerticalPanel list = new VerticalPanel();
		panel.setWidget(list);
		list.add(new CheckBox("Get groceries"));
		list.add(new CheckBox("Walk the dog"));
		initWidget(panel);
		setStyleName("NanoSim-Research");
	}

}