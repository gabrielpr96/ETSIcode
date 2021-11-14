package autosig;

import autosig.gui.MainWindow;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.handlers.ExceptionHandleable;

public class AutoExceptionHandle implements ExceptionHandleable{

    private final MainWindow window;
    
    public AutoExceptionHandle(MainWindow window){
        this.window = window;
    }
    
    @Override
    public void handleException(SIGException exception) {
        window.addException(exception);
    }
    
}
