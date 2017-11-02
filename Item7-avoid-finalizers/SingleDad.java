class SingleDad extends Object {
	public transient int age;
	public transient int height;

	private final Object finalizerGuardian = new Object() {
		@Override
			protected void finalize() throws Throwable {
				System.out.println("guardian finalize  called");
				// free resource here
			}
	};

	public SingleDad() {
		// constructor not available
	}


	public void getOlder() {
		age++;
	}

	public void getShorter() {
		height--;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
