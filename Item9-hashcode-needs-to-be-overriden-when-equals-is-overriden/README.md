When you override "equals", it's crucial to override "hashCode" as well. If you don't do it, hashCode class will not properly function, because you are violating the general contract for Object.hashCode. It will make it hard to properly function when the object is inserted in HashMap, HashSet, and HashTable.

### Java contract
1. The same object must return the same integer no matter how many times the hashCode() is applied.
2. If two objects are equal according
3. Although it's not required that two unequal objects according to the equals() method have distinct integer results, but it's a good practice to improve the performance of hash tables.

## Equal objects must have equal hash codes.

If hasCode() is not overriden and the following code runs

```c
public static void main(String[] args) {
       SingleDad mDad = new SingleDad(10, 9);
       Hashtable hashtable = new Hashtable();
       hashtable.put(mDad, "Mike");
       System.out.println(hashtable.get(new SingleDad(10, 9)));
}
```

it gives null.

That's because it thinks mDad that was used to put a value in the hash table is different from the  anonymous object that is used to get from the hashtable.

### How to write an efficient hash function?

1. Store some constant nonzero prime value e.g. 17 in an int variable called "result"
2. For each significant field f in your object, do the following:

a. Compute an int hash code c for the field:

i. If the field is a boolean, compute (f ? 1:0)
ii. If the field is a byte, char, short, or int, cast it to int: (int) f
iii. If the field is a long, compute (int) (f^(f>>>32))

NOTE: ">>>" is for unsigned bits. ">>" is for signed bits.

iv. If the field is a float, compute Float.floatToIntBits(f).
v. If the field is a double, compute Double.doubleToLongBits(f), and then hash the resulting long as in step 2.a.iii

b. Combine the hashcode c in step 2.a. like this:

result = 31 * result + c;

3. Return result

The reason 31 is chosen is because (31 * i == (i << 5) - i) and bit shifting is faster than calculation. Also note that every field is invovled in calculating hash to not return the same number if an object has a couple of same fields but some that are not same. That's why hashCode() needs to use the fields that are involved in the equals() method. The reason why 31 is prime is not clear.

### If a class is immutable and expensive to compute the hash code, you can save the hash or lazily initialize the first time hashCode is invoked.

```c
@Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = age;
            result = 31 * result + height;
        }
        return result;
    }
```

### Do not sacrifice the hash code function speed for the performance improvement. It is worth calculating the hash code longer to prevent linked-listed hash tables which would slow down the program even more. 
