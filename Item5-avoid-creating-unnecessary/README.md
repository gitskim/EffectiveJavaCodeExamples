?question? An object can always be reused if it is immutable. 

### The benefits of reusing an object
* It is both faster and more stylish.

```c
String s = new String("my-string"); // two string instances
```

1. when "my-string" is created.
2. when new String("my-string") is invoked.

A better way to do this is

```c
String s = "my-string";
```

The second way of creating a string is faster. 

You can often avoid creating unnecessary objects by using static factory methods and limit its number of instances that are created. 

````c

public class American {
    privae final Date dateOfBirth;
    
    public boolean bornAfter911() {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        
        gmtCal.set(2001, Calendar.September, 11, 0, 0, 0)l
        Date sep11 = gmtCal.getTime();
        
        return dateOfBirth.compareTo(sep11) < 0;
    }
}

````

The problem with the code above is that every time "bornAfter911()" is called, it creates a new instance of Calendar, TimeZone and two Date objects. In order to avoid creating all those objects every time it's called, 



We can have the comparison in a static block.

````c
private final Date dateOfBirth;
private static final Date SEP_11;
static {
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        
    gmtCal.set(2001, Calendar.September, 11, 0, 0, 0)l
    SEP_11 = gmtCal.getTime();
        
    return dateOfBirth.compareTo(sep11) < 0;
}

public boolean bornAFter911() {
    return dateOfBirth.compareTo(SEP_11) < 0;
}
````

This improved code creates an instance of Calendar, TimeZone and DAte only once, instead of creating them every time the method is invoked.

?? question - visit this again also refer to gang of four But more initialization can be done by creating SEP_11 field only when the method is invoked, by utilizing lazy initialization. 

### Unnecessary object creation due to autoboxing
```c
public static void main(String[] args) {
    Long sum = 0L;
    
    for (long i = 0; i < Integer.MAX_VALUE; i++) {
        sum += i;
    }
    
    System.out.println(sum);
}
```

This code is much slower than it should be, because of autoboxing. every time "sum += i" is called, i will be autoboxed and promoted to an object long. It means there will be 2^31 number of i objects will be created unnecessarily.

The LESSON is
**Prefer primitives to boxed primitives and watch out for unintentional autoboxing**

There is another important lesson. 

The counterpart to this item is Item 39 on "defensive copying", which means you should create a new object to avoid insidious bugs and security holes. Creating objects unnecessarily merely affect style and performance.

