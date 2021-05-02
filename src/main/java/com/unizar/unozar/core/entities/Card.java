package com.unizar.unozar.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARD")
public class Card{
  
  // Special value
  public final static int NONE = -1;
  
  // Colors
  public final static int BLACK = 0;
  public final static int RED = 1;
  public final static int YELLOW = 2;
  public final static int GREEN = 3;
  public final static int BLUE = 4;
  
  public final static int BASIC_COLORS[] = {RED, YELLOW, GREEN, BLUE};
  
  // Special functions
  public final static int REVERSE = 0;
  public final static int SKIP = 1;
  public final static int DRAW_TWO = 2;
  public final static int DRAW_FOUR = 3;
  public final static int CHANGE_COLOR = 4;
  
  public final static int BASIC_SPECIAL_FUNCT[] = {REVERSE, SKIP, DRAW_TWO};
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @Column(name = "NUM")
  private int num;
  
  @Column(name = "COLOR")
  private int color;
  
  @Column(name = "SPEC")
  private int spec; // Special function
  
  public Card(int num, int color, int specialFunct){
    this.num = num;
    this.color = color;
    this.spec = specialFunct;
  }
  
  public boolean isRelatedWith(Card otherCard){
    if((isSameColor(otherCard)) || (isSameNum(otherCard)) || 
        (isSameFunct(otherCard))){
      return true;
    }
    return false;
  }
  
  public boolean isSameNum(Card otherCard){
    if((num != NONE) && (num == otherCard.getNum())){
      return true;
    }
    return false;
  }
  

  public boolean isSameColor(Card otherCard){
    if(color == otherCard.getColor()){
      return true;
    }
    return false;
  }

  public boolean isSameFunct(Card otherCard){
    if((spec != NONE) && (spec == otherCard.getSpecialFunct())){
      return true;
    }
    return false;
  }
  
  /////////////////////////
  // Getters and Setters //
  /////////////////////////
  
  public int getNum(){
    return num;
  }
  
  public int getColor(){
    return color;
  }
  
  public int getSpecialFunct(){
    return spec;
  }
  
  ///////////////
  // TO_STRING //
  ///////////////
  
  private String numToString(){
    if(num != NONE){
      return Integer.toString(num);
    }else{
      return "X";
    }
  }

  private String colorToString(){
    switch(color){
    case RED:
      return "R";
    case YELLOW:
      return "Y";
    case GREEN:
      return "G";
    case BLUE:
      return "B";
    default:
      return "X";  
    }
  }
  
  private String specToString(){
    switch(spec){
    case REVERSE:
      return "R";
    case SKIP:
      return "S";
    case DRAW_TWO:
      return "2";
    case DRAW_FOUR:
      return "4";
    case CHANGE_COLOR:
      return "C";
    default:
      return "X";
    }
  }
  
  @Override
  public String toString(){
    return this.numToString() + this.colorToString() + this.specToString();
  }
}