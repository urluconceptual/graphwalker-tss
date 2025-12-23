package com.example.login;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.unibuc.login.LoginModel;
import org.unibuc.login.LoginService;
import org.unibuc.login.State;

import static org.junit.Assert.*;

@GraphWalker(
        value = "LoginTest",
        start = "v_Initial"
)
public class LoginTest extends ExecutionContext implements LoginModel {

    private static final String USER_ID = "cool_user1";
    private static final String PASSWORD = "Password123!";
    private static final String WRONG_PASSWORD = "WrongPassword:P";

    private final LoginService service =
            new LoginService(3, 60);

    // -------- Vertices --------

    @Override
    public void v_Initial() {
        System.out.print("-> (v_Initial) ");
        assertThrows(IllegalArgumentException.class,
                () -> service.getState(USER_ID));
    }

    @Override
    public void v_RegisteredEmailUnverified() {
        System.out.print("-> (v_RegisteredEmailUnverified) ");
        assertEquals(State.EMAIL_UNVERIFIED, service.getState(USER_ID));
        assertFalse(service.hasActiveSession(USER_ID));
    }

    @Override
    public void v_ActiveLoggedOut() {
        System.out.print("-> (v_ActiveLoggedOut) ");
        assertEquals(State.ACTIVE, service.getState(USER_ID));
        assertFalse(service.hasActiveSession(USER_ID));
    }

    @Override
    public void v_ActiveLoggedIn() {
        System.out.print("-> (v_ActiveLoggedIn) ");
        assertEquals(State.ACTIVE, service.getState(USER_ID));
        assertTrue(service.hasActiveSession(USER_ID));
    }

    @Override
    public void v_Locked() {
        System.out.print("-> (v_Locked) ");
        assertEquals(State.LOCKED, service.getState(USER_ID));
        assertFalse(service.hasActiveSession(USER_ID));
    }

    @Override
    public void v_PasswordExpired() {
        System.out.print("-> (v_PasswordExpired) ");
        assertEquals(State.PASSWORD_EXPIRED, service.getState(USER_ID));
        assertFalse(service.hasActiveSession(USER_ID));
    }

    @Override
    public void v_SessionTimedOut() {
        System.out.print("-> (v_SessionTimedOut) ");
        assertEquals(State.ACTIVE, service.getState(USER_ID));
        assertFalse(service.hasActiveSession(USER_ID));
    }

    @Override
    public void v_ErrorInvalidCredentials() {
        System.out.print("-> (v_ErrorInvalidCredentials) ");
        assertEquals(State.ACTIVE, service.getState(USER_ID));
        assertFalse(service.hasActiveSession(USER_ID));
    }

    @Override
    public void v_ErrorLockedLogin() {
        System.out.print("-> (v_ErrorLockedLogin) ");
        assertEquals(State.LOCKED, service.getState(USER_ID));
        assertFalse(service.hasActiveSession(USER_ID));
    }

    @Override
    public void e_Register() {
        System.out.print("-- e_Register -");
        service.register(USER_ID, PASSWORD);
    }

    @Override
    public void e_VerifyEmail() {
        System.out.print("-- e_VerifyEmail -");
        service.verifyEmail(USER_ID);
    }

    @Override
    public void e_LoginSuccess() {
        System.out.print("-- e_LoginSuccess -");
        long now = System.currentTimeMillis() / 1000;
        service.login(USER_ID, PASSWORD, now);
    }

    @Override
    public void e_LoginInvalidPassword() {
        System.out.print("-- e_LoginInvalidPassword -");
        long now = System.currentTimeMillis() / 1000;
        assertThrows(IllegalArgumentException.class,
                () -> service.login(USER_ID, WRONG_PASSWORD, now));
    }

    @Override
    public void e_RetryLoginAfterError() {
        System.out.print("-- e_RetryLoginAfterError -");
    }

    @Override
    public void e_LoginLockedAccount() {
        System.out.print("-- e_LoginLockedAccount -");
        long now = System.currentTimeMillis() / 1000;
        assertThrows(IllegalStateException.class,
                () -> service.login(USER_ID, PASSWORD, now));
    }

    @Override
    public void e_RetryLoginAfterLockedError() {
        System.out.print("-- e_RetryLoginAfterLockedError -");
    }

    @Override
    public void e_FailLoginUntilLocked() {
        System.out.print("-- e_FailLoginUntilLocked -");
        long now = System.currentTimeMillis() / 1000;
        for (int i = 0; i < 3; i++) {
            long t = now + i;
            assertThrows(IllegalArgumentException.class,
                    () -> service.login(USER_ID, WRONG_PASSWORD, t));
        }
        assertEquals(State.LOCKED, service.getState(USER_ID));
    }

    @Override
    public void e_Logout() {
        System.out.print("-- e_Logout -");
        service.logout(USER_ID);
    }

    @Override
    public void e_SessionTimeout() {
        System.out.print("-- e_SessionTimeout -");
        long now = System.currentTimeMillis() / 1000;
        service.tick(now + 120);
    }

    @Override
    public void e_LoginAfterTimeout() {
        System.out.print("-- e_LoginAfterTimeout -");
        long now = System.currentTimeMillis() / 1000;
        service.login(USER_ID, PASSWORD, now);
    }

    @Override
    public void e_ExpirePassword() {
        System.out.print("-- e_ExpirePassword -");
        service.expirePassword(USER_ID);
    }

    @Override
    public void e_LoginWithExpiredPassword() {
        System.out.print("-- e_LoginWithExpiredPassword -");
        long now = System.currentTimeMillis() / 1000;
        assertThrows(IllegalStateException.class,
                () -> service.login(USER_ID, PASSWORD, now));
    }

    @Override
    public void e_ResetPassword() {
        System.out.print("-- e_ResetPassword -");
        service.resetPassword(USER_ID, PASSWORD + "2");
    }

    @Override
    public void e_AdminUnlockAccount() {
        System.out.print("-- e_AdminUnlockAccount -");
        service.resetPassword(USER_ID, PASSWORD);
    }

    @Override
    public void e_ResetToInitial() {
        System.out.println("-- e_ResetToInitial -");
    }
}
