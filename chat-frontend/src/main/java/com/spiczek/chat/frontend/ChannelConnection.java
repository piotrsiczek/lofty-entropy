package com.spiczek.chat.frontend;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.shared.EventBus;
import com.spiczek.chat.frontend.events.MessageReceivedEvent;


/**
 * @author Piotr Siczek
 */
public class ChannelConnection {

    private String token;
    private static EventBus eventBus;

    public ChannelConnection(String token, EventBus eventBus) {
        Log.info("ChannelConnection constructor");
        this.token = token;
        this.eventBus = eventBus;
        this.connect(token);
    }

    private native void connect(String token) /*-{
        log = @com.spiczek.chat.frontend.ChannelConnection::log(Ljava/lang/String;);
        log("connect");
        log("token: " + token);

        var channel = new $wnd.goog.appengine.Channel(token);
        socket = channel.open();
        socket.onopen = @com.spiczek.chat.frontend.ChannelConnection::onOpen();
        socket.onclose = @com.spiczek.chat.frontend.ChannelConnection::onClose();
        socket.onmessage = messagehelper;
        socket.onerror = errorhelper;

        function messagehelper(message) {
            @com.spiczek.chat.frontend.ChannelConnection::onMessage(Ljava/lang/String;)(message.data);
        }
        function errorhelper(error) {
            @com.spiczek.chat.frontend.ChannelConnection::onError(Ljava/lang/String;I)(error.description, error.code);
        }

        log("end connect");
    }-*/;

    private static native void close() /*-{
        log("closing");
        socket.close();
        log("closed");
    }-*/;

    public static void onOpen() {
        log("onOpen from java");
    }

    public static void onMessage(String message) {
        String[] parts = message.split(";");
        log("message from java: " + parts[0] + " " + parts[1] + " talkId: " + parts[2] + " data: " + parts[3]);
        eventBus.fireEvent(new MessageReceivedEvent(new Long(parts[0]), parts[1], new Long(parts[2]), parts[3]));
    }

    public static void onClose() {
        log("onClose from java");
        //Window.alert("close from java");
    }

    public static void onError(String description, int code) {
        log("error from java " + description + " " + code);
        //Window.alert("error from java " + description + " " + code);
    }

    public static void log(String data) {
        Log.info(data);
    }
}
