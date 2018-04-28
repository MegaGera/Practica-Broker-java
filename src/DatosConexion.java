/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 *
 */
public class DatosConexion {
  public DatosConexion() {}
  
  private static final String IP = "192.168.1.12";
  
  private static final int PUERTO = 6090;
  
  public int getPuerto() {
    return PUERTO;
  }
  
  public String getIP() {
    return IP;
  }
}
