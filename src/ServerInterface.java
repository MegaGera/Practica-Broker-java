import java.rmi.Remote;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 * 
 */

public interface ServerInterface extends Remote{
	String ejecutar_servicio(String nombre_servicio, String tipo_return, 
							 String [] tipo_parametros, String [] parametros);
}
