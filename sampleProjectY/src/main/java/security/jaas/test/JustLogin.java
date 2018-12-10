package security.jaas.test;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;

/**
 * Created by chenjinming on 2018/12/10.
 */
public class JustLogin {

  public static LoginContext login(String name, String password) {
    try {
      //此处指定了使用配置文件的“Sample”验证模块，对应的实现类为SampleLoginModule
      LoginContext lc = new LoginContext("Sample", new SampleCallbackHandler(
              name, password));
      lc.login();// 如果验证失败会抛出异常

      return lc;
    } catch (LoginException e) {
      e.printStackTrace();
      return null;
    } catch (SecurityException e) {
      e.printStackTrace();
      return null;
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static void main(String args[]) throws Exception{
    // 打开系统安全权限检查开关
    System.setSecurityManager(new SecurityManager());


    LoginContext lc = JustLogin.login("wd", "111111");
    System.out.println("===============完成登录===========");
    Subject sub = lc.getSubject();
    System.out.println("当前用户身份：");
    for(Principal p : sub.getPrincipals()){
      System.out.println("------"+p.getClass()+"/"+p.getName());
    }
    lc.logout();
    System.out.println("===============用户登出===========");
    sub = lc.getSubject();
    System.out.println("当前用户身份：");
    if(sub.getPrincipals()==null || sub.getPrincipals().isEmpty()){
      System.out.println("------无");
    }else{
      for(Principal p : sub.getPrincipals()){
        System.out.println("------"+p.getClass()+"/"+p.getName());
      }
    }
  }

}
