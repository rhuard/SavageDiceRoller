package com.example.rhuard.savagediceroller;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity{

    private final int MOD_MAX = 10;
    private DiceEngine _dice_engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _dice_engine = new DiceEngine();
        RadioGroup rg = (RadioGroup) findViewById(R.id.charTypeRadioGroup);
        rg.check(R.id.wildCardRadioButton);
        RadioGroup mod = (RadioGroup) findViewById(R.id.modifierRadioGroup);
        mod.check(R.id.positiveRadioButton);
        String[] np = new String[MOD_MAX + 1];
        for(int i = 0; i<=MOD_MAX; i++){
            np[i] = String.valueOf(i);
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item , np);
        Spinner ms = (Spinner) findViewById(R.id.modiferSpiner);
        AdapterView.OnItemSelectedListener listener = new ModifierOnItemSelectedListener();
        ms.setAdapter(aa);
        ms.setSelection(0);
        ms.setOnItemSelectedListener(listener);
    }

    private String CurrentSpinnerValue() {
        Spinner sp = (Spinner) findViewById(R.id.modiferSpiner);
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
        TextView textview = (TextView) findViewById(R.id.resultTextView);
        textview.setText(result);
    }

    private void ProcessRoll(int size){

        Integer result = null;
        Switch ms = (Switch) findViewById(R.id.modifierSwitch);

        //check if modifer is required
        if(ms.isChecked()){
            //create modifier
            RadioButton mod_rd = (RadioButton) findViewById(R.id.negativeRadioButton);
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
            DisplayResult(String.valueOf(result));
        }else{
            DisplayResult("ERROR");
        }
    }

    public void RollD4(View view){
        ProcessRoll(4);
    }

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
