package de.sqlcoach.ejb;

import javax.ejb.Stateless;

@Stateless
public class HalloEjb implements HalloEjbLocal {
      public String sayHello(String name) {
      return "Hallo "  + name;
     }
}