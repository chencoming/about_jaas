package security.jaas.test;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import java.security.PrivilegedAction;



/**
 * Hello world!
 *
 */
public class LoginAndAuthorize {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main( String[] args ) throws Exception{

        // 打开系统安全权限检查开关
        System.setSecurityManager(new SecurityManager());
        LoginContext lc = JustLogin.login("wd", "111111");

        Subject sub = lc.getSubject();

        PrivilegedAction action1 = new SampleAction("temp111.txt", "登录状态下doAsPrivileged调用该操作");
        Subject.doAsPrivileged(sub, action1, null);
        PrivilegedAction action2 = new SampleAction("temp112.txt", "登录状态下doAs调用该操作");
        Subject.doAs(sub, action2);

        lc.logout();
        PrivilegedAction action3 = new SampleAction("temp113.txt", "登出状态下doAsPrivileged调用该操作");
        Subject.doAsPrivileged(sub, action3, null);
        PrivilegedAction action4 = new SampleAction("temp114.txt", "登出状态下doAs调用该操作");
        Subject.doAs(sub, action4);

    }
}
