//exam 1 practice
//March 5, 2019

import java.util.*;

class Animal{
    String name;
    int age;

     public String toString(){
	return this.name + " is " + age + " years old";
    }
}

interface Swims{
    public void swim();
}

class Dog extends Animal implements Swims{

    Dog(String name, int age){
	this.name = name;
	this.age = age;
    }
   
    public void swim(){
	System.out.println("splish splash");
    }
}

class Fish extends Animal implements Swims{

    Fish(String name, int age){
	this.name = name;
	this.age = age;
    }
    
    public void swim(){
	System.out.println("glug glug");
    }
}

class BoxTurtle extends Animal{

    BoxTurtle(String name, int age){
	this.name = name;
	this.age = age;
    }
}

class Cow extends Animal{
    Cow(String name, int age){
	this.name = name;
	this.age = age;
    }
}


class Main{
    public static void main(String[] args){
	ArrayList<Animal> boys= new ArrayList<>();

	Dog d1 = new Dog("Beans", 2);
	Dog d2 = new Dog("Meatball", 6);

	Fish f1 = new Fish("Cinderella", 1);
	Fish f2 = new Fish("Cinderella 2 Dreams Come True", 2);

	BoxTurtle b1 = new BoxTurtle("Gary", 67);
	BoxTurtle b2 = new BoxTurtle("Frank", 8);

	boys.add(d1);
	boys.add(d2);
	boys.add(f1);
	boys.add(f2);
	boys.add(b1);
	boys.add(b2);

	System.out.println(boys);

	ArrayList<Swims> swimmyBoys = new ArrayList<>();
	swimmyBoys.add(d1);
	swimmyBoys.add(d2);
	swimmyBoys.add(f1);
	swimmyBoys.add(f2);

	System.out.println("Boys who can swim:\n" + swimmyBoys);
	
    }
}
	
    
