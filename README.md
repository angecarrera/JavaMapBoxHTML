# JavaMapBoxHTML
Allows the creation of a map in an html file using mapbox. 
Allows geocoding

# How to use Geocoding
```
 GeoCoding a = new GeoCoding("MapBox Token");
 Ubicacion u = a.consultar("Parque la carolina");
 Ubicacion u2 = a.consultar("Parque el Ejido");
 System.out.println(u);
 System.out.println(u2);
```

# How to create an html file with a map
```
 //Token, latitud, longitud where the map is going to center
 GeneradorHTMLMapBox b = new GeneradorHTMLMapBox("MapBox Token",-0.958469,-90.962978);
 b.grabarHTML("src/HTML/prueba1.html");
 ```
# How to create an html file with a map and markers
 ```
 GeneradorHTMLMapBox b = new GeneradorHTMLMapBox("MapBox Token",-0.207371,-78.496579);
 b.limpiarHTML();
 b.anadirMarcador(-78.496578,-0.207371, "Parque la carolina");          
 b.grabarHTML("src/HTML/pruebaMarker.html");
 ```
