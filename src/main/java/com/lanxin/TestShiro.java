package com.lanxin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class TestShiro {

SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();

    @Before
    public void addUser()
    {

        simpleAccountRealm.addAccount("admin","123","user:select","user:update");

    }

    @Test
    public void Test()
    {

        DefaultSecurityManager defaultSessionManager=new  DefaultSecurityManager();

        defaultSessionManager.setRealm(simpleAccountRealm);


        SecurityUtils.setSecurityManager(defaultSessionManager);
        Subject subject=SecurityUtils.getSubject();

        try {
            UsernamePasswordToken token=new UsernamePasswordToken("admin","123");
            subject.login(token);
            System.out.println(subject.isAuthenticated());

            subject.checkRoles("user:select","user:update");



        } catch (IncorrectCredentialsException e) {
            System.out.println("IncorrectCredentialsException：异常（password错误）");
        }catch (UnknownAccountException e)
        {
            System.out.println("UnknownAccountException 异常 (用户名错误)");
        }

    }

}
