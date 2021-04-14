package threads.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnableConnection implements Runnable{
    private static final Logger logger = LogManager.getLogger(RunnableConnection.class);
    private final ConnectionPool pool;
    private final int id;

    public RunnableConnection(int id, ConnectionPool pool){
        this.id = id;
        this.pool = pool;
        logger.info(this.toString() + " initialized");
    }

    public void run(){
        Connection connection = null;

        try {
            logger.info(this.toString() + " is taking a connection");
            connection = pool.getConnection();

            connection.doSomething();

            logger.info(this.toString() + " is placing back the connection " + connection.getNumber());
            pool.placeBackConnection(connection);

        } catch (InterruptedException e) {
            logger.info(this.toString() + " Failed to get a connection: " + e);
        }
    }

    public int getId() {
        return id;
    }

    public ConnectionPool getPool() {
        return pool;
    }

    @Override
    public String toString(){
        return "RunnableConnection " + id;
    }
}
