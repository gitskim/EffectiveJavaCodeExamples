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

In the following examples, there is only one instance of an object guaranteed with a static factory method.

```c
class Qi {
	private static Qi instance = new Qi();

	static Qi create() {
		if (instance == null) {
			instance = new Qi();
		}
		return instance;
	}
}
```

and

```c
public final class Boolean implements java.io.Serializable, Comparable<Boolean> {

	public static Boolean valueOf(boolean b) {
		return b ? Boolean.TRUE : Boolean.FALSE;
	}

	public static final Boolean TRUE = new Boolean(true);

	public static final Boolean FALSE = new Boolean(false);
}
```

Note that for Q.create(), the null check is necessary. Without the null check, multiple instances of Qi are created, as Q.create() is called multiple times. However, garbage collector will ensure that there is only one instance. Until then, there will be as many instances of Qi as Q.create() is called.

With static factory methods, an immutable class can enjoy a guarantee that no two equal instances exist, which is advantageous in asynchronous programming.

#### Returning Any Subtype

Unlike constructors, static factory methods can return a subtype of their return type. 

e.g.

```c
public class Collections {
	// Suppresses default constructor, ensuring non-instantiability.
	private Collections() {
	}

	public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
		return new UnmodifiableCollection<>(c);
	}

	static class UnmodifiableCollection<E> implements Collection<E>, Serializable {
		// details not included
	}
}
```

Since Collections class has this static method of unmodifiableCollection to return UnmodifiableCollection which is a subclass defined within Collections class, it's possible to return a subtype of Collection (Please ntoe that the return type is Collection and that UnmodifiableCollection implements Collection. Also, the bulk of the API is reduced. Had we not implemented the static factory method, it would have been


```c
public class Collections {
	// Suppresses default constructor, ensuring non-instantiability.
	private Collections() {
	}

	public static class UnmodifiableCollection<E> implements Collection<E>, Serializable {
		// details not included
	}
}
```

Now the entire UnmodifiableCollection class is exposed as a public api. Unlike the previous API, which only exposed a public static method, this one is a heavy-weight API from the client perspective.

And the client would use the API by

```c
Collections.UnmodifiableCollection c = new Collections.UnmodifiableCollection();
```

There are disadvantages of using Collections API.

#### Disadvantage 1: static factory methods without public/protected constructors cannot be subclassed.

#### Disadvantage 2: static factory methods do not stand out as opposed to constructors, so it can be confusing for devs trying to use one to create an instance of an object.





