### Enum is needed when the int enum pattern is used.

```java
// The int enum pattern - severely deficient!
public static final int APPLE_FUJI =0; 
public static final int APPLE_PIPPIN =1; 
public static final int APPLE_GRANNY_SMITH = 2;
public static final int ORANGE_NAVEL  = 0;
public static final int ORANGE_TEMPLE = 1;
public static final int ORANGE_BLOOD  = 2;
```

Compiler cannot tell the difference between APPLE_FUJI and ORANGE_NAVEL.

### Enum is needed when 
