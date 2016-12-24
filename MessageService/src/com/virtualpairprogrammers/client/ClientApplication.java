package com.virtualpairprogrammers.client;

import com.virtualpairprogrammers.motd.MessageOfTheDayService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientApplication {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

//    MessageOfTheDayService helloWorld = container.getBean("motdService", MessageOfTheDayService.class);

    // you don't need the id of the object if you only have a single implementation for the interface
    MessageOfTheDayService helloWorld = container.getBean(MessageOfTheDayService.class);

    System.out.println(helloWorld.getTodaysMessage());

    container.close();
  }
}
