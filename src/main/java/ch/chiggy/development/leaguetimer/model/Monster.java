package ch.chiggy.development.leaguetimer.model;


public enum Monster {
    BARON("baron.png", 420, "baron.mp3"),
    BLUE("blue.png", 300, "blue.mp3"),
    RED("red.png", 300, "red.mp3"),
    DRAGON("dragon.png", 360, "dragon.mp3"),
    GOLEM("golem.png", 300, "golem.mp3"),
    BINHIB("inhibitor-b.png", 240, "ainhibitor.mp3"),
    PINHIB("inhibitor-p.png", 240, "einhibitor.mp3"),
    WIGHT("wight.png", 25, "wight.mp3"),
    WOLF("wolves.png", 120, "wolf.mp3"),
    WRAITH("wraith.png", 120, "wraith.mp3");

    String image;
    int time;
    String sound;

    private Monster(String image, int time, String sound) {
        this.image = image;
        this.time = time;
        this.sound = sound;
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
}
