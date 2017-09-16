package com.example.rhuard.savagediceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

public class OpenD6DiceRoller extends AppCompatActivity {

    private int _num_dice;
    private boolean _wild_die;
    private OpenD6DiceEngine _dice_engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_d6_dice_roller);

        setTitle("Open D6");

        _dice_engine = new OpenD6DiceEngine();

        _num_dice = 1;
        SetNumDiceText(_num_dice);

        //Init wild die switch
        Switch mod_switch = (Switch) findViewById(R.id.wildDieSwitch);
        mod_switch.setOnCheckedChangeListener(new OpenD6DiceRoller.SwitchCheckedChangedListener());
    }

    //Listener Classes
    public class SwitchCheckedChangedListener implements CompoundButton.OnCheckedChangeListener{
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            if(isChecked){
                _wild_die = true;
            }else{
                _wild_die = false;
            }
        }
    }

    private void DisplayResult(String text){
        TextView result_view = (TextView) findViewById(R.id.openD6ResultView);
        TextView roll_view = (TextView) findViewById(R.id.openD6RollView);
        TextView wild_view = (TextView) findViewById(R.id.openD6WildRollView);
        result_view.setText(text);
        roll_view.setText("");
        wild_view.setText("");
    }

    private void DisplayResult(Hashtable<String, List<Integer>> result){
        TextView result_view = (TextView) findViewById(R.id.openD6ResultView);
        TextView roll_view = (TextView) findViewById(R.id.openD6RollView);
        TextView wild_view = (TextView) findViewById(R.id.openD6WildRollView);

        String roll_text = "";
        if(_wild_die){
            wild_view.setText("Wild Die: " + String.valueOf(result.get("WildDie").get(0)));
        }else{
            wild_view.setText("");
        }
        roll_text += "Rolls: " + String.valueOf(result.get("Rolls"));

        roll_view.setText(roll_text);
        result_view.setText("Final Results: " + result.get("Final").get(0));
    }

    //helpers
    private String CurrentSpinnerValue() {
        Spinner sp = (Spinner) findViewById(R.id.modifierSpinner);
        return sp.getSelectedItem().toString();
    }

    private void SetNumDiceText(int num){
        TextView tv = (TextView) findViewById(R.id.openD6numDiceView);
        tv.setText(String.valueOf(num));
    }

    private void UpdateDice(int change){
        _num_dice = _num_dice + change;
        if(_num_dice < 1){
            _num_dice = 1;
        }
        SetNumDiceText(_num_dice);
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
            result = _dice_engine.HandleRoll(_num_dice, _wild_die, mod);
        }
        else{
            result = _dice_engine.HandleRoll(_num_dice, _wild_die);
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

    public void RollDice(View view){ProcessRoll();}

}
