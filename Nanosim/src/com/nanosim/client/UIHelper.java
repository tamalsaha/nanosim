package com.nanosim.client;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class UIHelper {

	public int setSelectedValue(ListBox lst, int value) {
		int index = -1;
		String strVal = "" + value;
		for (int i = lst.getItemCount(); i-- > 0;) {
			if (lst.getValue(i).equals(strVal)) {
				lst.setSelectedIndex(i);
				index = i;
				break;
			}
		}
		return index;
	}

	public TreeItem addImageItem(Tree tree, String title,
			AbstractImagePrototype imageProto) {
		TreeItem item = new TreeItem(imageItemHTML(imageProto, title));
		item.setTitle(title);
		tree.addItem(item);
		return item;
	}

	/**
	 * A helper method to simplify adding tree items that have attached images.
	 * {@link #addImageItem(TreeItem, String, AbstractImagePrototype) code}
	 * 
	 * @param root
	 *            the tree item to which the new item will be added.
	 * @param title
	 *            the text associated with this item.
	 */
	public TreeItem addImageItem(TreeItem root, String title,
			AbstractImagePrototype imageProto) {
		TreeItem item = new TreeItem(imageItemHTML(imageProto, title));
		item.setTitle(title);
		root.addItem(item);
		return item;
	}

	/**
	 * Generates HTML for a tree item with an attached icon.
	 * 
	 * @param imageProto
	 *            the image prototype to use
	 * @param title
	 *            the title of the item
	 * @return the resultant HTML
	 */
	public String imageItemHTML(AbstractImagePrototype imageProto, String title) {
		return imageProto.getHTML() + " " + title;
	}

}
