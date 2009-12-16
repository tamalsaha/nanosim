package com.nanosim.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Widget;
import com.nanosim.client.icons.NanosimImages;
import com.nanosim.client.mail.Mailboxes;
import com.nanosim.client.patent.PatentHome;
import com.nanosim.client.profile.ProfileHome;
import com.nanosim.client.research.ProposalHome;
import com.nanosim.client.research.ResearchHome;
import com.nanosim.client.transfer.SendFundHome;
import com.nanosim.model.GroupType;
import com.nanosim.model.Patent;

/**
 * A composite that contains the shortcut stack panel on the left side.
 * {@link com.google.gwt.user.client.ui.StackPanel},
 * {@link com.google.gwt.user.client.ui.Tree}, and other custom widgets.
 */
public class LeftPanel extends Composite {
	private static StackPanel stackPanel = new StackPanel();

	private Mailboxes mailboxes;
	private PatentHome patent;
	private ProfileHome profile;
	private SendFundHome sendFund;

	public LeftPanel() {
		stackPanel.setWidth("225px");
		initWidget(stackPanel);
	}

	public void loadShortcuts(RightPanel rightPanel) {
		Nanosim nanosim = Nanosim.getInstance();
		NanosimImages images = Nanosim.images;

		mailboxes = new Mailboxes();
		mailboxes.loadShortcuts(rightPanel);
		add(mailboxes, images.mail(), "Mail");

		if (nanosim.GroupType.getHasPatents()) {
			patent = new PatentHome();
			patent.loadShortcuts(rightPanel);
			add(patent, images.group(), "Patents");
		}

		if (nanosim.GroupType.getHasProfile()) {
			profile = new ProfileHome();
			profile.loadShortcuts(rightPanel);
			add(profile, images.group(), "Profile");
		}

		sendFund = new SendFundHome();
		sendFund.loadShortcuts(rightPanel);
		add(sendFund, images.group(), "Transactions");

		stackPanel.showStack(0);
		mailboxes.loadRightPanel();
	}

	@Override
	public void onBrowserEvent(Event event) {
		if (DOM.eventGetType(event) == Event.ONCLICK) {
			Element target = DOM.eventGetTarget(event);
			int index = findDividerIndex(target);
			if (index != -1) {
				stackPanel.showStack(index);
				switch (index) {
				case 0:
					mailboxes.loadRightPanel();
					break;
				case 1:
					patent.loadRightPanel();
					break;
				case 2:
					profile.loadRightPanel();
					break;
				case 3:
					sendFund.loadRightPanel();
					break;
				default:
					break;
				}
			}
		}
		super.onBrowserEvent(event);
	}

	private int findDividerIndex(Element elem) {
		while (elem != getElement()) {
			String expando = DOM.getElementProperty(elem, "__index");
			if (expando != null) {
				// Make sure it belongs to me!
				int ownerHash = DOM.getElementPropertyInt(elem, "__owner");
				if (ownerHash == hashCode()) {
					// Yes, it's mine.
					return Integer.parseInt(expando);
				} else {
					// It must belong to some nested StackPanel.
					return -1;
				}
			}
			elem = DOM.getParent(elem);
		}
		return -1;
	}

	public static int whichItemChosen() {
		return stackPanel.getSelectedIndex();
	}

	private void add(Widget widget, AbstractImagePrototype imageProto,
			String caption) {
		stackPanel.add(widget, createHeaderHTML(imageProto, caption), true);
	}

	private String createHeaderHTML(AbstractImagePrototype imageProto,
			String caption) {
		String captionHTML = "<table class='caption' cellpadding='0' cellspacing='0'>"
				+ "<tr><td class='lcaption'>"
				+ imageProto.getHTML()
				+ "</td><td class='rcaption'><b style='white-space:nowrap'>&nbsp;"
				+ caption + "</b></td></tr></table>";
		return captionHTML;
	}
}