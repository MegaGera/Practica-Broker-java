import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 *
 */

public class Broker implements BrokerInterface {
  public static final int PUERTO = new DatosConexion().getPuerto();
  
  ArrayList<Server> servidores;
  
  public Broker() {}
  @Override
  public String ejecutar_servicio(String nom_servicio, String[] parametros_servicio)
      throws RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void registrar_servidor(String host_remoto_IP_port, String nombre_registrado)
      throws RemoteException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void registrar_servicio(String nombre_registrado, String nom_servicio,
      String[] lista_param, String tipo_retorno) throws RemoteException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public ArrayList<String> listar_servicios() throws RemoteException {
    // TODO Auto-generated method stub
    return null;
  }
  
}
