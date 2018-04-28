import java.util.Arrays;

/**
 * @author Jorge Rambla 718911
 * @author Gonzalo Berné 715891
 * @author Alejandro
 * 
 * @version 1.0.0
 *
 */
public class Service {
  private String nom_servicio;
  private String retorno;
  private String[] lista_parametros;

  public Service(String nom_servicio, String retorno, String[] lista_parametros) {
    this.nom_servicio = nom_servicio;
    this.retorno = retorno;
    this.lista_parametros = lista_parametros;
  }
  
  @Override
  public String toString() {
    return retorno + " " + nom_servicio + "(" + Arrays.toString(this.lista_parametros)
        + ")";

  }

  /**
   * @return the nom_servicio
   */
  public String getNom_servicio() {
    return nom_servicio;
  }

  /**
   * @return the retorno
   */
  public String getRetorno() {
    return retorno;
  }

  /**
   * @return the lista_parametros
   */
  public String[] getLista_parametros() {
    return lista_parametros;
  }

}
