### What are Static Factory Methods?

```c
public final class Boolean implements java.io.Serializable, Comparable<Boolean> {

  public static Boolean valueOf(boolean b) {
    return b ? Boolean.TRUE : Boolean.FALSE;
  }

  public static final Boolean TRUE = new Boolean(true);

  public static final Boolean FALSE = new Boolean(false);
}
```

### Benefits of using static factory method

1. Unlike constructors, they have names.

If there are multiple constructors with different parameters, the user will nto be able to remember which constructor is which and can call the wrong one by mistake.

2. Unlike constructors, they are not required to create a new object each time they're invoked.

If it's expensive to create an instance of an object and equivalent objects are requested often, static factory methods can improve performance. The static keyword makes it so that no matter how many times Boolean.TRUE gets called, there's only one object of Boolean(true), and the same goes for Boolean.FALSE. Note that static objects are stored in method section of the memory.

#### Instance-controlled

Classes with static factory methods are considered to be instance-controlled.

The fact that static factory methods return the same object from repeated invocations allows strict control over what instances exist at any time.

Benefits include

1. Guarantee that it is a singleton
2. An immutable class can guarantee that no two equal instances exist
3.
