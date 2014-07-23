package com.spiczek.chat.frontend;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.spiczek.chat.frontend.ChannelConnection;
import com.spiczek.chat.frontend.composites.MessageComposite;
import com.spiczek.chat.shared.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class app implements EntryPoint {

    private final ClientServiceAsync clientService = GWT.create(ClientService.class);
    private final TokenServiceAsync tokenService = GWT.create(TokenService.class);
    private final MessageServiceAsync messageService = GWT.create(MessageService.class);


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button button = new Button("Click me");
        final Label label = new Label();
        final EventBus bus = new SimpleEventBus();

        final MessageComposite messageComposite = new MessageComposite(bus, 9, 9);


        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    clientService.getMessage("Hello, World!", new MyAsyncCallback(label));
                }
                else {
                    label.setText("");
                }
            }
        });


        final TextBox idBox = new TextBox();
        Button sendButton = new Button("getToken");
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.add(idBox);
        hPanel.add(sendButton);
        /*
        sendButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String result = JavaScriptWraper.getInstance().onMyButtonClick();
                label.setText(result);
            }
        });

        */

        final TextBox reciverIdBox = new TextBox();
        TextArea text = new TextArea();
        Button messageButton = new Button("send message");
        VerticalPanel vPane = new VerticalPanel();
        vPane.add(reciverIdBox);
        vPane.add(text);
        vPane.add(messageButton);

        sendButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                tokenService.getToken(Integer.parseInt(idBox.getText()), new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        label.setText("fail");
                    }

                    @Override
                    public void onSuccess(String result) {
                        label.setText("success " + result);
                        ChannelConnection connection = new ChannelConnection(result, bus);
                        messageComposite.setLoginId(Integer.parseInt(idBox.getText()));
                        messageComposite.setReciverId(Integer.parseInt(reciverIdBox.getText()));
                    }
                });

            }
        });
        /*
        sendButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                label.setText("asdf");
                String url = "http://localhost:8088/_ah/api/awesomechat/v1_experimental/token/787/?q=";
                RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
                builder.setHeader("Access-Control-Allow-Origin","*");

                builder.setCallback(new RequestCallback() {
                    @Override
                    public void onResponseReceived(Request request, Response response) {

                        if (response.getStatusCode() == 200) {

                            String data = response.getText();
                            System.out.println(data);
                            data = data.substring(16, data.length() - 4);

                            label.setText("|"+data+"|");
                            ChannelConnection connection = new ChannelConnection(data);
                        }
                        else {
                            System.out.println("error" + response.getStatusCode());
                            label.setText(response.getStatusText());
                        }

                    }

                    @Override
                    public void onError(Request request, Throwable exception) {
                        label.setText("error" + exception.getMessage());
                    }
                });

                try {
                    builder.send();
                    label.setText("end");
                } catch (RequestException e) {
                    e.printStackTrace();
                }

            }
        });
        */



        /*

        text.addKeyDownHandler(new HandlesAllKeyEvents() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                System.out.println(event.);

            }

            @Override
            public void onKeyPress(KeyPressEvent event) {

            }

            @Override
            public void onKeyUp(KeyUpEvent event) {
                event.get

            }
        });
        */



        //final int loginId = Integer.parseInt(idBox.getText());
        //final int receiverId = Integer.parseInt(reciverIdBox.getText());

        messageButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                messageService.sendMessage(Integer.parseInt(idBox.getText()), Integer.parseInt(reciverIdBox.getText()), "message", new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("error");
                    }

                    @Override
                    public void onSuccess(Void result) {
                        Window.alert("success");
                    }
                });
            }
        });




        RootPanel.get("slot1").add(label);
        RootPanel.get("slot2").add(hPanel);
        RootPanel.get("slot2").add(vPane);
        RootPanel.get("slot8").add(messageComposite);


    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;
        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
