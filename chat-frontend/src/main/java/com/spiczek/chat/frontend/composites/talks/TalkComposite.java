package com.spiczek.chat.frontend.composites.talks;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.spiczek.chat.frontend.composites.messages.MessageComposite;
import com.spiczek.chat.frontend.composites.toolbars.ArchiveTalkToolBar;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.composites.xarchives.messages.ArchiveMessageComposite;
import com.spiczek.chat.frontend.composites.xarchives.talks.ArchiveTalkRenderer;
import com.spiczek.chat.frontend.events.*;
import com.spiczek.chat.shared.MessageService;
import com.spiczek.chat.shared.MessageServiceAsync;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class TalkComposite extends Composite {
    interface TalkCompositeUiBinder extends UiBinder<HTMLPanel, TalkComposite> {}
    private static TalkCompositeUiBinder uiBinder = GWT.create(TalkCompositeUiBinder.class);

    interface MyEventBinder extends EventBinder<TalkComposite> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    private final MessageServiceAsync messageService = GWT.create(MessageService.class);

    @UiField HTMLPanel talkPanel;

    private EventBus eventBus;
    private UserDTO user;
    private List<MessageComposite> talks = new ArrayList<MessageComposite>();

    public TalkComposite(UserDTO user, EventBus eventBus) {
        this.initWidget(uiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);
        this.eventBus = eventBus;
        this.user = user;
    }

    private MessageComposite createTalkPanel(UserDTO friend, Long talkKey) {
        String displayName = user.getName() + " " + user.getSurname();
        MessageComposite messageComposite = new MessageComposite(eventBus, talkKey, user.getId(), displayName, friend.getId(), friend.getName());
        talks.add(messageComposite);
        talkPanel.add(messageComposite);
        return messageComposite;
    }

    private void createTalk(UserDTO friend, Long talkKey, String message) {
        Log.info("creating talk width " + friend.getId());
        MessageComposite messageComposite = createTalkPanel(friend, talkKey);
        messageComposite.showMessage(message);
    }

    private void createTalk(final UserDTO friend) {
        Log.info("creating talk width " + friend.getId());
        MessageComposite m = findTalk(friend.getId());
        if (m == null) {
            messageService.createTalk(user.getId(), user.getChatKey(), friend.getChatKey(), friend.getId(), new AsyncCallback<Long>() {
                @Override
                public void onFailure(Throwable caught) {
                    Log.error(caught.toString());
                }

                @Override
                public void onSuccess(Long talkKey) {
                    createTalkPanel(friend, talkKey);
                }
            });
        }
    }

    private MessageComposite findTalk(Long friendId) {
        for (MessageComposite m : talks) {
            if (m.getReceiverId().equals(friendId)) {
                return m;
            }
        }
        return null;
    }

    @EventHandler
    public void onTalkOpened(TalkOpenEvent event) {
        createTalk(event.getFriend());
    }

    @EventHandler
    public void onArchiveTalkOpened(ArchiveTalkOpenEvent event) {
        messageService.getTalks(user.getChatKey(), event.getFriend().getId(), new AsyncCallback<List<TalkDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error(caught.toString());
            }

            @Override
            public void onSuccess(List<TalkDTO> result) {
                Log.info("get talks");
                ListPanel<TalkDTO> listPanel = new ListPanel<TalkDTO>(eventBus, new ArchiveTalkRenderer(), user);
                listPanel.getElement().getStyle().setProperty("width", "30%");
                listPanel.setToolBar(new ArchiveTalkToolBar());
                listPanel.addItems(result);
                talkPanel.add(listPanel);
            }
        });
    }

    @EventHandler
    public void onArchiveMessageOpened(ArchiveMessageOpenEvent event) {
        messageService.getMessages(event.getTalkKey, new AsyncCallback<List<MessageDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.info(caught.toString());
            }

            @Override
            public void onSuccess(List<MessageDTO> result) {
                Log.info("get messages");
                //Todo usuwanie
                ArchiveMessageComposite archiveMessageComposite = new ArchiveMessageComposite(eventBus, user, result);
                talkPanel.add(archiveMessageComposite);
            }
        });

    }

//    @EventHandler
//    public void onTalkClosed(TalkClosedEvent event) {
//        MessageComposite messageComposite = event.getMessageComposite();
//        talkPanel.remove(messageComposite);
//        talks.remove(messageComposite);
//    }

    @EventHandler
    public void onCompositeClosed(CompositeCloseEvent event) {
        talkPanel.remove(event.getComposite());
        talks.remove(event.getComposite());
        //Todo usuwa cos innego
    }

    @EventHandler
    void onEmailLoaded(MessageReceivedEvent message) {
        Log.info("received message: " + message.getSenderId() + " talkId: " + message.getTalkId() + " data: " + message.getData());
        boolean isFound = false;
        for (MessageComposite m : talks) {
            //Todo sprawdzenie
            //if (m.getReceiverId().equals(message.getSenderId())) {
            if (m.getTalkKey().equals(message.getTalkId())) {
                m.showMessage(message.getData());
                isFound = true;
            }
            //else if friend exists
        }

        if (!isFound) {
            UserDTO u = new UserDTO(message.getSenderId(), message.getUserName(), "");
            createTalk(u, message.getTalkId(), message.getData());
        }
    }
}