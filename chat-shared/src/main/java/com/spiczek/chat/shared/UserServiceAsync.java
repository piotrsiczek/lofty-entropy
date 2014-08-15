package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

    void test(String data, AsyncCallback<String> async);
}
