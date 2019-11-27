# Difference between final and immutability
* Declaring something final doesn't mean it's immutable. Even though reference variable is final, we can perform any change in the corresponsiding object. But we can't perform reassignment for that variable. 

* Then what does final mean? final ensures that the address of the object remains the same whereas the Immutable suggests that we can't change the state of the object once created. 

* final means you can't change the object's reference to point to another refernece to another object, but you can still mutate its state. Immutable means you can't change the actual value, but you can change its reference to another one. 

```c
public void test() {
    // POSSIBLE
    final List<Integer> list = new ArrayList<>();
    list.add(1);
    for (int i = 0; i < list.size(); i++) {
        System.out.println(list.get(i));
    }

    // IMPOSSIBLE
    list = new ArrayList<>();
}
```
* final variable - primitive data type - the values can't be changed. But if the variable references to objects, they can't be changed to refer to any other object. 

* final method - can't be overriden
* final class - can't be extended
# How to create an immutable class
1. Make your class final, so that no other classes can extend it. 
2. MAke all the fields final, so that they're initialized only once inside the constructor and never modified afterwards. 
3. Don't expose setter methods. 
4. When exposing methods which modify the state of the class, you must always return a new instance of the class. 


### String immutability: https://javaconceptoftheday.com/how-the-strings-are-stored-in-the-memory/
