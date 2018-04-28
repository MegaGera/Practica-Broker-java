import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 *
 */

public interface BrokerInterface extends Remote {
	String ejecutar_servicio(String nom_servicio, String[] parametros_servicio) throws RemoteException;
}
