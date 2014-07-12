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

  private Announcer announcer;
  private String speech;
  private AudioClip audioClip;

  public Timeline createTimeLineWithAudioClip(Label label, int startTime, ImageView imageView,
      AudioClip sound) {
      audioClip = sound;
      return createTimeLine(label, startTime, imageView, true);
  }

  public Timeline createTimeLineWithSpeech(Label label, int startTime, ImageView imageView, String speech) {
      announcer = new Announcer();
      this.speech = speech;
      return createTimeLine(label, startTime, imageView, false);
  }

  /**
   * Creates a new timeline which acts as a timer for a GUI
   * The AudioMode selects the kind of audio which is played
   * when a timer reaches 00:00:00. Can be a AudioClip or a
   * String which represents spoken text.
   *
   * @param label
   * @param startTime
   * @param imageView
   * @param audioMode true=clip, false=spoken
   * @return a usable timeline
   */
  private Timeline createTimeLine(Label label, int startTime, ImageView imageView, boolean audioMode) {

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
                      playAudioComponent(audioMode);
                      imageView.setBlendMode(null);
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

  private String makeHumanReadable(int timeSeconds) {

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

  private void playAudioComponent(boolean clipMode) {
      if (clipMode) {
          audioClip.play();
      } else {
          announcer.speak(speech);
      }
  }
}
