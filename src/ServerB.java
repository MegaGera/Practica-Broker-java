import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 * 
 */

public class ServerB extends ServerAbstract {
	private static String IP;
	private static int puerto;
	private ArrayList<String> lista_libros = new ArrayList<String>();

	public ServerB() {
		IP = new DatosConexion().getIP();
		puerto = new DatosConexion().getPuerto();
	}

	public String[] lista_libros() {
        String[] lista = new String[lista_libros.size()];
        return lista_libros.toArray(lista);
    }

    public void introducir_libro(String libro) {
        lista_libros.add(libro);
        System.out.printf("El libro " + libro + " ha sido introducido\n");
    }

	public static void main(String args[]) {
		try {
			ServerB server = new ServerB();
			System.setProperty("java.rmi.server.hostname", IP);
			ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = null;
			try {
				registry = LocateRegistry.createRegistry(puerto);
			} catch (Exception e) {
				registry = LocateRegistry.getRegistry(puerto);
			}
			registry.bind("ServerB", stub);
			System.out.println("ServerB registrado!!");
			
			// Se coge el objeto remoto del broker
            registry = LocateRegistry.getRegistry(IP);
            BrokerInterface brokerInterface = (BrokerInterface) registry.lookup("BrokerInterface");
            // Se registra el servidor dentro del broker
            brokerInterface.registrar_servidor(IP,"ServerB");
            System.out.println("ServerB registrado en Broker!!");
            // Se registran los servicios dentro del broker
            brokerInterface.registrar_servicio("ServerB", "lista_libros", new String[0], "String[]");
            String[] parametros = {"String libro"};
            brokerInterface.registrar_servicio("ServerB", "introducir_libro", parametros, "void");
            System.out.println("Servicios de ServerB registrados en Broker");   
		} catch (Exception e) {

		}
	}

}
