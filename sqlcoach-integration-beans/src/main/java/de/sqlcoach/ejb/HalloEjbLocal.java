package de.sqlcoach.ejb;

import javax.ejb.Local;

@Local
public interface HalloEjbLocal {
 public String sayHello(String name);
}