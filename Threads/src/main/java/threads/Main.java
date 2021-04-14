package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import threads.connection.ConnectionPool;
import threads.connection.RunnableConnection;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {
    private final static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private final static Logger logger = LogManager.getLogger(Main.class);
    private final static Integer EXECUTIONS = 6;
    private static int runnableCount = 1;

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
    }

    public static synchronized void incrementRunnableCount(){
        runnableCount++;
    }
}
