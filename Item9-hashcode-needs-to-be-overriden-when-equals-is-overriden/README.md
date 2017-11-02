When you override "equals", it's crucial to override "hashCode" as well. If you don't do it, hashCode class will not properly function, because you are violating the general contract for Object.hashCode. 

### Java contract
* 
