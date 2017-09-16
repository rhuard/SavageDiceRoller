package com.example.rhuard.savagediceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.List;

public class SavageDiceRoller extends AppCompatActivity {

    private final int MOD_MAX = 10;
    private SavageDiceEngine _dice_engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savage_dice_roller);

        setTitle("Savage Worlds");

        _dice_engine = new SavageDiceEngine();

        //Init radio buttons
        RadioGroup rg = (RadioGroup) findViewById(R.id.charTypeRadioGroup);
        rg.check(R.id.wildCardRadioButton);
    }

    //Helper Methods
    private String CurrentSpinnerValue() {
        Spinner sp = (Spinner) findViewById(R.id.modifierSpinner);
        return sp.getSelectedItem().toString();
    }

    private boolean CurrentlyWildCard(){
        RadioButton rb = (RadioButton) findViewById(R.id.wildCardRadioButton);
        boolean result;
        if(rb.isChecked()){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    private void DisplayResult(String result){
        TextView textview = (TextView) findViewById(R.id.savageResultView);
        textview.setText(result);
    }

    private void DisplayResult(Hashtable<String, List<Integer>> result){
        TextView result_text = (TextView) findViewById(R.id.savageResultView);
        TextView wild_text = (TextView) findViewById(R.id.savageWwildTextView);
        TextView roll_text = (TextView) findViewById(R.id.savageRollTextView);

        if(result.containsKey("Wildcard")) {
            wild_text.setText("Wild die: " + result.get("Wildcard").toString());
        }else{
            wild_text.setText("");
        }
        roll_text.setText("Roll: " + result.get("Rolls").toString());
        result_text.setText("Final Result: " + result.get("Final").get(0).toString());
    }

    private void ProcessRoll(int size){

        Hashtable<String, List<Integer>> result = null;
        Switch ms = (Switch) findViewById(R.id.modifierSwitch);
        //check if modifier is required
        if(ms.isChecked()){
            //create modifier
            RadioButton mod_rd = (RadioButton) findViewById(R.id.modifierNegButton);
            int mod = Integer.parseInt(CurrentSpinnerValue());
            if (mod_rd.isChecked()){
                mod = -mod;
            }
            result = _dice_engine.HandleRoll(size, mod, CurrentlyWildCard());
        }
        else{
            result = _dice_engine.HandleRoll(size, CurrentlyWildCard());
        }

        if (result != null) {
            DisplayResult(result);
        }else{
            DisplayResult("ERROR");
        }
    }

    //Button Actions
    public void RollD4(View view){ ProcessRoll(4); }

    public void RollD6(View view){
        ProcessRoll(6);
    }

    public void RollD8(View view){
        ProcessRoll(8);
    }

    public void RollD10(View view){
        ProcessRoll(10);
    }

    public void RollD12(View view){
        ProcessRoll(12);
    }
}
