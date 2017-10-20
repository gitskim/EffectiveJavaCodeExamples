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

 
