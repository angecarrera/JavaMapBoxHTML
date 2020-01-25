/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadorhtmlmapbox;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACarrera
 */
public class GeneradorHTMLMapBox {
    private String accessToken;
    private double latitudInicial;
    private double longitudInicial;
    private List<String> HTML=new ArrayList<>();
    private List<Ubicacion> marcadores = new ArrayList<>();
    private static final String scriptSRC = "'https://api.tiles.mapbox.com/mapbox-gl-js/v0.49.0/mapbox-gl.js'";
    /**
     * Crea un objeto GeneradorHTMLMapBox para crear un archivo HTML de mapa
     * @param accessToken Token de acceso obtenido en mapbox
     * @param latitudInicial  Latitud inicial donde se centrara el mapa
     * @param longitudInicial Longitud inicial donde se centrara el mapa
     */
    public GeneradorHTMLMapBox(String accessToken, double latitudInicial, double longitudInicial) {
        this.accessToken = accessToken;
        this.latitudInicial = latitudInicial;
        this.longitudInicial = longitudInicial;
    }
    /**
     * Crea un objeto GeneradorHTMLMapBox para crear un archivo HTML de mapa 
     * @param accessToken  Token de acceso obtenido en mapbox
     */
    public GeneradorHTMLMapBox(String accessToken) {
        this.accessToken = accessToken;
    }

   
    private void armarCabecera(){
       HTML.add( "<!DOCTYPE html><html><head>    <script src="+scriptSRC+"></script>");
       HTML.add( "<link href='https://api.tiles.mapbox.com/mapbox-gl-js/v0.49.0/mapbox-gl.css' rel='stylesheet' />");
       HTML.add( "<style>");
       HTML.add( "body { margin:0; padding:0; }");
       HTML.add("#map { position:absolute; top:0; bottom:0; width:100%; height: 100%; }  "  );
       HTML.add("</style></head>");
       HTML.add( "<body> ");
       HTML.add("<div id = 'map'></div>");     
       HTML.add("<script>");
       
   }        
    
    private void crearMapa(){
       HTML.add("mapboxgl.accessToken = '"+accessToken +"';"+"\n") ; 
       HTML.add("var map = new mapboxgl.Map({"+"\n");
       HTML.add("container: 'map',"+"\n"); 
       HTML.add(" style: 'mapbox://styles/mapbox/streets-v9',"+"\n");  
       HTML.add(" center: ["+longitudInicial+","+latitudInicial +"],"+"\n");  
       HTML.add(" zoom: 13"+"\n");
       HTML.add(" });"+"\n");
       if (marcadores.size()>0){
           int i=0;
           for(Ubicacion u: marcadores){
               HTML.add("var marker"+i+" = new mapboxgl.Marker()");
               HTML.add(".setLngLat(["+u.getLongitud()+","+u.getLatitud()+"])");
               HTML.add(".setPopup(new mapboxgl.Popup({ offset: 25 })");
               HTML.add(".setHTML('" + u.getDescripcion()+ "'))");
               HTML.add(".addTo(map);");
               i++;
           }
       }
       HTML.add("</script></body></html>");
    }
    /**
     * Graba archivo html en la ruta especificada por parametro
     * @param archivo nombre de archivo debe contener extension html
     */
    public void grabarHTML(String archivo){
        armarCabecera();
        crearMapa();
        try {
            Files.write(Paths.get(archivo), HTML);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorHTMLMapBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Establece la ubicacion inicial desde donde aparecera el mapa
     * @param longitud
     * @param latitud 
     */
    public void establecerLocacionInicial(double longitud, double latitud){
        this.latitudInicial=latitud;
        this.longitudInicial=longitud;        
    }
    /**
     * Clean HTML file 
     */
    public void limpiarHTML(){
        HTML.clear();
    }
    /**
     * Clean all markers 
     */
    public void limpiarMarcadores(){
        marcadores.clear();
    }
    /**
     * Anadir Marcador en el mapa
     * @param longitud
     * @param latitud 
     */
    public void anadirMarcador(double longitud, double latitud){
        marcadores.add(new Ubicacion(latitud,longitud));
    }
    /**
     * Anadir Marcador en el mapa con una descripcion
     * @param longitud
     * @param latitud
     * @param descripcion 
     */
    public void anadirMarcador(double longitud, double latitud, String descripcion){
        marcadores.add(new Ubicacion(latitud,longitud, descripcion));
    }
    
    @Override
    public String toString() {
        return "GeneradorHTMLMapBox{" + "accessToken=" + accessToken + ", latitudInicial=" + latitudInicial + ", longitudInicial=" + longitudInicial + ", HTML=" + HTML + '}';
    }
    
}
