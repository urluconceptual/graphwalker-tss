package org.unibuc.login;

public class Session {
    private final String userId;
    private boolean active;
    private long lastActivityEpochSeconds;

    public Session(String userId, long now) {
        this.userId = userId;
        this.active = true;
        this.lastActivityEpochSeconds = now;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isActive() {
        return active;
    }

    public long getLastActivityEpochSeconds() {
        return lastActivityEpochSeconds;
    }

    public void markActivity(long now) {
        this.lastActivityEpochSeconds = now;
    }

    public void expire() {
        this.active = false;
    }
}