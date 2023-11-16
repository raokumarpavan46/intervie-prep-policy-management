package com.policymanagement.spring.jpa.h2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "policies")
public class Policy {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private boolean published;

  public Policy() {

  }

  public Policy(String name, String description, boolean published) {
    this.name = name;
    this.description = description;
    this.published = published;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  @Override
  public String toString() {
    return "Policy [id=" + id + ", name=" + name + ", desc=" + description + ", published=" + published + "]";
  }

}
