# Benefits of making defensive copies

#### You must program defensively, with the assumption that clients of your class will do their best to destroy its invariants (Item 39).

The following class looks like an immutable class, but using the fact that Date is mutable, an instance of Period is prone to mutability.

```java
public final class Period {
       private final Date start;
       private final Date end;

/**
* @param start the beginning of the period
* @param end the end of the period; must not precede start * @throws IllegalArgumentException if start is after end
* @throws NullPointerException if start or end is null
*/
       public Period(Date start, Date end) {
           if (start.compareTo(end) > 0)
               throw new IllegalArgumentException(
                   start + " after " + end);
           this.start = start;
           this.end   = end;
       }

       public Date start() {
           return start;
       }

       public Date end() {
           return end;
       }
       ...  // Remainder omitted
}
```

```java
// Attack the internals of a Period instance
Date start = new Date();
Date end = new Date();
Period p = new Period(start, end);
end.setYear(78); // Modifies internals of p!
```

Note that start and end are an instance of Date and they are used as arguments to create a period instance. Since in Java, it's passed by reference, modifying the "end" object will modify the period object which uses the "end". In order to defend against this, a copy of Date object can be made within the constructor.

```java
// Repaired constructor - makes defensive copies of parameters
public Period(Date start, Date end) {
       this.start = new Date(start.getTime());
       this.end   = new Date(end.getTime());

       if (this.start.compareTo(this.end) > 0) {
          throw new IllegalArgumentException(start +" after "+ end);
       }
}
```

But it's still prone to mutability, since the "end" can be obtained with a getter from the period class.

```java
// Second attack on the internals of a Period instance
Date start = new Date();
Date end = new Date();
Period p = new Period(start, end);
p.end().setYear(78); // Modifies internals of p!
```

In order to prevent mutability of the class via getters, a new instance of the object can be returned, instead of returning the object itself from the getter.

```java
// Repaired accessors - make defensive copies of internal fields
public Date start() {
       return new Date(start.getTime());
}

public Date end() {
       return new Date(end.getTime());
}
```
