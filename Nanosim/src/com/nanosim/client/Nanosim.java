package com.nanosim.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.nanosim.client.event.ISigninHandler;
import com.nanosim.client.icons.NanosimImages;
import com.nanosim.client.internal.NanosimConstants;
import com.nanosim.client.rpc.SigninService;
import com.nanosim.client.rpc.SigninServiceAsync;
import com.nanosim.model.Person;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Nanosim implements EntryPoint, ResizeHandler {
	/**
	 * The static images used throughout Nanosim.
	 */
	public static final NanosimImages images = (NanosimImages) GWT
			.create(NanosimImages.class);

	/**
	 * The static constants used throughout Nanosim.
	 */
	public static final NanosimConstants constants = (NanosimConstants) GWT
			.create(NanosimConstants.class);

	private final SigninServiceAsync signinService = SigninService.Util
			.getInstance();

	private CookieHelper cookieHelper = CookieHelper.Util.getInstance();

	private static Nanosim instance;

	public static Nanosim getInstance() {
		if (instance == null) {
			instance = GWT.create(Nanosim.class);
		}
		return instance;
	}

	private Person person;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		instance = this;

		// for test
		signinService.signin("ibm", "demo", new AsyncCallback<Person>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Person result) {
				person = result;
				cookieHelper.setIsLoggedIn(true);
				setHomeScreen();
			}
		});
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();

//		if (!cookieHelper.getIsLoggedIn()) {
//			setSigninScreen();
//		} else
//			setHomeScreen();
	}

	private void setSigninScreen() {
		SigninScreen signinScreen = new SigninScreen();
		signinScreen.addLoginHandler(new ISigninHandler() {

			@Override
			public void OnSuccess(Person p) {
				person = p;
				cookieHelper.setIsLoggedIn(true);
				setHomeScreen();
			}
		});
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		rootPanel.add(signinScreen);
	}

	private HomeScreen homeScreen;

	private void setHomeScreen() {
		homeScreen = new HomeScreen(person);
		Window.addResizeHandler(this);
		Window.enableScrolling(true);
		Window.setMargin("0px");

		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		rootPanel.add(homeScreen);

		// Call the window resized handler to get the initial sizes setup.
		// Doing this in a deferred command causes it to occur after all
		// widgets' sizes have been computed by the browser.
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				onWindowResized(homeScreen, Window.getClientWidth(), Window
						.getClientHeight());
			}
		});
		onWindowResized(homeScreen, Window.getClientWidth(), Window
				.getClientHeight());
	}

	@Override
	public void onResize(ResizeEvent event) {
		onWindowResized(homeScreen, event.getWidth(), event.getHeight());
	}

	public void onWindowResized(HomeScreen homeScreen, int width, int height) {
		// if (homeScreen != null && homeScreen.leftPanel != null) {
		// // Adjust the shortcut panel and detail area to take up the
		// // available room in the window.
		// int shortcutHeight = height - homeScreen.leftPanel.getAbsoluteTop()
		// - 8;
		// if (shortcutHeight < 1) {
		// shortcutHeight = 1;
		// }
		// homeScreen.leftPanel.setHeight(shortcutHeight + "px");
		//
		// // Give the right panel widget a chance to resize itself as well.
		// homeScreen.rightPanel.setPixelSize(width, height);
		// }
	}

	public void Logout() {
		cookieHelper.setIsLoggedIn(false);
		setSigninScreen();
	}
}