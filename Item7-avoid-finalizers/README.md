## What is a finalizer?
finalize() gets called before garbage collector cleans it up. When the method is called, the object gets queued in a garbage collection thread. It is better to have JVM manage memory than messing up JVM's work by calling finalize().

**Finalizers are unpredictable, often dangerous, and generally unnecessary**

"finalize()" is not the Java's version of destructor. Somethign that comes closes to C++'s destructor in Java is try/finally.

```c
try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	System.out.println(br.readLine());
} catch(Exception e) {
	// catch some exceptions
} finally {
	// DISPOSE whatever you don't need after the exception occurred.
}
```

### Why do finalizers have to be avoided?

1. There is no guarantee that they'll be executed proptly. It can take arbitrarily long between the time that an object becomes unreachable and the time that its finalizer's executed. => ***you should never do anything time-critical in a finalizer*** How fast finalizers is executed depends on JVM implementation to JVM implementation. It is possible that your test runs seamlessly, but it might fail miserably on the JVM that your customers use the most. 

It is clearly written here (http://bugs.java.com/view_bug.do?bug_id=6668279) to not use System.gc and System.runFinalization in the attempt to call finalizers, but as the article says, they don't guarantee it. The only methods that claim to guarantee finalization are System.runFinalizersOnExit and Runtime.runFinalizersOnExit are fatally flawed and have been deprecated. 

Providing a finalizer for a class can delay freeing up memory for other uses.

The Java documentation does not discuss which thread will execute finalizers, so there is no way to prevent the problems that are metnioned above. The best thing to do is stay away from it. Also note that the documentation does not guarantee that finalize() will be called, so it's possible that a program can terminate without executing finalizers on some objects. 

It takes longer for JVM to clean up an object when finaliz() is used compared to when it's not used. 

During multi-threaded program's life cycle, if there is an uncaught exception in a thread, then the program will not terminate and another thread tries to use such a corrupted object, arbitrary undeterministic behavior may result. 

### Instead of using a finalizer, provide an explicit termination method
1. Implement an explicit termination method with a private field to keep track of whether it has been terminated. 
2. when the object is terminated, the private field of the object records that it is no longer aid. 
3. Other methods of the class must check this field and throw an IllegalStateException if tehy are called after the object has been terminated. 

e.g. close() on InputStreatm, OutputStream and java.sql.Connection.
e.g. cancle() on java.util.Timer

object.finalize()

4. Explicit termination methods in try-finaly ensures termination, because the finally clause ensures that it will get executed even if an exception is thrown while the object is being used. 

```c
Foo foo = new Foo(...);
try {
	// Do what must be done with foo
} finally {
	foo.terminate();
}
```

### What are finalizers actually good for?
There are two legitimate uses.

1. Better late than never. Although there is no guarantee that the finalizer will be invoked promptly, it is better to free the resource late than never. Since hte finalizer logs a warning if the resource has not been terminated, we get some warning and traces of the origin of the issue. 

NOTE: The four classes (FileInputStream, FileOutputStream, Timer, and Connection) have finalizers that serve as safety nets but they don't log warnings. That's just how it was designed originally. 

2. A native peer is a native object to which a normal object communicates via native methods. You can think of a Java peer class as a java interface t to deal with C/C++. A native peer is the native C/C++ class to which Java tries to communicate with. Since a native peer is not a normal object, the garbage colelctor doesn't knowa bout it and can't reclaim it when its Java peer is reclaimed. If the native peer does not hold critical resources and not have a termination method, then finalizer can be used to free it. However, if the native peer does hold critical resources, it needs to have explicit termination methods. 

### How to use a finalizer

If you really have to use a finalizer, this is how you would use it.
1. you need to override the "finalize()" method from Object super class. If you are creating your own super class, make sure to include finalize. 
2. Add a try catch and in the finally block, call "super.finalzie();

The reason that you need to invoke the superclass finalizer is it gets executed even if the subclass finalization throws an exception and vice versa. Note that if a subclass implementor overrides a superclass finalizer but forget to invoke it, the superclass finzlier will never be invoked. 

```c
@Override
protected void finalize() throws Throwable {
	try { 
		// finalize subclass state
	} finally {
		super.finalize();
	}
}
```

Note that this is a two step process: first you need to override the finalize method and second you need to call the finalize method. Another way of doing it is having a finalizer on an anonymous class whose sole purpose is to finalize its outer class instance. Then every time there is an instance of the outer class made, there is always a finalizer guardian enclosed. The finalization gaurdian needs to be sotred in a private instance field so the finalizer guardian becomes eligible for finalization at the same time as the enclosing instance. When the guardian is finalized, it performs finalization of the outer class as if its finalizer were a method on the outer class. Since when outer class is garbage collected, inner anonymous finalizer guardian gets garbage colelcted as well (note that private fields will be garbage collected when its class is garbage collected), its inner anonymous class' finalize method wil be called and that method works as if you overrode the finalize method for the outer class. 
