package com.solvd.newLinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        NewLinkedList<Integer> linkedList = new NewLinkedList<>();

        Integer[] array = {2,5,21,5,61};

        linkedList.addFirst(4);
        linkedList.addLast(5);

        Node<Integer> newNode = linkedList.getLastNode();

        linkedList.linkBefore(3, newNode);

        logger.info(linkedList.size());
        logger.info(linkedList.toString());

        linkedList.remove(1);

        //Try adding a collection
        linkedList.addAll(Arrays.asList(array));
        linkedList.removeFirst();

        logger.info(linkedList.toString());
        logger.info(linkedList);


        logger.info("------------------");
        linkedList.forEach(logger::info);

        logger.info("------------------");
        //Descending iterator generated with NewDescendingIterator class
        linkedList.descendingIterator().forEachRemaining(logger::info);
    }
}
