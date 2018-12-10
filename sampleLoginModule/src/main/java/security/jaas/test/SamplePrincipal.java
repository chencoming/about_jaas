package security.jaas.test;

import java.security.Principal;

/**
 * Created by chenjinming on 2018/12/10.
 */
public class SamplePrincipal implements Principal {
  private String name;
  public SamplePrincipal(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }
  public boolean equals(Object o) {
    return (o instanceof SamplePrincipal)
            && this.name.equalsIgnoreCase(((SamplePrincipal) o).name);
  }
  public int hashCode() {
    return name.toUpperCase().hashCode();
  }

}
