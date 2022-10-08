package com.fastforward.cardsapi.controller.request;

public class CardRequest {

  private String name;

  private String description;

  private String imageUrl;

  private int strength;

  private int speed;

  private int gear;

  private int intellect;

  private int skill;

  private long originId;

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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getGear() {
    return gear;
  }

  public void setGear(int gear) {
    this.gear = gear;
  }

  public int getIntellect() {
    return intellect;
  }

  public void setIntellect(int intellect) {
    this.intellect = intellect;
  }

  public int getSkill() {
    return skill;
  }

  public void setSkill(int skill) {
    this.skill = skill;
  }

  public long getOriginId() {
    return originId;
  }

  public void setOriginId(long originId) {
    this.originId = originId;
  }

  @Override
  public String toString() {
    return "CardRequest{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", strength=" + strength +
        ", speed=" + speed +
        ", gear=" + gear +
        ", intellect=" + intellect +
        ", skill=" + skill +
        ", originId=" + originId +
        '}';
  }
}
