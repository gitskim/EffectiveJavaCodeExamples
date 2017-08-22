public class ArianaObserver extends Observer {

    public ArianaObserver(ArianaGrande arianaGrande) {
        this.subject = arianaGrande;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("ArianaObserver updated. Ariana said : " + subject.getMessage());
    }
}
