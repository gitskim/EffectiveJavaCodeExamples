### A singleton is a class that is instantiated only once. Singleton classes are difficult to test.

please read http://blog.rseiler.at/2014/06/explanation-how-proxy-based-mock.html

If the proxy based mock frameworks such as Mockito is used, single classses are difficult to test, because mockito creates a subclass of the original class as a mocked class. It has other data injected to avoid testing the dependencies and only focus on testing the class itself. However, one can't mock a singleton, since it can't be subclassed. (Note that it can be done with a testing framework that uses bytecode manipulation)

### How to make a class a singleton?

private constructor + public final fields + public static factory instance

NOTE: a private constructor can be called refelctively by using the AccessibleObject.setAccesible method. In order to defend against reflection, you can make the private constructor throw an error when it's called the second time. 

```c
public class SingleDad {
	private static final SingleDad INSTANCE = new SingleDad(30, 180);
	public int age;
	public int height;

	private SingleDad(int age, int height) {
		// constructor not available
		this.age = age;
		this.height = height;
		if (INSTANCE != null) {
			throw new IllegalArgumentException("Single Dad's constructor cannot be called");
		}
	}

	public static SingleDad getInstance() {
		return INSTANCE;
	}

	public void getOlder() {
		age++;
	}

	public void getShorter() {
		height--;
	}
}
```

If you run the class in the main

```c
public class Main {
	public static void main(String[] args) {
		SingleDad mDad = SingleDad.getInstance();
		System.out.println("my dad's current age is " + mDad.age);
		System.out.println("my dad's current height is " + mDad.height);

		mDad.getOlder();
		System.out.println("my dad got older and he is " + mDad.age + " years old");
		mDad.getShorter();
		System.out.println("my dad got shorter and he is " + mDad.height + "cm tall");
	}
}
```

Then the result comes out to be 

```c
my dad's current age is 30
my dad's current height is 180
my dad got older and he is 31 years old
my dad got shorter and he is 179cm tall
```

### What happens when you make make a serializable class a singleton?

The short answer is adding "implements Serializable" is merely not enough. (NOTE: Serialization is a mechanism of writing the state of an object into a byte stream. Deserialization is reconstructing the object from a serialized state, which is a byte stream.) The reason is that each time a serialized instance is deserialized, a new instance will be created. In order to prevent this you need to add readResolve method to the Singleton class and declare all isntance fields transient.

As written in Oracle Docs,
"Classes that need to designate a replacement when an instance of it is read from the stream should implement this special method with the exact signature."

```c

 ANY-ACCESS-MODIFIER Object readResolve() throws ObjectStreamException;

```

```c
private SingleDad readResolve() {
	// Return the one true SingleDad and let the garbage collector take care of the SingleDad impersonators.
	return INSTANCE;
}
```

Java transient keyword is used in serialization. If you define any data member as transient, it will not be serialized.
