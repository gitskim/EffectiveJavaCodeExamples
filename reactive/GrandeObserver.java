/**
 * Created by kimsuh on 4/27/17.
 */
public class GrandeObserver extends Observer {

    public GrandeObserver(ArianaGrande arianaGrande) {
        this.subject = arianaGrande;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("GrandeObserver updated. Grande said : " + subject.getMessage());
    }
}
