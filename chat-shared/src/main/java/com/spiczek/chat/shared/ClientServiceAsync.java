package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);
}
