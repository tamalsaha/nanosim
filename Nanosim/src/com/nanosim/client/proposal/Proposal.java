package com.nanosim.client.proposal;

import com.google.gwt.user.client.ui.TreeItem;
import com.nanosim.client.Nanosim;
import com.nanosim.client.RightPanel;
import com.nanosim.client.StackContentBase;
import com.nanosim.client.icons.NanosimImages;
import com.nanosim.model.GroupType;

public class Proposal extends StackContentBase {

	public Proposal() {
		singleton = this;
		NanosimImages images = Nanosim.images;

		// TreeItem root = new TreeItem(uiHelper.imageItemHTML(images.home(),
		// "user@nanosim.com"));
		// tree.addItem(root);
		// uiHelper.addImageItem(root, "Mission", images.inbox());
		// root.setState(true);
	}

	@Override
	public void loadShortcuts(RightPanel rightPanel, GroupType groupType) {
		this.rightPanel = rightPanel;
		// rightPanel.add(null);
		// rightPanel.add(null);
	}

	@Override
	public void loadRightPanel() {
		rightPanel.clear();
	}

}