package com.example.rhuard.savagediceroller;

import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by rhuard on 9/14/17.
 */

public class ModifierOnItemSelectedListener implements AdapterView.OnItemSelectedListener{

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        parent.setSelection(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //nothing to do
    }

}
