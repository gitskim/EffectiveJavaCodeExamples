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
### Why does finalizers have to be avoided?

1. There is no guarantee that they'll be executed proptly. It can take arbitrarily long between the time that an object becomes unreachable and the time that its finalizer's executed. => ***you should never do anything time-critical in a finalizer*** How fast finalizers is executed depends on JVM implementation to JVM implementation. It is possible that your test runs seamlessly, but it might fail miserably on the JVM that your customers use the most. 

? question? What does it mean by "finalizer thread was running at a lower priority than another thread, so objects weren't getting finalized at the rate they became eligible for finalization. 

? question? not only does the language specification provide no guarantee that finalizers will get executed promptly

It is clearly written here (http://bugs.java.com/view_bug.do?bug_id=6668279) to not use System.gc and System.runFinalization in the attempt to call finalizers, but as the article says, they don't guarantee it. The only methods that claim to guarantee finalization are System.runFinalizersOnExit and Runtime.runFinalizersOnExit are fatally flawed and have been deprecated. 


 
