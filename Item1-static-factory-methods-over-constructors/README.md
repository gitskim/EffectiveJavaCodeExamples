A good example is
```c
public final class Boolean implements java.io.Serializable, Comparable<Boolean> {

  public static Boolean valueOf(boolean b) {
    return b ? Boolean.TRUE : Boolean.FALSE;
  }

  public static final Boolean TRUE = new Boolean(true);

  public static final Boolean FALSE = new Boolean(false);
}
```
