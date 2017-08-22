import java.util.List;

/**
 * Created by kimsuh on 4/27/17.
 */
public class MainClass {
    public static void main(String[] args) {
        ArianaGrande subject = new ArianaGrande();

        new ArianaObserver(subject);
        new GrandeObserver(subject);

        System.out.println("First message");
        subject.setMessage("Hollywood girl");
        System.out.println("Second message");
        subject.setMessage("One last time");

        Outer.Inner inner = new Outer().new Inner();


    }
}
