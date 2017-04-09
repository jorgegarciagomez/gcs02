package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Javier on 20/03/2017.
 */

public class PisoDTO
{

    private static List<Piso> pisos = new ArrayList();

    public PisoDTO() {

    }

    public void addDefaultData() {
        pisos.clear();
        Piso p1=new Piso("San Vicente", "alquilar", "piso normal", 200, "Piso Estudiantes San Vicente", 2, 2, 300, "casa1",
                "Piso céntrico cerca de la universidad, con buenas vistas y cerca del supermercado. Amueblado incluído.");

        Piso p2=new Piso("San Vicente", "compartir", "duplex", 150, "Buscando Compañero", 3, 2, 500, "piso1",
                "Piso céntrico cerca de la universidad, con buenas vistas y cerca del supermercado. Amueblado incluído.");
        Piso p3=new Piso("Calpe", "comprar", "chalet", 150, "Chalet de lujo", 5, 4, 1000, "piso1",
                "Piso céntrico cerca de la universidad, con buenas vistas y cerca del supermercado. Amueblado incluído.");
        Piso p4=new Piso("San Vicente", "alquilar", "piso normal", 250, "Piso cerca UA", 3, 2, 300,"piso1",
                "Piso céntrico cerca de la universidad, con buenas vistas y cerca del supermercado. Amueblado incluído.");
        pisos.add(p1);
        pisos.add(p2);
        pisos.add(p3);
        pisos.add(p4);
    }

    public List<Piso> devuelvePisos()
    {
        return pisos;
    }

    public List<Piso> busquedaPisos(String loc, String op, String vivienda)
    {
        ArrayList aux= new ArrayList();

        for(int i=0;i<pisos.size();i++)
        {
            Log.i("LOG", "busquedaPisos() localizacion: " + loc);
            Log.i("LOG", "busquedaPisos() pisos.get(i).getLocalizacion(): " + pisos.get(i).getLocalizacion() + " localizacion: " + loc);
            Log.i("LOG", "busquedaPisos() operacion: " + op);
            Log.i("LOG", "busquedaPisos() pisos.get(i).getOpcion(): " + pisos.get(i).getOpcion() + " opcion: " + op);
            Log.i("LOG", "busquedaPisos() vivienda: " + vivienda);
            Log.i("LOG", "busquedaPisos() pisos.get(i).getVivienda(): " + pisos.get(i).getVivienda() + " vivienda: " + vivienda);
            Log.i("LOG", "busquedaPisos() DESCRIPCION: " + pisos.get(i).getDescripcion());
            if(pisos.get(i).getOpcion().toLowerCase().equals(op.toLowerCase()) && pisos.get(i).getLocalizacion().toLowerCase().equals(loc.toLowerCase()) && pisos.get(i).getVivienda().toLowerCase().equals(vivienda.toLowerCase()))
            {
                aux.add(pisos.get(i));
            }
        }

        return aux;
    }

    public List<Piso> busquedaPisosAv(String loc, String op, int banyos, int hab, int pmin, int pmax, int tmin, int tmax)
    {
        ArrayList aux= new ArrayList();

        for(int i=0;i<pisos.size();i++)
        {
            if(pisos.get(i).getOpcion().equals(op) && pisos.get(i).getLocalizacion().equals(loc))
            {
                if(pisos.get(i).getBanyos()>=banyos && pisos.get(i).getHabitaciones()>=hab)
                {
                    int prec=pisos.get(i).getPrecio();
                    int tam=pisos.get(i).getTam();
                    if(prec<=pmax && prec>=pmin && tam<=tmax && tam>=tmin)
                    {
                        aux.add(pisos.get(i));
                    }
                }
            }
        }

        return aux;
    }

    public void addPiso(String localizacion, String opcion, String vivienda, int precio, String nombre,
                        int habitaciones, int banyos, int tam, String imagen, String descripcion) {

        Log.i("LOG", "addPiso() localizacion: " + localizacion);
        Log.i("LOG", "addPiso() opcion: " + opcion);
        Log.i("LOG", "addPiso() vivienda: " + vivienda);
        Log.i("LOG", "addPiso() precio: " + precio);
        Log.i("LOG", "addPiso() tam: " + tam);
        Piso p=new Piso(localizacion, opcion, vivienda, precio, "Piso  " + localizacion, 2, 2, tam, "casa1", descripcion);
        this.pisos.add(p);
    }
}
