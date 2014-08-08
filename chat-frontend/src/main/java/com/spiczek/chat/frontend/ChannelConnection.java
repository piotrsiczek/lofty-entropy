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
        //eventBinder.bindEventHandlers(this, eventBus);
        //this.publish();
        this.connect(token);

    }

//    private native void publish() /*-{
//
//       this.@com.spiczek.chat.frontend.ChannelConnection::log(Ljava/lang/String;)("published log");
////        alert("asdf");
//        $wnd.log = @com.spiczek.chat.frontend.ChannelConnection::log(Ljava/lang/String;);
//        $wnd.log("publish");
//        //$wnd.alert("publish");
//        $wnd.onOpen = @com.spiczek.chat.frontend.ChannelConnection::onOpen();
//        $wnd.onClose = @com.spiczek.chat.frontend.ChannelConnection::onClose();
//        $wnd.onMessage = @com.spiczek.chat.frontend.ChannelConnection::onMessage(Ljava/lang/String;);
//        $wnd.onError = @com.spiczek.chat.frontend.ChannelConnection::onError(Ljava/lang/String;I);
//        $wnd.log("end publish");
//        //$wnd.alert("end publish");
//    }-*/;

//    private native void connect(String token) /*-{
//        //$wnd.alert(token);
//        log("token: " + token);
//        $wnd.connectChannel(token);
//
//    }-*/;

    private native void connect(String token) /*-{
        log = @com.spiczek.chat.frontend.ChannelConnection::log(Ljava/lang/String;);
        log("connect");
        log("token: " + token);

        var channel = new $wnd.goog.appengine.Channel(token);
        var socket = channel.open();
        socket.onopen = @com.spiczek.chat.frontend.ChannelConnection::onOpen();
        socket.onclose = @com.spiczek.chat.frontend.ChannelConnection::onClose();
        socket.onmessage = messagehelper;
        socket.onerror = errorhelper;

        function messagehelper(message) {
            this.@com.spiczek.chat.frontend.ChannelConnection::onMessage(Ljava/lang/String;)(message.data);
        }
        function errorhelper(error) {
            this.@com.spiczek.chat.frontend.ChannelConnection::onError(Ljava/lang/String;I)(error.description, error.code);
        }

        log("end connect");
    }-*/;

    public static void onOpen() {
        log("onOpen from java");
        //Window.alert("open from java");
    }

    public static void onMessage(String message) {
        String[] parts = message.split(";");
        log("message from java: " + parts[0] + " data: " + parts[1]);
        //Window.alert("message from java: " + parts[0] + " data: " + parts[1]);
        eventBus.fireEvent(new MessageReceivedEvent(new Message(Integer.parseInt(parts[0]), parts[1])));
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
