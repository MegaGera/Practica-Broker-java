import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 * 
 */

public class ServerA extends ServerAbstract {
	private static String IP;
	private static int puerto;

	public ServerA() {
		IP = new DatosConexion().getIP();
		puerto = new DatosConexion().getPuerto();
	}

	public String dar_hora() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String dar_fecha() {
		DateFormat dateFormat = new SimpleDateFormat("dd // MMMM // yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void main(String args[]) {
		try {
			ServerA server = new ServerA();
			System.setProperty("java.rmi.server.hostname", IP);
			ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = null;
			try {
				registry = LocateRegistry.createRegistry(puerto);
			} catch (Exception e) {
				registry = LocateRegistry.getRegistry(puerto);
			}
			registry.bind("ServerA", stub);
			System.out.println("ServerA registrado!!");
			
			// Se coge el objeto remoto del broker
            registry = LocateRegistry.getRegistry(IP);
            BrokerInterface brokerInterface = (BrokerInterface) registry.lookup("BrokerInterface");
            // Se registra el servidor dentro del broker
            brokerInterface.registrar_servidor(IP,"ServerA");
            System.out.println("ServerA registrado en Broker!!");
            // Se registran los servicios dentro del broker
            brokerInterface.registrar_servicio("ServerA", "dar_hora", new String[0], "String");
            brokerInterface.registrar_servicio("ServerA", "dar_fecha", new String[0], "String");
            System.out.println("Servicios de ServerA registrados en Broker!!");
		} catch (Exception e) {

		}
	}

}
