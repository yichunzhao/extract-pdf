package com.ynz.pdf.extractpdf.statemachine.context;

import com.ynz.pdf.extractpdf.model.ARKDataModel;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;

public interface ARKLineTextContext extends Context<ARKLineTextState> {
    String getLine();

    ARKDataModel getModel();
}
