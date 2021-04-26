package threads.callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DiceBasket {
    private static final Logger logger = LogManager.getLogger(DiceBasket.class);
    private final int POOL_SIZE = 5;
    private static DiceBasket basket;
    private BlockingQueue<Dice> dices = new LinkedBlockingQueue<>(POOL_SIZE);
    private int count = 0;

    private DiceBasket() {
    }

    public static DiceBasket getDiceBasket() {
        if(basket == null){
            logger.info("DiceBasket lazy initialization started");
            basket = new DiceBasket();
        }
        logger.info("Returning DiceBasket");
        return basket;
    }

    private synchronized void newDice(){
        logger.info("New dice is being created with connectionNumber: " + count);
        dices.add(new Dice(count));
        count++;
    }

    public Dice getDice() throws InterruptedException {
        if(count < POOL_SIZE){
            newDice();
        }
        return dices.take();
    }

    public void addDice(Dice dice){
        dices.offer(dice);
    }

    public void throwDices(){
        dices.forEach(Dice::throwDice);
    }
}
