public final class Circle {
    private static final int RADIUS = 3;
    private static final String NAME = "circle_with_rad_3cm";
    private String color;

    public static Circle create() {
        return new Circle();
    }

    public Circle setColor(String color) {
        this.color = color;
        return this;
    }
}
