package com.microservice.storage.model;

import javax.persistence.*;

@Entity(name = "Person")
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue
  @Column(name="person_id")
  private long personId;
  @Column(name="person_name")
  private String personName;
  @Column(name="person_address")
  private String personAddress;

  public Person() {
  }

  public Person(String personName, String personAddress) {
    this.personName = personName;
    this.personAddress = personAddress;
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }


  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }


  public String getPersonAddress() {
    return personAddress;
  }

  public void setPersonAddress(String personAddress) {
    this.personAddress = personAddress;
  }

}
