package com.example.rhuard.savagediceroller;

import android.hardware.camera2.TotalCaptureResult;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

public class DicePoolRoller extends AppCompatActivity {

    //TODO: implemet dice pool engine to handle rolling mulitple of various dice sizes more gracfully
    private DiceEngine _dice_engine;
    private int _num_d4_dice;
    private int _num_d6_dice;
    private int _num_d8_dice;
    private int _num_d10_dice;
    private int _num_d12_dice;
    private int _num_d20_dice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_pool_roller);

        setTitle("Dice Pool");

        _dice_engine = new DiceEngine();
        //TODO: put this in hashtable so can make updating methods cleaner
        _num_d4_dice = 0;
        _num_d6_dice = 0;
        _num_d8_dice = 0;
        _num_d10_dice = 0;
        _num_d12_dice = 0;
        _num_d20_dice = 0;
    }

    //Helper Methods
    private String CurrentSpinnerValue() {
        Spinner sp = (Spinner) findViewById(R.id.modifierSpinner);
        return sp.getSelectedItem().toString();
    }

    private void DisplayResult(String result){
        TextView textview = (TextView) findViewById(R.id.dicePoolResultsView);
        textview.setText(result);
    }

    private void DisplayResult(Integer final_result, List<Integer> rolls){
        HorizontalScrollView result_scroll = (HorizontalScrollView) findViewById(R.id.dicePoolResultScrollView);
        HorizontalScrollView rolls_scroll = (HorizontalScrollView) findViewById(R.id.dicePoolRollScrollView);
        result_scroll.scrollTo(0,0);
        rolls_scroll.scrollTo(0,0);

        TextView result_text = (TextView) findViewById(R.id.dicePoolResultsView);
        TextView roll_text = (TextView) findViewById(R.id.dicePoolRollView);

        roll_text.setText("Roll: " + rolls.toString());
        result_text.setText("Final Result: " + final_result.toString());
    }

    private void UpdateD4Dice(int change){
        _num_d4_dice = _num_d4_dice + change;
        if(_num_d4_dice < 0){
            _num_d4_dice = 0;
        }
        Setd4NumDiceText(_num_d4_dice);
    }

    private void UpdateD6Dice(int change){
        _num_d6_dice = _num_d6_dice + change;
        if(_num_d6_dice < 0){
            _num_d6_dice = 0;
        }
        Setd6NumDiceText(_num_d6_dice);
    }

    private void UpdateD8Dice(int change){
        _num_d8_dice = _num_d8_dice + change;
        if(_num_d8_dice < 0){
            _num_d8_dice = 0;
        }
        Setd8NumDiceText(_num_d8_dice);
    }

    private void UpdateD10Dice(int change){
        _num_d10_dice = _num_d10_dice + change;
        if(_num_d10_dice < 0){
            _num_d10_dice = 0;
        }
        Setd10NumDiceText(_num_d10_dice);
    }

    private void UpdateD12Dice(int change){
        _num_d12_dice = _num_d12_dice + change;
        if(_num_d12_dice < 0){
            _num_d12_dice = 0;
        }
        Setd12NumDiceText(_num_d12_dice);
    }

    private void UpdateD20Dice(int change){
        _num_d20_dice = _num_d20_dice + change;
        if(_num_d20_dice < 0){
            _num_d20_dice = 0;
        }
        Setd20NumDiceText(_num_d20_dice);
    }

    private void setNumDiceText(int view_id, int num){
        TextView tv = (TextView) findViewById(view_id);
        tv.setText((String.valueOf(num)));
    }

    private void Setd4NumDiceText(int num){
        setNumDiceText(R.id.d4Total, num);
    }
    private void Setd6NumDiceText(int num){
        setNumDiceText(R.id.d6Total, num);
    }
    private void Setd8NumDiceText(int num){
        setNumDiceText(R.id.d8Total, num);
    }
    private void Setd10NumDiceText(int num){
        setNumDiceText(R.id.d10Total, num);
    }
    private void Setd12NumDiceText(int num){
        setNumDiceText(R.id.d12Total, num);
    }
    private void Setd20NumDiceText(int num){
        setNumDiceText(R.id.d20Total, num);
    }

    private void ProcessRoll(){
        Hashtable<String, List<Integer>> d4result = null;
        Hashtable<String, List<Integer>> d6result = null;
        Hashtable<String, List<Integer>> d8result = null;
        Hashtable<String, List<Integer>> d10result = null;
        Hashtable<String, List<Integer>> d12result = null;
        Hashtable<String, List<Integer>> d20result = null;

        Integer total = 0;
        List<Integer> rolls = new ArrayList<Integer>();
        int end = 0;
        for(int i = 0; i < _num_d4_dice; i++) {
            Integer roll = _dice_engine.HandleRoll(4).get("Final").get(0);
            total += roll;
            rolls.add(roll);
        }
        for(int i = 0; i < _num_d6_dice; i++) {
            Integer roll = _dice_engine.HandleRoll(6).get("Final").get(0);
            total += roll;
            rolls.add(roll);
        }
        for(int i = 0; i < _num_d8_dice; i++) {
            Integer roll = _dice_engine.HandleRoll(8).get("Final").get(0);
            total += roll;
            rolls.add(roll);
        }
        for(int i = 0; i < _num_d10_dice; i++) {
            Integer roll = _dice_engine.HandleRoll(10).get("Final").get(0);
            total += roll;
            rolls.add(roll);
        }
        for(int i = 0; i < _num_d12_dice; i++) {
            Integer roll = _dice_engine.HandleRoll(12).get("Final").get(0);
            total += roll;
            rolls.add(roll);
        }
        for(int i = 0; i < _num_d20_dice; i++) {
            Integer roll = _dice_engine.HandleRoll(20).get("Final").get(0);
            total += roll;
            rolls.add(roll);
        }

        Switch ms = (Switch) findViewById(R.id.modifierSwitch);
        //check if modifier is required
        int mod = 0;
        if(ms.isChecked()){
            //create modifier
            RadioButton mod_rd = (RadioButton) findViewById(R.id.modifierNegButton);
            mod = Integer.parseInt(CurrentSpinnerValue());
            if (mod_rd.isChecked()){
                mod = -mod;
            }
        }

        total += mod;
        Collections.sort(rolls);
        DisplayResult(total, rolls);
    }

    //dice buttons
    //Button Actions
    public void AddD4(View view){
        UpdateD4Dice(1);
    }

    public void RemoveD4(View view){
        UpdateD4Dice(-1);
    }

    public void AddD6(View view){
        UpdateD6Dice(1);
    }

    public void RemoveD6(View view){
        UpdateD6Dice(-1);
    }

    public void AddD8(View view){
        UpdateD8Dice(1);
    }

    public void RemoveD8(View view){
        UpdateD8Dice(-1);
    }

    public void AddD10(View view){
        UpdateD10Dice(1);
    }

    public void RemoveD10(View view){
        UpdateD10Dice(-1);
    }

    public void AddD12(View view){
        UpdateD12Dice(1);
    }

    public void RemoveD12(View view){
        UpdateD12Dice(-1);
    }
    public void AddD20(View view){
        UpdateD20Dice(1);
    }

    public void RemoveD20(View view){
        UpdateD20Dice(-1);
    }

    public void RollDice(View view){ ProcessRoll(); }
}
