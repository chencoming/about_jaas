package security.jaas.test;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * Created by chenjinming on 2018/12/10.
 */
public class SampleCallbackHandler implements CallbackHandler{
  private String username;
  private String password;

  public SampleCallbackHandler(final String username, final String password) {
    this.username = username;
    this.password = password;
  }

  public void handle(Callback[] callbacks) throws IOException,
          UnsupportedCallbackException {
    for (int index = 0; index < callbacks.length; index++) {
      if (callbacks[index] instanceof NameCallback) {
        NameCallback ncb = (NameCallback) callbacks[index];
        ncb.setName(username);
      }
      if (callbacks[index] instanceof PasswordCallback) {
        PasswordCallback pcb = (PasswordCallback) callbacks[index];
        pcb.setPassword(password.toCharArray());
      }
    }
  }

}
