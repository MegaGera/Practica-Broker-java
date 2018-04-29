import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
  private static final int PUERTO = new DatosConexion().getPuerto();
  private static String ip;
  ArrayList<Server> servidores;

  @SuppressWarnings("static-access")
  public Broker(String ip) {
    this.ip = ip;
    this.servidores = new ArrayList<Server>();
  }

  @Override
  public String ejecutar_servicio(String nom_servicio, String[] parametros_servicio)
      throws RemoteException {
    for (Server server : servidores) {
      for (Service service : server.getServicios()) {
        if (service.getNom_servicio().equals(nom_servicio)) {
          try {
            Registry reg = LocateRegistry.getRegistry(ip, server.getPort());
            ServerInterface serverInterface =
                (ServerInterface) reg.lookup(server.getNombre());
            return serverInterface.ejecutar_servicio(service.getNom_servicio(),
                service.getRetorno(), service.getLista_parametros(), parametros_servicio);
          } catch (NotBoundException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return "";
  }

  @Override
  public void registrar_servidor(String host_remoto_IP_port, String nombre_registrado)
      throws RemoteException {
    this.servidores.add(new Server(host_remoto_IP_port+":"+PUERTO, nombre_registrado));

  }

  @Override
  public void registrar_servicio(String nombre_registrado, String nom_servicio,
      String[] lista_param, String tipo_retorno) throws RemoteException {
    for (Server server : this.servidores) {
      if(server.getNombre().equals(nombre_registrado)) {
        server.addServicio(nom_servicio, tipo_retorno, lista_param);
      }
    }

  }

  @Override
  public ArrayList<String> listar_servicios() throws RemoteException {
   ArrayList<String> retVal = new ArrayList<String>();
    for (Server server : this.servidores) {
      for (Service service : server.getServicios()) {
        retVal.add(service.toString());
      }
    }
    return retVal;
  }
  
  public static void main (String[] args) {
    if (args.length == 0) {
      System.err.println("Argumentos insuficientes");
    } else {
      try {
        Broker broker = new Broker(args[0]);
        System.setProperty("java.rmi.server.hostname", ip);
        BrokerInterface stub = (BrokerInterface) UnicastRemoteObject.exportObject(broker, 0);
        Registry registry = null;
        try {
          registry = LocateRegistry.createRegistry(PUERTO);
        } catch (RemoteException e) {
          registry = LocateRegistry.getRegistry(PUERTO);
        }
        
        registry.bind("BrokerInterface", stub);
        
        System.out.println("Broker registrado");
      } catch (RemoteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (AlreadyBoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
