package threads.runnable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int POOL_SIZE = 5;
    private static ConnectionPool connectionPool;
    private BlockingQueue<Connection> connections;
    private int connectionNumber = 0;

    private ConnectionPool(){
        connections = new LinkedBlockingQueue<Connection>(POOL_SIZE);
    }

    public static ConnectionPool getConnectionPool(){
        if (connectionPool == null){
            logger.info("ConnectionPool lazy initialization started");
            connectionPool = new ConnectionPool();
        }
        logger.info("Returning ConnectionPool");
        return connectionPool;
    }

    private synchronized void newConnection(){
        logger.info("New Connection is being created with connectionNumber: " + connectionNumber);
        connections.add(new Connection(connectionNumber));
        connectionNumber++;
    }

    public Connection getConnection() throws InterruptedException {
        if (connectionNumber < POOL_SIZE){
            newConnection();
        }
        return connections.take();
    }

    public void placeBackConnection(Connection connection){
        connections.offer(connection);
    }

    public void shutDownAll(){
        connections.forEach(Connection::closeConnection);
        connections = new LinkedBlockingQueue<Connection>(POOL_SIZE);
        connectionNumber = 0;
    }

}
