package com.spiczek.chat.frontend.composites.friends;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.spiczek.chat.frontend.composites.panels.VPanel;
import com.spiczek.chat.shared.ClientService;
import com.spiczek.chat.shared.ClientServiceAsync;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;


/**
 * @author Piotr Siczek
 */
public class FriendComposite extends Composite {

    interface FriendCompositeUiBinder extends UiBinder<HTMLPanel, FriendComposite> {}
    private static FriendCompositeUiBinder uiBinder = GWT.create(FriendCompositeUiBinder.class);

    private static final ClientServiceAsync clientService = GWT.create(ClientService.class);
    private FriendDataProvider dataProvider;

    @UiField
    PagerPanel pagerPanel;

    @UiField
    RangeLabelPager rangeLabelPager;

    private CellList<UserDTO> cellList;

    public FriendComposite() {
        this.initWidget(uiBinder.createAndBindUi(this));
        initialize();
    }

    public void initialize() {
        cellList = new CellList<UserDTO>(new FriendCell());
        cellList.setPageSize(10);

        cellList.setKeyboardPagingPolicy(HasKeyboardPagingPolicy.KeyboardPagingPolicy.INCREASE_RANGE);
        cellList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.BOUND_TO_SELECTION);

        final SingleSelectionModel<UserDTO> selectionModel = new SingleSelectionModel<UserDTO>();

        cellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                showContact(selectionModel.getSelectedObject());
            }
        });


        //Add the CellList to the data provider in the database.
        dataProvider = new FriendDataProvider();

        cellList.setRowCount(100, false);
        dataProvider.addDataDisplay(cellList);

        pagerPanel.setDisplay(cellList);
        rangeLabelPager.setDisplay(cellList);
    }

    public void showContact(UserDTO user) {
        Window.alert(user.getName());
    }

    private static class FriendDataProvider extends AsyncDataProvider<UserDTO> {

        private int startIndex = 0;
        private Long lastId = null;
        /**
         * {@link #onRangeChanged(HasData)} is called when the table requests a new
         * range of data. You can push data back to the displays using
         * {@link #updateRowData(int, List)}.
         */
        @Override
        protected void onRangeChanged(HasData<UserDTO> display) {
            // Get the new range.
            Log.info("Range changed");
            //final Range range = display.getVisibleRange();
            //final int start = range.getStart();
            //final int length = range.getLength();

//            Long userId;
//            if (startIndex == 0)
//                userId = null;
//            else {
//                //Range r = display.getVisibleRange();
//                //List<UserDTO> t = display.getVisibleItems();
//
//                //userId = display.getVisibleItem(startIndex).getId();
//            }

//            clientService.getFriends(lastId, 10, new AsyncCallback<List<UserDTO>>() {
//                @Override
//                public void onFailure(Throwable caught) {
//                    Log.error(caught.toString());
//                }
//
//                @Override
//                public void onSuccess(List<UserDTO> result) {
//                    Log.info("Get partial friends");
//                    //pobieram ostatni element
//                    updateRowData(startIndex, result);
//                    lastId = result.get(result.size()-1).getId();
//                    startIndex+=10;
//                }
//            });
        }
    }



































    private VPanel p = new VPanel();

    private void create1() {
        CellList<String> cellList = new CellList<String>(new TextCell());

        // Create a list data provider.
        final ListDataProvider<String> dataProvider = new ListDataProvider<String>();

        // Add the cellList to the dataProvider.
        dataProvider.addDataDisplay(cellList);

        // Create a form to add values to the data provider.
        final TextBox valueBox = new TextBox();
        valueBox.setText("Enter new value");
        Button addButton = new Button("Add value", new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Get the value from the text box.
                String newValue = valueBox.getText();

                // Get the underlying list from data dataProvider.
                List<String> list = dataProvider.getList();

                // Add the value to the list. The dataProvider will update the cellList.
                list.add(newValue);
            }
        });

        // Add the widgets to the root panel.
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(valueBox);
        vPanel.add(addButton);
        vPanel.add(cellList);

        p.add(vPanel);
    }

    public void create2() {
        // Create a CellList.
        CellList<String> cellList = new CellList<String>(new TextCell());

        // Create a data provider.
        FriendDataProvider dataProvider = new FriendDataProvider();

        // Add the cellList to the dataProvider.
        dataProvider.addDataDisplay(null);

        // Create paging controls.
        SimplePager pager = new SimplePager();
        pager.setDisplay(cellList);

        // Add the widgets to the root panel.
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(pager);
        vPanel.add(cellList);

        p.add(vPanel);
    }

//    static class ContactCell extends AbstractCell<ContactInfo> {
//        /**
//         * The html of the image used for contacts.
//         */
//        private final String imageHtml;
//
//        public ContactCell(ImageResource image) {
//            this.imageHtml = AbstractImagePrototype.create(image).getHTML();
//        }
//
//        public ContactCell() {
//            imageHtml= "asdf";
//        }
//
//        @Override
//        public void render(Context context, ContactInfo value, SafeHtmlBuilder sb) {
//            // Value can be null, so do a null check..
//            if (value == null) {
//                return;
//            }
//
//            sb.appendHtmlConstant("<table>");
//
//            // Add the contact image.
//            sb.appendHtmlConstant("<tr><td rowspan='3'>");
//            sb.appendHtmlConstant(imageHtml);
//            sb.appendHtmlConstant("</td>");
//
//            // Add the name and address.
//            sb.appendHtmlConstant("<td style='font-size:95%;'>");
//            sb.appendEscaped(value.getFullName());
//            sb.appendHtmlConstant("</td></tr><tr><td>");
//            sb.appendEscaped(value.getAddress());
//            sb.appendHtmlConstant("</td></tr></table>");
//        }
//    }




//    private void create() {
//        // Create a cell to render each value.
//        TextCell textCell = new TextCell();
//
//        // Create a CellList that uses the cell.
//        this.cellList = new CellList<String>(textCell);
//        cellList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
//
//        // Add a selection model to handle user selection.
//        final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
//        cellList.setSelectionModel(selectionModel);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            public void onSelectionChange(SelectionChangeEvent event) {
//                String selected = selectionModel.getSelectedObject();
//                if (selected != null) {
//                    Window.alert("You selected: " + selected);
//                }
//            }
//        });
//
//        cellList.setRowCount(DAYS.size(), true);
//
//        // Push the data into the widget.
//        cellList.setRowData(0, DAYS);
//
//        p.add(cellList);
//    }
}
