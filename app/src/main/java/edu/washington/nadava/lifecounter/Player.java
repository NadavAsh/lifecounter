package edu.washington.nadava.lifecounter;

/**
 * Represents a player object with a life counter.
 */
public class Player {
    public static final int DEFAULT_LIFE = 20;

    private int playerId;
    private int life;

    public Player(int playerId) {
        this(playerId, DEFAULT_LIFE);
    }

    public Player(int playerId, int life) {
        this.playerId = playerId;
        this.life = life;
    }

    public int getId() {
        return playerId;
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(int value) {
        life = value;
    }

    public void incrementLife(int value) { life += value; }

    public void decrementLife(int value) { life -= value; }

    public boolean isDead() { return life <= 0; }
}
