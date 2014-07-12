package ch.chiggy.development.leaguetimer.service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Countdown {
  
    private MonsterFactory factory;
    
    public Countdown(MonsterFactory factory){
      this.factory = factory;
    }

    public Timeline createTimeLine(Label label, int startTime, ImageView imageView,
 AudioClip sound, boolean ward, String id) {
        Timeline timeline = new Timeline();
        AudioClip alert =
                new AudioClip(Countdown.class.getResource("/sounds/warning.mp3").toString());
        timeline.setCycleCount(Timeline.INDEFINITE);
        label.setTextFill(Color.WHITE);
        label.setText(makeHumanReadable(startTime));
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    int timeSeconds = startTime;
                    boolean red = false;

                    @Override
                    public void handle(ActionEvent event) {
                        timeSeconds--;
                        if (timeSeconds <= 20) {
                            if (timeSeconds == 20) {
                                alert.play();
                            }
                            red = !red;
                        } else {
                            red = false;
                        }
                        if (timeSeconds <= 0) {
                            timeline.stop();
                            label.setVisible(false);
                            timeSeconds = startTime;
                            red = false;
                            sound.play();
                            imageView.setBlendMode(null);
                            if (ward){
                              factory.removeWard(id);
                            }
                        } else {
                            if (red) {
                                label.setTextFill(Color.RED);
                            } else {
                                label.setTextFill(Color.WHITE);
                            }
                            label.setText(makeHumanReadable(timeSeconds));
                        }
                    }
                }));
        return timeline;
    }

    public String makeHumanReadable(int timeSeconds) {

        int minutes = timeSeconds / 60;
        int seconds = timeSeconds - (minutes * 60);

        String time = minutes + ":";

        if (seconds < 10) {
            time += "0" + seconds;
        } else {
            time += seconds;
        }

        return time;

    }

}
