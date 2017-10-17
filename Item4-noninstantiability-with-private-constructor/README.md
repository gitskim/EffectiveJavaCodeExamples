### Utility class with static methods and static fields
* Utility classes are not designed to be instantiated.
* Some people consider making a class abstract, but the class can be subclassed, which can be instantiated. More importantly, it misleads the user into thinkingi that the class was designed for inheritance. 
* a better solution would be making a class non-instantiable by making the constructor private. 

```c

public class Utils {
	private Utils() {
		throw new AssertionError();
	}
}
