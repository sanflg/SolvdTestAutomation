package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import threads.callable.CallableDice;
import threads.runnable.ConnectionPool;

import threads.callable.DiceBasket;
import threads.runnable.RunnableConnection;

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

        ExecutorService connectionsExecutor = Executors.newFixedThreadPool(EXECUTIONS);

        //---> Runnable example
        IntStream.range(0,EXECUTIONS).forEach(execution->{
            logger.info("A thread is executing a new RunnableConnection");
            connectionsExecutor.execute(new RunnableConnection(runnableCount, connectionPool));
            runnableCount++;
        });
        runnableCount = 1;

        //---> Callable example with Future

        List<Future<Integer>> futuresList = new ArrayList<>();

        IntStream.range(0,EXECUTIONS).forEach(execution ->{
            logger.info("A thread is executing a new RunnableDice");
            Future<Integer> future = connectionsExecutor.submit(new CallableDice(runnableCount, diceBasket));
            runnableCount++;
            futuresList.add(future);
        });

        //This block is outside of the previous stream because the .get() method will stop the execution of Main,
        //delaying every iteration until the result of the previous one is done, therefore, not generating a new thread,
        //and working in a sequential way. The previous list is used to store the reference of every object to apply the
        //.get() method latter.
        futuresList.forEach(f->{
            try {
                logger.info("partial result is: " +  incrementResult(f.get()));
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Thread interrupted: " + e);
            }
        });

        logger.info("The dices throw sums up: " + result);
        runnableCount = 1;
        result = 0;

        //---> Callable example with CompletableFuture
        List<Future<Integer>> completableFuturesList = new ArrayList<>();

        IntStream.range(0,EXECUTIONS).forEach(execution ->{
            logger.info("A thread is executing a new RunnableDice");
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
                int i = 0;
                try {i = new CallableDice(runnableCount, diceBasket).call();
                }catch (InterruptedException e) {logger.error("Couldn't call new dice: " + e); }
                return i;
            });
            completableFuturesList.add(future);
            runnableCount++;
        });

        completableFuturesList.forEach(f->{
            try {
                logger.info("partial result is: " +  incrementResult(f.get()));
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Thread interrupted: " + e);
            }
        });

        logger.info("The dices throw sums up: " + result);

        connectionsExecutor.shutdown();
    }

    public static synchronized int incrementResult(int i){
        return result+=i;
    }
}
