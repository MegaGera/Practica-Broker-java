import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ClienteC {

  private static final String brokerIP = "192.168.1.12";

  public static void main(String[] args) {
    try {
      System.setProperty("java.rmi.server.hostname", "192.168.1.12");
      Registry registry;
      try {
    	   registry = LocateRegistry.createRegistry(6090);
      } catch (Exception e) {
    	   registry = LocateRegistry.getRegistry(6090);
      }
      
      BrokerInterface brokerInterface = (BrokerInterface) registry.lookup("BrokerInterface");
      boolean running = true;
      while (running) {
        System.out.println("Elige escenario:");
        System.out.println("\t1) Escenario 2");
        System.out.println("\t2) Escenario 3");
        System.out.println("\t3) Salir");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("> ");
        try {
          int escenario = Integer.parseInt(br.readLine());

          if (escenario == 1) {
            boolean running_1 = true;
            while (running_1) {
              System.out.println("Elige una opcion");
              System.out.println("\tA) Insertar libro.");
              System.out.println("\tB) Lista de libros, fecha y hora actuales.");
              System.out.println("\tC) Salir.");
              System.out.print("> ");

              switch (br.readLine().toUpperCase()) {
                case "A":
                  System.out.print("Nombre del libro: ");
                  String[] param = {br.readLine()};
                  brokerInterface.ejecutar_servicio("introducir_libro", param);
                  break;
                case "B":
                  System.out.println(
                      brokerInterface.ejecutar_servicio("lista_libros", new String[0]));
                  System.out.println("Fecha actual: "
                      + brokerInterface.ejecutar_servicio("dar_fecha", new String[0]));
                  System.out.println("Hora actual: "
                      + brokerInterface.ejecutar_servicio("dar_hora", new String[0]));
                  break;
                default:
                  running_1 = false;
                  break;
              }
            }
          } else if (escenario == 2) {
            boolean running_2 = true;
            while (running_2) {
              ArrayList<String> lista_servicios = brokerInterface.listar_servicios();
              System.out.println("Elige un servicio:");
              int n = 0;
              for (String s : lista_servicios) {
                System.out.println("\t" + ++n + ") " + s);
              }
              System.out.print(">");
              String lol = br.readLine();
              switch(lol) {
              default: 
            	  running_2=false;
            	  break;
              }
            }
          } else {
            running = false;
          }
        } catch (Exception e) {
          System.err.println("No se pueden poner caracteres no numericos");
        }
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
