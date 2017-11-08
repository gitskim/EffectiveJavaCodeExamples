public class SingleDad extends Object {

    //volatile is for multi-threading
    private volatile int hashCode;

    public transient int age;

    public transient int height;

    public SingleDad(int age, int height) {
        this.age = age;
        this.height = height;
    }

    public void getOlder() {
        age++;
    }

    public int getHeight() {
        return height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleDad singleDad = (SingleDad) o;

        if (age != singleDad.age) return false;
        return height == singleDad.height;

    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = age;
            result = 31 * result + height;
        }
        return result;
    }
}
