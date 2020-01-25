/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoding;

import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import generadorhtmlmapbox.Ubicacion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class GeoCoding {
  private String accessToken;
  private Ubicacion ubi=null;
  /**
   * Creacion de Objeto GeoCoding para obtener latitud y longitud de un lugar 
   * @param accessToken 
   */
  public GeoCoding(String accessToken) {
        this.accessToken = accessToken;
  }
  /**
   * Consultar ubicacion de un query 
   * @param query
   * @return 
   */
  public Ubicacion consultar(String query) {
    
    MapboxGeocoding geocoding = MapboxGeocoding.builder()
      .accessToken(accessToken)
      .query(query)
      .build();
      try {
          String resultado= geocoding.executeCall().body().toJson();               
 
        
         int i = resultado.indexOf("coordinates");
         String sub= resultado.substring(i+14,resultado.length());
         int i2 = sub.indexOf("]");
         String[] u = sub.substring(0, i2).split(",");
         
         ubi =new Ubicacion(Double.parseDouble(u[1]),Double.parseDouble(u[0]));
         
   
     return ubi;
      } catch (IOException ex) {
          Logger.getLogger(GeoCoding.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
      catch (NumberFormatException e){
          System.out.println("No existen coordenadas "+e);
          return null;
      }
      catch (Exception e){
          Logger.getLogger(GeoCoding.class.getName()).log(Level.SEVERE, null, e);
          return null;
      }
  }
  

}
