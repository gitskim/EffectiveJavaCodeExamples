Previously we talked about static factory pattern's benefits. However, it doesn't scale well if there are large numbers of optional parameters. 

## Telescoping pattern
The issue with this pattern is it doesn't scale well

```c

public class Button {
	private final int param1;
	private final int param2;
	private final int param3;
	private final int param4;
	private final int param5;
	private final int param6;

	public Button(int param1, int param2, int param3, int param4, int param5, int param6) {
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
		this.param5 = param5;
		this.param6 = param6;
	}

	public Button(int param1, int param2, int param3, int param4, int param5) {
		this(param1, param2, param3, param4, param5, 0);
	}

	public Button(int param1, int param2, int param3, int param4) {
		this(param1, param2, param3, param4, 0);
	}

	public Button(int param1, int param2, int param3) {
		this(param1, param2, param3, 0);
	}

	public Button(int param1, int param2) {
		this(param1, param2, 0);
	}
}

```

## JavaBeans Pattern
* It can be in an inconsistent state partway through its construction. 
* This class can never be immutable. 

```c
public class Button {
	private int param1 = -1; // required so no default value
	private int param2 = -1; // required so no default value
	private int param3 = 0;
	private int param4 = 0;
	private int param5 = 0;
	private int param6 = 0;

	public Button() {
		// empty
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public void setParam2(int param2) {
		this.param2 = param2;
	}

	public void setParam3(int param3) {
		this.param3 = param3;
	}

	public void setParam4(int param4) {
		this.param4 = param4;
	}

	public void setParam5(int param5) {
		this.param5 = param5;
	}

	public void setParam6(int param6) {
		this.param6 = param6;
	}
}
```

## Builder Pattern

```c
public class Button {
	private final int param1; // required so no default value
	private final int param2; // required so no default value
	private final int param3;
	private final int param4;
	private final int param5;
	private final int param6;

	public static class Builder {
		// required params NOTE it's final
		private final int param1;
		private final int param2;

		// optional params, intialized to default values.
		private int param3 = 0;
		private int param4 = 0;
		private int param5 = 0;
		private int param6 = 0;

		// constructor
		public Builder(int param1, int param2) {
			this.param1 = param1;
			this.param2 = param2;
		}

		public Builder param3(int value) {
			this.param3 = value;
			return this;
		}

		public Builder param4(int value) {
			this.param4 = value;
			return this;
		}

		public Builder param5(int value) {
			this.param5 = value;
			return this;
		}

		public Builder param6(int value) {
			this.param6 = value;
			return this;
		}


		public Button build() {
			return new Button(this);
		}
	}

	public Button(Builder builder) {
		this.param1 = builder.param1;
		this.param2 = builder.param2;
		this.param3 = builder.param3;
		this.param4 = builder.param4;
		this.param5 = builder.param5;
		this.param6 = builder.param6;
	}
}
```

You would use it like this:

```c
Button button = new Button.Builder(100, 101);
button.param3(102);
button.param4(103);
// you can make consecutive calls like this
button.param5(104).param6(106);
button.build();
```

It is recommended that any invariant check is done in the object as opposed to the builder class, so IllegalStateException should be thrown in the build method, when the build method executes the object's method. 

### Benefits of using Builder pattern

1. Constructors can have only one varags param, but builders can have multiple. 
2. Builder is flexible in a way that you can choose optional params and set them however you want, as opposed to the constructor that accepts some values as params and create an instance of an object immediately.

Or with a builder, you can return a different type of an object with different build methods. 

```c
public Button build() {
	return new Button(this);
}

public Button build1() {
	return new SubclassButton(this);
}
```

### Disadvantages of using Builder pattern
1. There is a cost to create a builder object before creating the actual object. 
2. Since the builder pattern is more verbose than the telescoping pattern, it should be used if the number of paramters exceeds 4. 
