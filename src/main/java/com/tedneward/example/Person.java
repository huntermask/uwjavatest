package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;

  private static int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    count++;
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> returnList = new ArrayList<>();
    returnList.add(new Person("Ted", 41, 250000));
    returnList.add(new Person("Charlotte", 43, 150000));
    returnList.add(new Person("Michael", 22, 10000));
    returnList.add(new Person("Matthew", 15, 0));
    return returnList;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int value) throws IllegalArgumentException {
    if (value < 0) {
      throw new IllegalArgumentException("Age must not be less than zero.");
    }

    int old = age;
    age = value;

    this.pcs.firePropertyChange("age", old, value);
    propertyChangeFired = true;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) throws IllegalArgumentException {
    if (null == value) {
      throw new IllegalArgumentException("The name must be non-null.");
    }

    String old = name;
    name = value;

    this.pcs.firePropertyChange("name", old, value);
    propertyChangeFired = true;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double value) {
    double old = salary;
    salary = value;

    this.pcs.firePropertyChange("salary", old, value);
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public int count() {
    return count;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object o) {
    if (null == o || !this.getClass().equals(o.getClass())) {
      return false;
    }

    Person p = (Person)o;

    return (this.name.equals(p.getName()) && this.age == p.getAge());
  }

  @Override
  public int compareTo(Person other) {
    return (int)(other.getSalary() - this.getSalary());
  }

  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:"+ this.salary + "]";
  }

  static class AgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
