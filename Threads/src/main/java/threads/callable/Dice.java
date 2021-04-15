package threads.callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Dice{
    private static final Logger logger = LogManager.getLogger(Dice.class);
    private static final int FACES = 6;
    private final int number;

    public Dice(int number){
        this.number = number;
    }

    public synchronized int throwDice(){
        Random random = new Random();
        int i = random.nextInt(FACES) + 1;
        logger.info("Dice " + number + " throws: " + i);
        return i;

    }

    public int getNumber() {
        return number;
    }
}
