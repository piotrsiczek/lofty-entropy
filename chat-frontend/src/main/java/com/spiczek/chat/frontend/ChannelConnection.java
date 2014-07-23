package com.spiczek.chat.frontend;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.spiczek.chat.frontend.events.MessageReceivedEvent;

/**
 * Created by piotr on 2014-04-26.
 */
public class ChannelConnection {

    private String token;
    private static EventBus eventBus;



    public ChannelConnection(String token, EventBus eventBus) {

        this.token = token;
        this.eventBus = eventBus;
        //eventBinder.bindEventHandlers(this, eventBus);
        System.out.println("&&&&&&&&&&&&&&&&&&&&");
        this.connect(token);
        this.publish();
        System.out.println("&&&&&&&&&&&&&&&&&&&&");
    }

    private native void publish() /*-{
        $wnd.alert("publish");
        $wnd.onOpen = @com.spiczek.chat.frontend.ChannelConnection::onOpen();
        $wnd.onClose = @com.spiczek.chat.frontend.ChannelConnection::onClose();
        $wnd.onMessage = @com.spiczek.chat.frontend.ChannelConnection::onMessage(Ljava/lang/String;);
        $wnd.onError = @com.spiczek.chat.frontend.ChannelConnection::onError(Ljava/lang/String;I);
        $wnd.alert("end publish");
    }-*/;

    private native void connect(String token) /*-{

        $wnd.alert(token);
        $wnd.connectChannel(token);

    }-*/;

    public static void onOpen() {
        Window.alert("open from java");
    }

    public static void onMessage(String message) {
        String[] parts = message.split(";");
        Window.alert("message from java: " + parts[0] + " data: " + parts[1]);
        eventBus.fireEvent(new MessageReceivedEvent(new Message(Integer.parseInt(parts[0]), parts[1])));
    }

    public static void onClose() {
        Window.alert("close from java");
    }

    public static void onError(String description, int code) {
        Window.alert("error from java " + description + " " + code);
    }

}
