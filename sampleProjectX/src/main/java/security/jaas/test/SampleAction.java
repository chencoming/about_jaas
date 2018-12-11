package security.jaas.test;

import javax.security.auth.Subject;
import java.io.File;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;

/**
 * Created by chenjinming on 2018/12/11.
 */
public class SampleAction implements PrivilegedAction {
  private final static String FOLDER_PATH = "/home/root1/comingor";
  String fileName = "";
  String message = "";

  public SampleAction(String fileName, String message){
    this.fileName = fileName;
    this.message = message;
  }

  public Object run() {
    try{
      AccessControlContext acc = AccessController.getContext();
      Subject sub = Subject.getSubject(acc);
      System.out.println("==========="+message+"=============");
      System.out.println("-----当前登录身份");
      for(Principal p : sub.getPrincipals()){
        System.out.println("---------"+p.getClass().getName()+"/"+p.getName());
      }
      System.out.println("-----开始执行操作");
      SampleAction.makeFile(fileName);
      System.out.println("======================================");
    }catch(Exception e){
      System.err.println(message + ":" +e.getMessage());
    }
    return null;
  }

  public static void makeFile(String fileName) throws Exception{
    // 尝试在工程 A 执行文件的路径中创建一个新文件
    File fs = new File(FOLDER_PATH + "/" + fileName);
    fs.createNewFile();
    System.out.println("create "+fileName+" done!");
  }
}
