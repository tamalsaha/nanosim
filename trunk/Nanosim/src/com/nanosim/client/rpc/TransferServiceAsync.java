package com.nanosim.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TransferServiceAsync {
	void insertBudget(String credit, String id, AsyncCallback<Integer> callback);
}