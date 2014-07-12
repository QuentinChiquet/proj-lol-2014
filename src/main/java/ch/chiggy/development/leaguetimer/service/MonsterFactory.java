package ch.chiggy.development.leaguetimer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javafx.animation.Animation.Status;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ch.chiggy.development.leaguetimer.controller.Controller;
import ch.chiggy.development.leaguetimer.model.Monster;
import ch.chiggy.development.leaguetimer.model.NpcMonster;
import ch.chiggy.development.leaguetimer.model.Ward;
import ch.chiggy.development.leaguetimer.model.WardType;

public class MonsterFactory {

  private Controller controller;
  private Countdown countdown = new Countdown();

  public MonsterFactory(Controller controller) {
    this.controller = controller;
  }


  public AnchorPane createWard(Ward ward) {
    AnchorPane anchor = new AnchorPane();
    int startTime = ward.getSeconds();
    ImageView imageView = new ImageView(ward.getImage());
    imageView.setFitHeight(30);
    imageView.setFitWidth(30);
    Label label = new Label();
    anchor.setLayoutX(ward.getLocation().getX() - 15);
    anchor.setLayoutY(ward.getLocation().getY() - 15);
    AnchorPane.setLeftAnchor(label, -5.0);
    AnchorPane.setTopAnchor(label, 35.0);
    anchor.getChildren().addAll(imageView, label);
    Timeline timeline = countdown.createTimeLine(label, startTime, imageView, Ward.getSound());
    timeline.play();
    String id = UUID.randomUUID().toString();
    anchor.setId(id);
    anchor.setOnMousePressed(event -> {
      event.consume();
    });
    anchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
      Timeline timer = timeline;

      @Override
      public void handle(MouseEvent event) {
        switch (event.getButton()) {
          case PRIMARY:
            switch (ward.getType()) {
              case SIGHT:
                timer.stop();
                controller.removeAnchorPane(id);
                controller.newWard(WardType.VISION, ward.getLocation());
                break;
              case VISION:
                timer.stop();
                controller.removeAnchorPane(id);
                controller.newWard(WardType.GHOST, ward.getLocation());
                break;
              case GHOST:
                controller.removeAnchorPane(id);
                timer.stop();
                break;
              default:
                throw new NoSuchElementException("Unkown Ward type!");
            }
            break;
          case SECONDARY:
            timer.stop();
            controller.removeAnchorPane(id);
            break;
          default:
            break;
        }
      }
    });

    return anchor;
  }

  public AnchorPane createMonster(NpcMonster npcMonster) {

    AnchorPane anchor = new AnchorPane();
    int startTime = npcMonster.getTimeInSeconds();
    ImageView imageView = new ImageView(npcMonster.getImage());
    if (npcMonster.isInhibitor()) {
      imageView.setFitHeight(90);
      imageView.setFitWidth(90);
    } else {
      imageView.setFitHeight(50);
      imageView.setFitWidth(50);
    }
    Label label = new Label();
    label.setVisible(false);
    anchor.setLayoutX(npcMonster.getLocation().getX());
    anchor.setLayoutY(npcMonster.getLocation().getY());
    AnchorPane.setLeftAnchor(label, 5.0);
    AnchorPane.setTopAnchor(label, 10.0);
    anchor.getChildren().addAll(imageView, label);
    anchor.setOnMousePressed(event -> {
      event.consume();
    });

    anchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
      Timeline timeline = null;

      @Override
      public void handle(MouseEvent event) {

        imageView.setBlendMode(BlendMode.MULTIPLY);
        label.setVisible(true);

        switch (event.getButton()) {
          case PRIMARY:
            if (timeline == null || timeline.getStatus() == Status.STOPPED) {
              timeline =
                  countdown.createTimeLine(label, startTime, imageView, npcMonster.getSound());
              timeline.playFromStart();
            } else if (timeline.getStatus() == Status.PAUSED) {
              timeline.play();
            } else if (timeline.getStatus() == Status.RUNNING) {
              timeline.pause();
            }
            break;
          case SECONDARY:
            timeline.stop();
            timeline = countdown.createTimeLine(label, startTime, imageView, npcMonster.getSound());
            timeline.playFromStart();

          default:
            break;
        }
      }

    });

    return anchor;
  }

  public List<AnchorPane> createAllMonsters() {

    List<AnchorPane> monsterList = new ArrayList<AnchorPane>();

    monsterList.add(createMonster(new NpcMonster(Monster.DRAGON, new Point2D(640, 480))));
    monsterList.add(createMonster(new NpcMonster(Monster.BARON, new Point2D(380, 190))));
    monsterList.add(createMonster(new NpcMonster(Monster.BLUE, new Point2D(310, 290))));
    monsterList.add(createMonster(new NpcMonster(Monster.BLUE, new Point2D(690, 360))));
    monsterList.add(createMonster(new NpcMonster(Monster.RED, new Point2D(530, 510))));
    monsterList.add(createMonster(new NpcMonster(Monster.RED, new Point2D(490, 180))));
    monsterList.add(createMonster(new NpcMonster(Monster.GOLEM, new Point2D(570, 570))));
    monsterList.add(createMonster(new NpcMonster(Monster.GOLEM, new Point2D(450, 120))));
    monsterList.add(createMonster(new NpcMonster(Monster.PINHIB, new Point2D(620, 60))));
    monsterList.add(createMonster(new NpcMonster(Monster.PINHIB, new Point2D(660, 140))));
    monsterList.add(createMonster(new NpcMonster(Monster.PINHIB, new Point2D(770, 160))));
    monsterList.add(createMonster(new NpcMonster(Monster.BINHIB, new Point2D(150, 490))));
    monsterList.add(createMonster(new NpcMonster(Monster.BINHIB, new Point2D(250, 520))));
    monsterList.add(createMonster(new NpcMonster(Monster.BINHIB, new Point2D(270, 640))));
    monsterList.add(createMonster(new NpcMonster(Monster.WIGHT, new Point2D(240, 280))));
    monsterList.add(createMonster(new NpcMonster(Monster.WIGHT, new Point2D(790, 370))));
    monsterList.add(createMonster(new NpcMonster(Monster.WOLF, new Point2D(320, 360))));
    monsterList.add(createMonster(new NpcMonster(Monster.WOLF, new Point2D(675, 290))));
    monsterList.add(createMonster(new NpcMonster(Monster.WRAITH, new Point2D(470, 420))));
    monsterList.add(createMonster(new NpcMonster(Monster.WRAITH, new Point2D(520, 240))));


    return monsterList;
  }
}
