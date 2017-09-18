package com.example.rhuard.savagediceroller;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temporary until being developed
        Button DicePoolButton = (Button) findViewById(R.id.dicePoolDollerButton);
        DicePoolButton.setEnabled(false);

    }

    public void StartSavageDieRoller(View view){
        Intent savage_intent = new Intent(this, SavageDiceRoller.class);
        startActivity(savage_intent);
    }

    public void StartOpenD6DieRoller(View view){
        Intent opend6_intent = new Intent(this, OpenD6DiceRoller.class);
        startActivity(opend6_intent);
    }

    public void StartFudgeDieRoller(View view){
        Intent fudge_intent = new Intent(this, FudgeDiceRoller.class);
        startActivity(fudge_intent);
    }
}
