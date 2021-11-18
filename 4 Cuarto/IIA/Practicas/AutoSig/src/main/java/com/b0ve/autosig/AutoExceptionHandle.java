package com.b0ve.autosig;

import com.b0ve.autosig.gui.MainWindow;
import com.b0ve.sig.utils.exceptions.SIGException;
import com.b0ve.sig.utils.exceptions.ExceptionHandleable;

public class AutoExceptionHandle implements ExceptionHandleable {

    private final MainWindow window;

    public AutoExceptionHandle(MainWindow window) {
        this.window = window;
    }

    @Override
    public void handleException(SIGException exception) {
        window.addException(exception);
    }

}
