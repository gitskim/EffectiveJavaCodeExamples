public class CircleProxy {
    private Circle circle;

    public CircleProxy create() {
        circle = Circle.create();
        return this;
    }

    public CircleProxy setColor(String color) {
        circle.setColor(color);
        return this;
    }
}
