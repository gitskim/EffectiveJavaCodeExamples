This item is about memory leak. There are three ways of causing memory leaks that were discussed in the book. 

1. Managing its own memory

```c
import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[--size];
	}

	public void ensureCapacity() {
		if(elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}
}

```
There is a place where it can cause memory leak, which would increase garbage collector acitivty and memory footprint. In extreme cases, disk paging (there isn't enough memory, so secondary memory is brought in for use) and even program failure could occur due to those performance issues. 

What causes memory leak is the pop method. the pop method has the size field decreasing, so the stack doesnt't have access to a popped element, but the element still exists in the array. That's why it's a memory leak, because unused data are not garbage-collected. 

A better way of implementing the pop method is

```c
public Object pop() {
	if (size == 0) {
		throw new EmptyStackException();
	}
	Object result = elements[--size];
	elements[size] = null;
	return result;
}
```

The fix is to null out references once they become obsolete, although the best way to eliminate an obsolete reference is to let the variable fall out of scope. It occurs naturally if each variable is defined in the narrowest possible scope. 

Note that nulling out a reference is not the most desriable way to eliminate an obsolete reference. It should be only used when a data structure manages its own memory. In this case of Stack, there is no way for garbage colelctor to know that inactive portion of the array are not used, but only the programmer knows. 

General rule of thumb is **Whenever a class manages its own memory, the programmer should be alert for memeory leaks**

2. Caches

Caches are another common source of memory leaks. A solution to this woudl be using a WeakHashMap to store caches. Note that WeakHashMap is useful only if the desired lifetime of cache entries is determined by external references to the key, not the value. ? question? does the weak hashmap get garbage collected item by item or the entire map?

3. Listeners and other callbacks

The best way to ensure that callbacks are garbage collected is to store only weak references to them; for exmaple, callbacks can be stored as keys in a WeakHashMap. 

|key|value|
|---|---|
|Callback|value|

