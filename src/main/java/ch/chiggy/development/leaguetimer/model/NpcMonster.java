package ch.chiggy.development.leaguetimer.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


public class NpcMonster {

    private Point2D location;
    private Image image;
    private int timeInSeconds;
    private boolean inhibitor;
    private AudioClip sound;

    public NpcMonster(Monster monster, Point2D location) {
        this.location = location;
        image = new Image("images/" + monster.getImage());
        timeInSeconds = monster.getTime();
        sound =
                new AudioClip(NpcMonster.class.getResource("/sounds/" + monster.getSound())
                        .toString());
        if (monster == Monster.BINHIB || monster == Monster.PINHIB) {
            inhibitor = true;
        } else {
            inhibitor = false;
        }
    }

    public Point2D getLocation() {
        return this.location;
    }

    public Image getImage() {
        return image;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public boolean isInhibitor() {
        return inhibitor;
    }

    public AudioClip getSound() {
        return sound;
    }

}
