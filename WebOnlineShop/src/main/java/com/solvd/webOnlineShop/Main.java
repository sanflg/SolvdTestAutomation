package com.solvd.webOnlineShop;

import com.solvd.webOnlineShop.payment.Ticket;
import com.solvd.webOnlineShop.ui.menu.mainMenu.MainMenu;
import com.solvd.webOnlineShop.user.registeredUser.CustomerTester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(MainMenu.class);

    // Debug tool, if human input is wanted turn dummy off (at the moment this implementation uses BufferReader and console
    // it only works when executed in terminal.
    private static final boolean isDummyOn = true;

    public static void main(String[] args) {

        logger.info("Execution started");
        new com.solvd.webOnlineShop.GenerateRandomData();
        new MainMenu();
        processTickets();
        logger.info("Execution terminated");
    }

    public static boolean isIsDummyOn() {
        return isDummyOn;
    }

    public static void processTickets(){

        LinkedList<Ticket> ticketList = (LinkedList<Ticket>) DatabaseSimulation.getTicketList();

        if(CustomerTester.generateTicketOption() == 1){
            DatabaseSimulation.getTicketList().forEach(t->
            logger.info("Ticket processed: " + ticketList.removeFirst().toString()));}
        else{ticketList.descendingIterator().forEachRemaining(ticket->
            logger.info("Ticket processed: " + ticketList.removeLast().toString()));}

        logger.info("All tickets processed!");
    }
}

