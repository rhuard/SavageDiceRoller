package com.example.rhuard.savagediceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class FudgeDiceRoller extends AppCompatActivity {

    private int _num_dice;
    private FudgeDiceEngine _dice_engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fudge_dice_roller);

        setTitle("Fudge");

        _dice_engine = new FudgeDiceEngine();

        _num_dice = 4;
        SetNumDiceText(_num_dice);
    }

    //Helpers
    private void SetNumDiceText(int num){
        TextView tv = (TextView) findViewById(R.id.fudgeDiceTextView);
        tv.setText(String.valueOf(num));
    }

    private void UpdateDice(int change){
        _num_dice = _num_dice + change;
        if(_num_dice < 1){
            _num_dice = 1;
        }
        SetNumDiceText(_num_dice);
    }

    private String CurrentSpinnerValue() {
        Spinner sp = (Spinner) findViewById(R.id.modifierSpinner);
        return sp.getSelectedItem().toString();
    }

    private String TranslateDice(List<Integer> rolls){
        List<String> translated = new ArrayList<String>();
        for(Integer r : rolls){
            int t = _dice_engine.Translate(r);
            String symbol = " ";
            switch(t){
                case -1:
                    symbol = "-";
                    break;
                case 0:
                    symbol = "0";
                    break;
                case 1:
                    symbol = "+";
                    break;
            }

            translated.add(symbol);
        }
        return String.valueOf(translated);
    }

    private void DisplayResult(String text){
        TextView result_view = (TextView) findViewById(R.id.fudgeResultView);
        TextView roll_view = (TextView) findViewById(R.id.fudgeDieRollView);
        result_view.setText(text);
        roll_view.setText("");
    }

    private void DisplayResult(Hashtable<String, List<Integer>> result){
        HorizontalScrollView result_scroll = (HorizontalScrollView) findViewById(R.id.fudgeDiceResultsScrollView);
        HorizontalScrollView rolls_scroll = (HorizontalScrollView) findViewById(R.id.fudgeRollsScrollView);
        result_scroll.scrollTo(0,0);
        rolls_scroll.scrollTo(0,0);

        String rolls = TranslateDice(result.get("Rolls"));
        TextView result_view = (TextView) findViewById(R.id.fudgeResultView);
        TextView roll_view = (TextView) findViewById(R.id.fudgeDieRollView);

        roll_view.setText("Rolls: " + rolls);
        result_view.setText("Final Results: " + result.get("Final").get(0));
    }

    private void ProcessRoll(){
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
            result = _dice_engine.HandleRoll(_num_dice, mod);
        }
        else{
            result = _dice_engine.HandleRoll(_num_dice);
        }

        if (result != null) {
            DisplayResult(result);
        }else{
            DisplayResult("ERROR");
        }
    }

    //Button Actions
    public void AddDice(View view){
        UpdateDice(1);
    }

    public void RemoveDice(View view){
        UpdateDice(-1);
    }

    public void RollDice(View view){ ProcessRoll(); }
}
