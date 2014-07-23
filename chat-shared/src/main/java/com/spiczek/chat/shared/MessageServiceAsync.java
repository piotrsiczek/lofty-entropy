package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by piotr on 2014-04-27.
 */
public interface MessageServiceAsync {
    /**
     * Utility/Convenience class.
     * Use MessageService.App.getInstance() to access static instance of MessageServiceAsync
     */


    /**
     * Utility/Convenience class.
     * Use MessageService.App.getInstance() to access static instance of MessageServiceAsync
     */
    void sendMessage(int fromId, int toId, String message, AsyncCallback<Void> async);

    /**
     * Utility/Convenience class.
     * Use MessageService.App.getInstance() to access static instance of MessageServiceAsync
     */

}
