package com.example.rhuard.savagediceroller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by rhuard on 9/14/17.
 */

//Savage worlds dice roller (handles raises and wildcards)
public class SavageDiceEngine extends DiceEngine implements DiceResults {

    public SavageDiceEngine(){
        super();
    }

    //Roll exploding dice creating new results table
    private Hashtable<String, List<Integer>> RollExplodingDice (int size){
        Hashtable<String, List<Integer>> results = CreateResultsTable();
        int result = 0;//default to 0 since no die can have size 0
        do{
            result = RollDie(size);
            results.get("Rolls").add(result);
        }while(result == size);

        return results;
    }

    //Add exploding dice roll to existing results table
    private Hashtable<String, List<Integer>> AddExplodingDiceRoll (int size, Hashtable<String, List<Integer>> results, String results_key){
        int result = 0;//default to 0 since no die can have size 0
        do{
            result = RollDie(size);
            if(!results.containsKey(results_key)){
                results.put(results_key, new ArrayList<Integer>());
            }
            results.get(results_key).add(result);
        }while(result == size);

        return results;
    }

    //Override because dice can explode
    @Override
    public Hashtable<String, List<Integer>> HandleRoll(int size){
        Hashtable<String, List<Integer>> results = RollExplodingDice(size);
        int sum = SumRolls(results.get("Rolls"));
        results.get("Final").add(sum);
        return results;
    }

    //Override because Dice can explode
    @Override
    public Hashtable<String, List<Integer>>  HandleRoll(int size, int modifier){
        Hashtable<String, List<Integer>> results = HandleRoll(size);
        int sum = SumRolls(results.get("Rolls"));
        results.get("Final").add(sum + modifier);
        return results;
    }

    public Hashtable<String, List<Integer>> HandleRoll(int size, boolean wildcard){
        Hashtable<String, List<Integer>> results = null;
        if(wildcard){
            results = RollExplodingDice(size);
            results = AddExplodingDiceRoll(6, results, "Wildcard");
            int sum = SumRolls(results.get("Rolls"));
            int wild_sum = SumRolls(results.get("Wildcard"));
            if(sum > wild_sum){
                results.get("Final").add(sum);
            }else{
                results.get("Final").add(wild_sum);
            }
        }else{
            results = HandleRoll(size);
        }
        return results;
    }

    public Hashtable<String, List<Integer>> HandleRoll(int size, int modifier, boolean wildcard){
        Hashtable<String, List<Integer>> results = HandleRoll(size, wildcard);
        results.get("Final").set(0, results.get("Final").get(0) + modifier);
        return results;
    }
}
