package xcl.com.exandroid;

import android.net.Uri;

class clase_zapatos {
    //public Uri uri;
    public String nombre;
    public String precio;
    public String ubicacion;



    public String url;


    public clase_zapatos(String name, String price, String place,String url) {
        this.precio= price;
        this.nombre= name;
        this.ubicacion=place;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


}