package com.spiczek.chat.frontend;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.spiczek.chat.frontend.composites.friends.FriendCell;
import com.spiczek.chat.frontend.composites.panels.VPanel;
import com.spiczek.chat.frontend.composites.talks.TalkComposite;
import com.spiczek.chat.frontend.composites.test.FriendComposite;
import com.spiczek.chat.frontend.composites.toolbars.FriendToolBar;
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
    private TextBox friendIdBox;
    private SimpleEventBus eventBus;
    private FriendComposite fr;

//    private TalkComposite talkComposite;
    /**
     * This is the entry point method.`
     */
    public void onModuleLoad() {

        //fr = new FriendComposite();

        //RootPanel.get("slot3").add(fr);

        initWidgets();

        //fr.initialize();
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

//        final Button generateTestButton = new Button("Click me");
//        final Label label = new Label();


//        final MessageComposite messageComposite = new MessageComposite(eventBus, 9, 9);

//        generateTestButton.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                if (label.getText().equals("")) {
//                    clientService.getMessage("Hello, World!", new AsyncCallback<String>() {
//                        @Override
//                        public void onFailure(Throwable caught) {
//                            label.setText("Failed to receive answer from server!");
//                        }
//
//                        @Override
//                        public void onSuccess(String result) {
//                            label.getElement().setInnerHTML(result);
//                        }
//                    });
//                } else {
//                    label.setText("");
//                }
//            }
//        });



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


//        getFrinedsButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent clickEvent) {
//                clientService.getFriends(new AsyncCallback<List<UserDTO>>() {
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        Log.error("dupa2");
//                    }
//
//                    @Override
//                    public void onSuccess(List<UserDTO> userDTOs) {
//                        Log.info("get data");
//                        List<String> items = new ArrayList<String>();
////                        for (UserDTO u : userDTOs) {
//////                            Label id = new Label(u.getId() + "");
//////                            Label name = new Label(u.getName());
//////                            Label surname = new Label(u.getSurname());
//////                            HPanel friendHPanel = new HPanel(id, name, surname);
//////                            friendVPanel.add(friendHPanel);
////                            items.add(u.getId() + " " + u.getName() + " " + u.getSurname());
////                        }
//                        cellList.setRowData(0, userDTOs);
//
//                        Log.info("data displayed");
//                    }
//                });
//            }
//        });

        //RootPanel.get("slot3").add(new FriendComposite());
    }

    private void initWidgets() {
        eventBus = new SimpleEventBus();

        clientService.getUserDetails(new AsyncCallback<UserDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error(caught.toString());
                Window.Location.replace("/login");
            }

            @Override
            public void onSuccess(UserDTO user) {
                Log.info("user details " + user.toString());
                generateToken(user.getId());
                initFriendsWidget(user.getFriendKey());
                initTalksWidget(user);
            }
        });
    }

    private void generateToken(Long userId) {
        messageService.getToken(userId, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error(caught.toString());
            }

            @Override
            public void onSuccess(String result) {
                Log.info("success " + result);
                ChannelConnection connection = new ChannelConnection(result, eventBus);
            }
        });
    }

    private void initFriendsWidget(Long friendKey) {
        final FriendComposite friendComposite = new FriendComposite(eventBus);
        friendComposite.addTitlePanel(new FriendToolBar(eventBus));
        RootPanel.get("leftSlot").add(friendComposite);

        clientService.getFriends(friendKey, new AsyncCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error(caught.toString());
            }

            @Override
            public void onSuccess(List<UserDTO> result) {

                if (result == null) {
                    Log.error("no friends");
                }
                else
                    friendComposite.loadData(result);
            }
        });

    }

    private void initTalksWidget(final UserDTO user) {
        final TalkComposite talkComposite = new TalkComposite(user, eventBus);
        RootPanel.get("centerSlot").add(talkComposite);
    }

}
