# Difference between final and immutability
* By declaring final, there is no immutability nature. Even though reference variable is final, we can perform any change in the corresponsiding object. But we can't perform reassignment for that variable. 

* final ensures taht the address of the object remains the same whereas the Immutable suggests that we can't change the state of the object once created. 

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

