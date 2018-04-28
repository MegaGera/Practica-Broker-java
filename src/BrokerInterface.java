import java.rmi.Remote;
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

public interface BrokerInterface extends Remote {

  /**
   * @param nom_servicio
   * @param parametros_servicio
   * @return Retorno del servicio ejecutado en String
   * @throws RemoteException
   */
  String ejecutar_servicio(String nom_servicio, String[] parametros_servicio)
      throws RemoteException;

  /**
   * @param host_remoto_IP_port
   * @param nombre_registrado
   * @throws RemoteException
   */
  void registrar_servidor(String host_remoto_IP_port, String nombre_registrado)
      throws RemoteException;

  /**
   * @param nombre_registrado
   * @param nom_servicio
   * @param lista_param
   * @param tipo_retorno
   * @throws RemoteException
   */
  void registrar_servicio(String nombre_registrado, String nom_servicio,
      String[] lista_param, String tipo_retorno) throws RemoteException;
  
  /**
   * @return Lista de servicios
   * @throws RemoteException
   */
  ArrayList<String> listar_servicios() throws RemoteException;
}
