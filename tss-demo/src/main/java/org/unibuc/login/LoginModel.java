package org.unibuc.login;

import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;

public interface LoginModel {

    @Edge()
    void e_ResetToInitial();

    @Vertex()
    void v_ActiveLoggedOut();

    @Edge()
    void e_LoginLockedAccount();

    @Edge()
    void e_LoginSuccess();

    @Edge()
    void e_Logout();

    @Edge()
    void e_Register();

    @Edge()
    void e_LoginInvalidPassword();

    @Vertex()
    void v_ErrorLockedLogin();

    @Vertex()
    void v_RegisteredEmailUnverified();

    @Vertex()
    void v_PasswordExpired();

    @Edge()
    void e_ResetPassword();

    @Edge()
    void e_FailLoginUntilLocked();

    @Edge()
    void e_VerifyEmail();

    @Edge()
    void e_ExpirePassword();

    @Edge()
    void e_AdminUnlockAccount();

    @Edge()
    void e_RetryLoginAfterLockedError();

    @Edge()
    void e_LoginWithExpiredPassword();

    @Vertex()
    void v_ActiveLoggedIn();

    @Vertex()
    void v_Locked();

    @Vertex()
    void v_Initial();

    @Vertex()
    void v_SessionTimedOut();

    @Edge()
    void e_SessionTimeout();

    @Edge()
    void e_LoginAfterTimeout();

    @Edge()
    void e_RetryLoginAfterError();

    @Vertex()
    void v_ErrorInvalidCredentials();
}
