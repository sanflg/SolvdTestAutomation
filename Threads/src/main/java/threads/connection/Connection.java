package threads.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {
    private final static Logger logger = LogManager.getLogger(Connection.class);
    private final int number;

    public Connection(int number){
        this.number = number;
    }

    public synchronized void doSomething() throws InterruptedException {
        logger.info("Connection " + number + " is doing something");
        wait(1000);
    }

    public void closeConnection(){
        //close this connection
    }

    public int getNumber() {
        return number;
    }
}
