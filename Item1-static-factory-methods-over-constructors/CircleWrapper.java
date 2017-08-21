public class CircleWrapper {
    private CircleProxy circleProxy;

    public CircleWrapper(CircleProxy circleProxy) {
        this.circleProxy = circleProxy;
    }

    public void setCircleColor(String color) {
        circleProxy = createCircle();
        circleProxy.setColor(color);
    }

    private CircleProxy createCircle() {
        return circleProxy.create();
    }
}
