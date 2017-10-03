public class Button {
    private int color;
    private int shape;
    private String message;
    private int width;
    private int length;

    public Button(Builder builder) {
        color = builder.color;
        shape = builder.shape;
        message = builder.message;
        width = builder.width;
        length = builder.length;
    }

    public static class Builder {
        private int color;
        private int shape;
        private String message;
        private int width;
        private int length;

        public Builder setColor(int color) {
            this.color = color;
            return this;
        }

        public Builder setShape(int shape) {
            this.shape = shape;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setLength(int length) {
            this.length = length;
            return this;
        }

        public Button build() {
            return new Button(this);
        }
    }
}

