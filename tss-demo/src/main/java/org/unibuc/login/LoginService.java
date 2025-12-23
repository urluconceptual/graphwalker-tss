package org.unibuc.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginService {
    private final Map<String, String> credentials = new HashMap<>();
    private final Map<String, State> States = new HashMap<>();
    private final Map<String, Integer> failedAttempts = new HashMap<>();
    private final Map<String, Session> sessions = new HashMap<>();

    private final int maxFailedAttempts;
    private final long sessionTimeoutSeconds;

    public LoginService(int maxFailedAttempts, long sessionTimeoutSeconds) {
        this.maxFailedAttempts = maxFailedAttempts;
        this.sessionTimeoutSeconds = sessionTimeoutSeconds;
    }

    public void register(String userId, String password) {
        if (credentials.containsKey(userId)) {
            throw new IllegalStateException("User already exists");
        }
        credentials.put(userId, password);
        States.put(userId, State.EMAIL_UNVERIFIED);
        failedAttempts.put(userId, 0);
    }

    public void verifyEmail(String userId) {
        requireUser(userId);
        if (States.get(userId) != State.EMAIL_UNVERIFIED) {
            throw new IllegalStateException("Email already verified");
        }
        States.put(userId, State.ACTIVE);
    }

    public Session login(String userId, String password, long now) {
        requireUser(userId);
        State state = States.get(userId);
        if (state == State.LOCKED) {
            throw new IllegalStateException("Account locked");
        }
        if (!Objects.equals(credentials.get(userId), password)) {
            int attempts = failedAttempts.get(userId) + 1;
            failedAttempts.put(userId, attempts);
            if (attempts >= maxFailedAttempts) {
                States.put(userId, State.LOCKED);
            }
            throw new IllegalArgumentException("Invalid credentials");
        }
        failedAttempts.put(userId, 0);
        if (state == State.PASSWORD_EXPIRED) {
            throw new IllegalStateException("Password expired");
        }
        Session session = new Session(userId, now);
        sessions.put(userId, session);
        return session;
    }

    public void logout(String userId) {
        sessions.remove(userId);
    }

    public void expirePassword(String userId) {
        requireUser(userId);
        States.put(userId, State.PASSWORD_EXPIRED);
    }

    public void resetPassword(String userId, String newPassword) {
        requireUser(userId);
        credentials.put(userId, newPassword);
        States.put(userId, State.ACTIVE);
        failedAttempts.put(userId, 0);
    }

    public void tick(long now) {
        for (Session session : sessions.values()) {
            if (session.isActive()
                    && now - session.getLastActivityEpochSeconds() > sessionTimeoutSeconds) {
                session.expire();
            }
        }
        sessions.values().removeIf(s -> !s.isActive());
    }

    public State getState(String userId) {
        requireUser(userId);
        return States.get(userId);
    }

    public boolean hasActiveSession(String userId) {
        Session s = sessions.get(userId);
        return s != null && s.isActive();
    }

    private void requireUser(String userId) {
        if (!credentials.containsKey(userId)) {
            throw new IllegalArgumentException("Unknown user");
        }
    }
}
