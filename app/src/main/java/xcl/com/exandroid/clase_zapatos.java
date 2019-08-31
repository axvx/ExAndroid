package xcl.com.exandroid;

import android.net.Uri;

class clase_zapatos {
    //public Uri uri;
    public String nombre;


    public String precio;
    public String ubicacion;


    public clase_zapatos(String name, String price, String place) {
        this.precio= price;
        this.nombre= name;
        this.ubicacion=place;
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