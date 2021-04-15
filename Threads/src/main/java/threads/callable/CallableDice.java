package threads.callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

public class CallableDice implements Callable<Integer> {
    private static final Logger logger = LogManager.getLogger(CallableDice.class);
    private final DiceBasket pool;
    private int lastNumber;
    private final int id;

    public CallableDice(int id, DiceBasket pool){
        this.id = id;
        this.pool = pool;
        logger.info(this.toString() + " initialized");
    }


    @Override
    public Integer call() throws InterruptedException {

        logger.info(this.toString() + " is taking a dice");
        Dice dice = pool.getDice();

        lastNumber = dice.throwDice();

        logger.info(this.toString() + " is placing back the dice " + dice.getNumber());
        pool.addDice(dice);

        return lastNumber;
    }

    public int getId() {
        return id;
    }

    public DiceBasket getPool() {
        return pool;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    @Override
    public String toString(){
        return "RunnableDice " + id;
    }

}

