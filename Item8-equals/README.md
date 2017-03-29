## Item 8 - Obey the general contract when overriding equals
### Benefits of Overriding equals method
* The equals() method provided in the object class uses "==" to compare. For primitive types, "==" works perfectly fine, but for objects it's tricky. Default implementation of equals() class provided by java.lang.Object compares memory location and return true only if two reference cariable are pointing to the same memory location. That's why we override equals() method. 
* Equals method can be implemented however the programmer wants. 
---
Back to Item 8
* equals() needs to have the right behavior when threading is implemented. 
* 
