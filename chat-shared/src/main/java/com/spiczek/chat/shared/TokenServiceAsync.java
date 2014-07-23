package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by piotr on 2014-04-27.
 */
public interface TokenServiceAsync {
    void getToken(int id, AsyncCallback<String> async);
}
