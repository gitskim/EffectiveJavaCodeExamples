### A singleton is a class that is instantiated only once. Singleton classes are difficult to test.

please read http://blog.rseiler.at/2014/06/explanation-how-proxy-based-mock.html

If the proxy based mock frameworks such as Mockito is used, single classses are difficult to test, because mockito creates a subclass of the original class as a mocked class. It has other data injected to avoid testing the dependencies and only focus on testing the class itself. However, one can't mock a singleton, since it can't be subclassed. (Note that it can be done with a testing framework that uses bytecode manipulation)


