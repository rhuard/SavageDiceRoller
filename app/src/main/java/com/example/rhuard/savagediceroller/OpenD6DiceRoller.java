package com.example.rhuard.savagediceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Set;

public class OpenD6DiceRoller extends AppCompatActivity {

    int num_dice;
    boolean wild_die;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_d6_dice_roller);

        setTitle("Open D6");

        num_dice = 1;
        SetNumDiceText(num_dice);

        //Init wild die switch
        Switch mod_switch = (Switch) findViewById(R.id.wildDieSwitch);
        mod_switch.setOnCheckedChangeListener(new OpenD6DiceRoller.SwitchCheckedChangedListener());
    }

    //Listener Classes
    public class SwitchCheckedChangedListener implements CompoundButton.OnCheckedChangeListener{
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            if(isChecked){
                wild_die = true;
            }else{
                wild_die = false;
            }
        }
    }

    private void DisplayResult(String text){
        TextView tv = (TextView) findViewById(R.id.openD6ResultView);
        tv.setText(text);
    }

    //helpers
    private void SetNumDiceText(int num){
        TextView tv = (TextView) findViewById(R.id.openD6numDiceView);
        tv.setText(String.valueOf(num));
    }

    private void UpdateDice(int change){
        num_dice = num_dice + change;
        if(num_dice < 0){
            num_dice = 0;
        }
        SetNumDiceText(num_dice);
    }

    //Button Actions
    public void AddDice(View view){
        UpdateDice(1);
    }

    public void RemoveDice(View view){
        UpdateDice(-1);
    }

}
