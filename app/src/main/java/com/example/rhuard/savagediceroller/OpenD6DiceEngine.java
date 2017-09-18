package com.example.rhuard.savagediceroller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by rhuard on 9/15/17.
 */

public class OpenD6DiceEngine extends DiceEngine implements DiceResults {

    public OpenD6DiceEngine(){
        super();
    }

    public Hashtable<String, List<Integer>> HandleRoll(int num, boolean wild_die){
        Hashtable<String, List<Integer>> results = CreateResultsTable();
        int end = 0;
        if(num > 0) {
            //roll wild die if necessary
            if (wild_die) {
                num = num - 1;
                int roll = 0;
                results.put("WildDie", new ArrayList<Integer>());
                //keep rolling if it keeps exploding
                int result;
                do{
                    result = RollDie(6);
                    results.get("WildDie").add(result);
                    roll += result;
                }while(roll == 6);

                end += roll;
            }

            //roll rest of dice
            int roll;
            for(int i = 0; i < num; i++){
                roll = RollDie(6);
                results.get("Rolls").add(roll);
                end += roll;
            }

            //get result
            results.get("Final").add(end);
        }
        return results;
    }

    public Hashtable<String, List<Integer>> HandleRoll(int num, boolean wild_die, int modifier){
        Hashtable<String, List<Integer>> results = HandleRoll(num, wild_die);
        results.get("Final").set(0, results.get("Final").get(0) + modifier);
        return results;
    }
}
