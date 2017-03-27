package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MObject object1 = new MObject();
        MObject object2 = new MObject();

        EqualsObject equalsObject = new EqualsObject();
        EqualsObject equalsObject2 = new EqualsObject();

        System.out.println("object 1 and 2 equal? " + object1.equals(object2));
        System.out.println("equalsObject and equalsObject2 equal? " + equalsObject.equals(equalsObject2));
    }
}
