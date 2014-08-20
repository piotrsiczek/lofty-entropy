package com.spiczek.chat.frontend.composites.widgets;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;

/**
 * @author Piotr Siczek
 */
public class TextBox extends com.google.gwt.user.client.ui.TextBox {

    private Button enterButton;

    public TextBox() {
        super();
    }

    public void setEnterButton(Button enterButton) {
        this.enterButton = enterButton;
        initialize();
    }

    private void initialize() {
        this.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getUnicodeCharCode() == KeyCodes.KEY_ENTER) {
                    enterButton.click();
                }
            }
        });
    }
}
