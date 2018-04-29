import java.util.ArrayList;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 *
 */
public class Server {
  private String nombre_registrado;
  private String host_remoto_IP_port;
  private ArrayList<Service> servicios;
  
  public Server(String host_remoto_IP_port, String nombre_registrado) {
    this.nombre_registrado = nombre_registrado;
    this.host_remoto_IP_port = host_remoto_IP_port;
    servicios = new ArrayList<Service>();
  }
  
  public String getIP() {
    String[] retVal = this.host_remoto_IP_port.split(":");
    return retVal[0];
  }
  
  public int getPort() {
    String[] retVal = this.host_remoto_IP_port.split(":");
    return Integer.parseInt(retVal[1]);
  }
  
  public String getNombre() {
    return this.nombre_registrado;
  }
  
  public ArrayList<Service> getServicios() {
    return this.servicios;
  }
  
  public void addServicio(Service new_service) {
    this.servicios.add(new_service);
  }
  
  public void addServicio(String nom_servicio, String retorno, String[] lista_parametros) {
    this.servicios.add(new Service(nom_servicio, retorno, lista_parametros));
  }
}
