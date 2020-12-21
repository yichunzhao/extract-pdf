package com.ynz.pdf.extractpdf.statemachine.state;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;

public interface ARKLineTextState extends State {

    void doAction(ARKLineTextContext context);

}
