### Don't go overboard in providing conveience methods

Consider pro- viding a “shorthand” only if it will be used often. When in doubt, leave it out.

### Avoid long parameter lists. 

Long sequences of identically typed parameters are especially harmful.

```java
public void whatever(String one, String two, String three, String four) {
	// dangerous
}
```

### Break the method up into multiple methods. If done carelessly, this can lead to too many methods, but it can also help reduce the method count by increasing orthogonality.

### For parameter types, favor interfaces over classes

By using a class instead of an interface, the client is restricted to a particular implementation and forced an unnecessary and potentially expensive copy operation if the input data happens to exist in some other form. 

### Prefer two-element enum types to boolean parameters.

```java
public enum TemperatureScale {
	FAHRENHEIT,
	CELSIUS
}

Thermometer.newInstance(TemperatureScale.CELSIUS)
```

as opposed to

```java
Thermometer.newInstance(true)
```

1. you can add another enum type such as KELVIN in a future withouth having to add a new static factory to Thermometer
2. Temperature-scale dependenceis can be refactored into methods on the enum constants. e.g. you can have a method that takes a double value and normalized it to celsius.


