package com.bootcamp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Cat {
    @Getter
    private String name;
    @Getter
    private int age;
    // No Getter for eyes
    private Eye[] eyes; // JSON: array = list
    // No Getter for tail
    private Tail tail;
  
    public Cat(String name, int age, Color eyeColor, double tailLength) {
        this.name = name;
        this.age = age;
        //this.eyes[0].color = color;
        //this.eyes[1].color = color;
        this.eyes = new Eye[] {new Eye(eyeColor), new Eye(eyeColor)};
        this.tail = new Tail(tailLength);
    }
    @Getter
    @AllArgsConstructor
    public static class Eye{
        private Color color;
    
    }
    
    @Getter
    @AllArgsConstructor
    public static class Tail{
        private double length;
    }  
    
    // Instance method = Presentation
    // Getter
    public Eye getLeftEye() {
    return this.eyes[0]; // ordering, filtering, mapping
    }

    // Getter
    public Eye getRightEye() {
    return this.eyes[1];
    }

    // Getter
    public double getTailLength() {
    return this.tail.getLength();
    }
  

    public static void main(String[] args) {
        String emailContent = "Dear Sir, \n\"scentence\" \\\thello ";
        System.out.println(emailContent);
        // Dear Sir,
        // "scentence" \ hello
      }
    
}
