package ch.chiggy.development.leaguetimer.view;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ch.chiggy.development.leaguetimer.controller.Controller;
import ch.chiggy.development.leaguetimer.model.WardType;


public class Gui {

  private Pane root = new Pane();
  private Stage primaryStage;
  private Controller controller;

  public Gui(Stage primaryStage, Controller controller) {

    this.primaryStage = primaryStage;
    this.controller = controller;

    root.getStylesheets().add("style.css");

    this.primaryStage.setResizable(false);
    this.primaryStage.setTitle("League Of Legends Timer V0.3");
    this.primaryStage.setScene(new Scene(root, 949, 816));
    root.setOnMousePressed(event -> {
      Point2D point = new Point2D(event.getX(), event.getY());
      this.controller.newWard(WardType.SIGHT, point);
    });
    this.primaryStage.show();
  }

  public void addMonsters(List<AnchorPane> listMonsters) {
    for (AnchorPane monster : listMonsters) {
      root.getChildren().add(monster);
    }
  }

  public void removeAnchorPane(String id) {
    ObservableList<Node> listNode = root.getChildren();
    for (int x = 0; x < listNode.size(); x++) {
      if (listNode.get(x).getId() != null) {
        if (listNode.get(x).getId().equals(id)) {
          root.getChildren().remove(x);
        }
      }
    }
  }

  public void addWard(AnchorPane wardPane) {
    root.getChildren().add(wardPane);
  }
}
