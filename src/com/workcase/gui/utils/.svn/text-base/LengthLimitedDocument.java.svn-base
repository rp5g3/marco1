package com.workcase.gui.utils;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class LengthLimitedDocument extends PlainDocument {
    private int _limit;

    public LengthLimitedDocument(int limit, JTextField field) {
        super();
        _limit = limit;
        field.setDocument(this);
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str != null && (getLength() + str.length()) <= _limit) {
            super.insertString(offset, str, attr);
        }
    }
}