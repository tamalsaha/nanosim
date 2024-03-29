package com.nanosim.client.mail;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.nanosim.model.Mail;

 /**
 * A composite for displaying the details of an email message.
 */
public class MailBody extends Composite {

	private VerticalPanel panel = new VerticalPanel();
	private VerticalPanel headerPanel = new VerticalPanel();
	private HTML sender = new HTML();	
	private HTML recipient = new HTML();
	private HTML subject = new HTML();
	private HTML body = new HTML();
	private HTML date = new HTML();
	private ScrollPanel scroller = new ScrollPanel(body);

	public MailBody() {
		body.setWordWrap(true);

		headerPanel.add(subject);
		headerPanel.add(sender);
		headerPanel.add(recipient);
		headerPanel.add(date);
		headerPanel.setWidth("100%");

		DockPanel innerPanel = new DockPanel();
		innerPanel.add(headerPanel, DockPanel.NORTH);
		innerPanel.add(scroller, DockPanel.CENTER);

		innerPanel.setCellHeight(scroller, "100%");
		panel.add(innerPanel);
		innerPanel.setSize("100%", "100%");
		scroller.setSize("100%", "100%");
		initWidget(panel);

		setStyleName("mail-Detail");
		headerPanel.setStyleName("mail-DetailHeader");
		innerPanel.setStyleName("mail-DetailInner");
		subject.setStyleName("mail-DetailSubject");
		sender.setStyleName("mail-DetailSender");
		recipient.setStyleName("mail-DetailRecipient");
		body.setStyleName("mail-DetailBody");
	}

	/**
	 * Adjusts the widget's size such that it fits within the window's client
	 * area.
	 */
	public void adjustSize(int windowWidth, int windowHeight) {
		int scrollWidth = windowWidth - scroller.getAbsoluteLeft() - 9;
		if (scrollWidth < 1) {
			scrollWidth = 1;
		}

		int scrollHeight = windowHeight - scroller.getAbsoluteTop() - 9;
		if (scrollHeight < 1) {
			scrollHeight = 1;
		}

		scroller.setPixelSize(scrollWidth, scrollHeight);
	}

	public void setItem(Mail item) {
		subject.setHTML(item.getSubject());
		sender.setHTML("<b>From:</b>&nbsp;" + item.getFromGroup());
		recipient.setHTML("<b>To:</b>&nbsp" + item.getToGroup());
		//date.setHTML("<b>Sent:</b>&nbsp" + item.date);
		body.setHTML(item.getMessage());
	}
}