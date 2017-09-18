package com.example.rhuard.savagediceroller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 * Created by rhuard on 9/15/17.
 */

//basic dice roller
public class DiceEngine implements DiceResults {

    public Random rand;

    public DiceEngine(){
        rand = new Random(System.currentTimeMillis());
    }

    public Hashtable <String, List<Integer>> CreateResultsTable(){
        Hashtable<String, List<Integer>> results;
        results = new Hashtable<String, List<Integer>>();
        List<Integer> rolls = new ArrayList<Integer>();
        List<Integer> end = new ArrayList<Integer>();
        results.put("Rolls", rolls);//always have a collection of rolls
        results.put("Final", end); //always have an end result
        return results;
    }

    public int RollDie(int size){
        return rand.nextInt(size) + 1;
    }

    public int RollDie(int size, int modifier){
        return RollDie(size) + modifier;
    }

    public Hashtable<String, List<Integer>> HandleRoll(int size){
        Hashtable<String, List<Integer>> results = CreateResultsTable();
        int result = RollDie(size);
        results.get("Rolls").add(result);
        results.get("Final").add(result);
        return results;
    }

    public Hashtable<String, List<Integer>> HandleRoll(int size, int modifier) {
        Hashtable<String, List<Integer>> results = CreateResultsTable();
        int result = RollDie(size) + modifier;
        results.get("Rolls").add(result);
        results.get("final").add(result);
        return results;
    }

    public int SumRolls(List<Integer> rolls){
        int sum = 0;
        for (int i : rolls){
            sum += i;
        }
        return sum;
    }
}
