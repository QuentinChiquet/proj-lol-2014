package ch.chiggy.development.leaguetimer.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


public class Ward {

  Point2D location;
  Image image;
  int seconds;
  WardType type;
  static final AudioClip SOUND = new AudioClip(Ward.class.getResource("/sounds/ward.wav")
      .toString());

  public Ward(WardType type, Point2D location) {
    this.location = location;
    image = new Image(Ward.class.getResource("/images/" + type.getImage()).toString());
    seconds = type.getTime();
    this.type = type;
  }

  public Point2D getLocation() {
    return location;
  }

  public Image getImage() {
    return image;
  }

  public int getSeconds() {
    return seconds;
  }

  public WardType getType() {
    return type;
  }

  public static AudioClip getSound() {
    return SOUND;
  }
}
