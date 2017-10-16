public class SingleDad {
    private static final SingleDad INSTANCE = new SingleDad(30, 180);
    public int age;
    public int height;

    private SingleDad(int age, int height) {
        // constructor not available
        this.age = age;
        this.height = height;
    }

    public static SingleDad getInstance() {
        return INSTANCE;
    }

    public void getOlder() {
        age++;
    }

    public void getShorter() {
        height--;
    }
}
