package com.example.rhuard.savagediceroller;

import android.content.Intent;

import com.example.rhuard.savagediceroller.DiceEngine;
import com.example.rhuard.savagediceroller.DiceResults;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ryan on 9/17/17.
 */

public class FudgeDiceEngine extends DiceEngine implements DiceResults {

    public FudgeDiceEngine() {super();}

    public Hashtable<String, List<Integer>> HandleRoll(int num){
        Hashtable<String, List<Integer>> results = CreateResultsTable();

        int roll;
        int end = 0;
        for(int i = 0; i < num; i++){
            roll = RollDie(6);
            results.get("Rolls").add(roll);
            end += Translate(roll);
        }

        results.get("Final").add(end);

        return results;
    }

    public Hashtable<String, List<Integer>> HandleRoll(int num, int modifier){
        Hashtable<String, List<Integer>> results = HandleRoll(num);
        results.get("Final").set(0, results.get("Final").get(0) + modifier);
        return results;
    }

    public int Translate(int roll){
        int end=0;
        switch(roll){
            case 1:
            case 2:
                end = -1;
                break;
            case 3:
            case 4:
                end = 0;
                break;
            case 5:
            case 6:
                end = 1;
                break;
        }
        return end;
    }
}