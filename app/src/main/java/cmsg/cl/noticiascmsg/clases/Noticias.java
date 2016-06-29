package cmsg.cl.noticiascmsg.clases;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ocantuarias on 28-06-2016.
 */
public class Noticias {

    String titulo,resumen;

    public Noticias(String titulo, String resumen) {
        this.titulo = titulo;
        this.resumen = resumen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}
