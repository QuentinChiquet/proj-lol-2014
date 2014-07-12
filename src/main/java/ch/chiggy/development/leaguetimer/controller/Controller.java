package ch.chiggy.development.leaguetimer.controller;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ch.chiggy.development.leaguetimer.model.Ward;
import ch.chiggy.development.leaguetimer.model.WardType;
import ch.chiggy.development.leaguetimer.service.MonsterFactory;
import ch.chiggy.development.leaguetimer.view.Gui;



public class Controller extends Application {

  private Gui gui;
  private MonsterFactory monsterFactory;
  private List<AnchorPane> monsterList;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    gui = new Gui(primaryStage, this);
    monsterFactory = new MonsterFactory(this);
    monsterList = monsterFactory.createAllMonsters();
    gui.addMonsters(monsterList);
  }

  public void removeAnchorPane(String id) {
    gui.removeAnchorPane(id);
  }


  public void newWard(WardType type, Point2D location) {
    Ward ward = new Ward(type, location);
    gui.addWard(monsterFactory.createWard(ward));
  }
}
