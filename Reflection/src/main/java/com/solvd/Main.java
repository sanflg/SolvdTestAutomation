package com.solvd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        Class<?> claz = Class.forName("java.util.Stack");
        Stack<String> stack = new Stack<>();

        logger.info("Class: " + claz);

        logger.info("--------------Fields--------------");
        Arrays.stream(claz.getFields()).forEach(logger::info);

        logger.info("--------------Methods--------------");
        Arrays.stream(claz.getMethods()).forEach(logger::info);

        logger.info("Super Class: " + claz.getSuperclass());

        logger.info("--------------Constructors--------------");
        Arrays.stream(claz.getConstructors()).forEach(logger::info);

        logger.info("--------------Invoke with 'empty' method--------------");
        Method getName = null;
        try {
            getName = claz.getDeclaredMethod("empty");
        } catch (NoSuchMethodException e) {
            logger.info(e);
        }

        assert getName != null;
        logger.info("Calling \"empty\" method:" + getName.invoke(stack));

        logger.info("--------------Using the Constructor--------------");
        Constructor<?> constructor = null;
        try {
            constructor = claz.getConstructor();
        } catch (NoSuchMethodException e) {
            logger.error(e);
        }
        Object newStack = null;
        try {
            newStack = constructor != null ? constructor.newInstance() : null;
        } catch (InstantiationException e) {
            logger.error(e);
        }
        assert newStack != null;
        logger.info("New Stack class: " + newStack.getClass());
        logger.info("New Stack to string: " + newStack.toString());
    }
}
