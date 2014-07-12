package ch.chiggy.development.leaguetimer.model;

public enum WardType {
    GHOST("ghost.png", 60),
    SIGHT("sight.png", 180),
    VISION("vision.png", 180);

    private int time;
    private String image;

    private WardType(String image, int time) {
        this.image = image;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

}
