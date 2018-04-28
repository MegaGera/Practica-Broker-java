import java.lang.reflect.Method;
import java.rmi.Remote;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 * 
 */

public abstract class ServerAbstract implements ServerInterface {

	public String ejecutar_servicio(String nombre_servicio, String tipo_retorno, String[] tipo_parametros,
			String[] parametros) {
		try {
			Method metodo = this.getClass().getMethod(nombre_servicio, getClasesParametros(tipo_parametros));
			
			if (tipo_retorno.equals("String")) {
				return (String) metodo.invoke(this, parametros);
				
			} else if(tipo_retorno.equals("String[]")) {
				String[] retorno_metodo = (String[]) metodo.invoke(this, parametros);
				String retorno = "";
				for (String ret: retorno_metodo) {
					retorno += ret + "\n";
				}
				return retorno;
				
			} else if(tipo_retorno.equals("void")) {
				metodo.invoke(this, parametros);
				return "";
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Class[] getClasesParametros(String[] tipo_parametros) {
		Class[] clases_parametros = new Class[tipo_parametros.length];
		try {
			for (int i = 0; i < tipo_parametros.length; i++) {
				if (tipo_parametros[i].equals("String")) {
					clases_parametros[i] = Class.forName("java.lang.String");
				} else if (tipo_parametros[i].equals("Integer")) {
					clases_parametros[i] = Class.forName("java.lang.Integer");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases_parametros;
	}
}
