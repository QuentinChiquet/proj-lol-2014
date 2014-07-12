package ch.chiggy.development.leaguetimer.model;


public enum Monster {
  BARON("baron.png", 420, "baron.mp3", "The Baron has "),
  BLUE("blue.png", 300, "blue.mp3", "The Blue-Thing has "),
  RED("red.png", 300, "red.mp3", "The Red-Thing has "),
  DRAGON("dragon.png", 360, "dragon.mp3", "The Dragon has "),
  GOLEM("golem.png", 300, "golem.mp3", "The Golem has "),
  BINHIB("inhibitor-b.png", 240, "ainhibitor.mp3", "The B-Inhibitor has "),
  PINHIB("inhibitor-p.png", 240, "einhibitor.mp3", "The P-Inhibitor has "),
  WIGHT("wight.png", 25, "wight.mp3", "The Weight has "),
  WOLF("wolves.png", 120, "wolf.mp3", "The Wolf has "),
  WRAITH("wraith.png", 120, "wraith.mp3", "The Wraith has ");

  private static final String RESPAWNEDTEXT = "ri-spawned";

  String image;
  int time;
  String sound;
  String speech;

  private Monster(String image, int time, String sound, String speech) {
      this.image = image;
      this.time = time;
      this.sound = sound;
      this.speech = speech + RESPAWNEDTEXT;
  }

  public String getImage() {
      return image;
  }

  public int getTime() {
      return time;
  }

  public String getSound() {
      return sound;
  }

  public String getSpeech() {
      return speech;
  }
}
