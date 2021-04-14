package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import threads.completableFuture.CallableDice;
import threads.connection.ConnectionPool;

import threads.completableFuture.DiceBasket;
import threads.connection.RunnableConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {
    private final static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private final static DiceBasket diceBasket = DiceBasket.getDiceBasket();
    private final static Logger logger = LogManager.getLogger(Main.class);
    private final static Integer EXECUTIONS = 6;
    private static int runnableCount = 1;
    private static int result = 0;

    public static void main(String[] args){

        //connections threads
        ExecutorService connectionsExecutor = Executors.newFixedThreadPool(EXECUTIONS);

        IntStream.range(0,EXECUTIONS).forEach(execution->{
            logger.info("A thread is executing a new RunnableConnection");
            connectionsExecutor.execute(new RunnableConnection(runnableCount, connectionPool));
            incrementRunnableCount();
        });
        runnableCount = 0;
        connectionsExecutor.shutdown();

        //dices threads
        ExecutorService diceExecutor = Executors.newFixedThreadPool(EXECUTIONS);

        List<Future<Integer>> futuresList = new ArrayList<>();

        IntStream.range(0,EXECUTIONS).forEach(execution ->{
            logger.info("A thread is executing a new RunnableDice");
            Future<Integer> future = diceExecutor.submit(new CallableDice(runnableCount, diceBasket));
            incrementRunnableCount();
            futuresList.add(future);
        });

        futuresList.forEach(f->{
            try {
                int partial = f.get();
                logger.info("partial result is: " + partial);
                incrementResult(partial);
                logger.info("The total result sums up, so far: " + result);
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Thread interrupted: " + e);
            }
        });

        logger.info("The dices throw sums up: " + result);
        runnableCount = 0;
        diceExecutor.shutdown();
    }

    public static synchronized void incrementRunnableCount(){
        runnableCount++;
    }

    public static synchronized void incrementResult(int i){
        result+=i;
    }
}
