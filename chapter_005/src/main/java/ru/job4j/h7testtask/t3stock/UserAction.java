package ru.job4j.h7testtask.t3stock;
import java.util.HashMap;

/**
 * The UserAction class.
 */
public abstract class UserAction {
    /**
     * @param input input.
     * @param market is the HashMap.
     */
    public abstract void execute(SimpleInput input, HashMap<Integer, TradeIssuer> market);
}