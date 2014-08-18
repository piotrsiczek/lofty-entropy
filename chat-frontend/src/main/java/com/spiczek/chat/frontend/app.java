package com.spiczek.chat.frontend;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.spiczek.chat.frontend.composites.*;
import com.spiczek.chat.shared.*;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class app implements EntryPoint {

    private final ClientServiceAsync clientService = GWT.create(ClientService.class);
    private final MessageServiceAsync messageService = GWT.create(MessageService.class);

    /**
     * This is the entry point method.`
     */
    public void onModuleLoad() {


        clientService.getUserDetails(new AsyncCallback<UserDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.info("details error" + caught.toString());
            }

            @Override
            public void onSuccess(UserDTO user) {
                Log.info("details " + user.toString());
            }
        });


//        userService.test("dupa", new AsyncCallback<String>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                Log.error(caught.toString());
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                Log.info(result);
//            }
//        });

//        clientService.generateFriends(new AsyncCallback<Void>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                Log.error("dupa");
//            }
//
//            @Override
//            public void onSuccess(Void v) {
//                Log.info("Successfylly generated friends.");
//            }
//        });
//
//        for (int i=0; i<1000000000;i++) {
//
//        }

        final Button generateTestButton = new Button("Click me");
        final Label label = new Label();
        final EventBus bus = new SimpleEventBus();

        final MessageComposite messageComposite = new MessageComposite(bus, 9, 9);

        generateTestButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    clientService.getMessage("Hello, World!", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }
            }
        });


        Label label1 = new Label("Get Token");
        final TextBox idBox = new TextBox();
        Button getTokenButton = new Button("getToken");
        HPanel hPanel = new HPanel(label1, idBox, getTokenButton);


        final TextBox reciverIdBox = new TextBox();
        TextArea text = new TextArea();
        Button messageButton = new Button("send message");
        VPanel vPane = new VPanel(reciverIdBox, text, messageButton);




        getTokenButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                messageService.getToken(Integer.parseInt(idBox.getText()), new AsyncCallback<String>() {
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


        vPane.add(generateTestButton);


        Button generateFrinedsButton = new Button("generate friends");
        Button getFrinedsButton = new Button("get friends");

        final VPanel friendVPanel = new VPanel(generateFrinedsButton, getFrinedsButton);

//        generateFrinedsButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent clickEvent) {
//                clientService.generateFriends(new AsyncCallback<Void>() {
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        Log.error("dupa");
//                    }
//
//                    @Override
//                    public void onSuccess(Void v) {
//                        Log.info("Successfylly generated friends.");
//                    }
//                });
//            }
//        });

        FriendCell friendCell = new FriendCell();
        final CellList<UserDTO> cellList = new CellList<UserDTO>(friendCell);
        //TextCell textCell = new TextCell();
        //final CellList<String> cellList = new CellList<String>(textCell);

//        friendsList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
//        // Add a selection model to handle user selection.
//        final SingleSelectionModel<FriendInfo> selectionModel = new SingleSelectionModel<FriendInfo>();
//        friendsList.setSelectionModel(selectionModel);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            public void onSelectionChange(SelectionChangeEvent event) {
//                FriendInfo selected = selectionModel.getSelectedObject();
//                if (selected != null) {
//                    Window.alert("You selected: " + selected.getName());
//                }
//            }
//        });





        // Set a key provider that provides a unique key for each contact. If key is
        // used to identify contacts when fields (such as the name and address)
        // change.

        cellList.setPageSize(10);
        cellList.setKeyboardPagingPolicy(HasKeyboardPagingPolicy.KeyboardPagingPolicy.INCREASE_RANGE);
        cellList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.BOUND_TO_SELECTION);

        // Add a selection model so we can select cells.
        final SingleSelectionModel<UserDTO> selectionModel = new SingleSelectionModel<UserDTO>();
        cellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                //contactForm.setContact(selectionModel.getSelectedObject());
            }
        });





        //PagerPanel pagerPanel = new PagerPanel();
        //pagerPanel.setDisplay(cellList);


        //cellList.setRowCount(100, false);
        ScrollPanel scroll = new ScrollPanel(cellList);

        friendVPanel.add(scroll);

        // Push the data into the widget.


        getFrinedsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                clientService.getFriends(new AsyncCallback<List<UserDTO>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.error("dupa2");
                    }

                    @Override
                    public void onSuccess(List<UserDTO> userDTOs) {
                        Log.info("get data");
                        List<String> items = new ArrayList<String>();
//                        for (UserDTO u : userDTOs) {
////                            Label id = new Label(u.getId() + "");
////                            Label name = new Label(u.getName());
////                            Label surname = new Label(u.getSurname());
////                            HPanel friendHPanel = new HPanel(id, name, surname);
////                            friendVPanel.add(friendHPanel);
//                            items.add(u.getId() + " " + u.getName() + " " + u.getSurname());
//                        }
                        cellList.setRowData(0, userDTOs);

                        Log.info("data displayed");
                    }
                });
            }
        });



        RootPanel.get("slot1").add(label);
        RootPanel.get("slot2").add(hPanel);
        RootPanel.get("slot2").add(vPane);
        RootPanel.get("slot3").add(new FriendComposite());
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
