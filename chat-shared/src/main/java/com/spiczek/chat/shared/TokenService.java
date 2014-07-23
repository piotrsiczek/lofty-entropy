package com.spiczek.chat.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by piotr on 2014-04-27.
 */
@RemoteServiceRelativePath("TokenService")
public interface TokenService extends RemoteService {
    public String getToken(int id);

    /**
     * Utility/Convenience class.
     * Use TokenService.App.getInstance() to access static instance of TokenServiceAsync
     */
    public static class App {
        private static final TokenServiceAsync ourInstance = (TokenServiceAsync) GWT.create(TokenService.class);

        public static TokenServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
