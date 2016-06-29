package cmsg.cl.noticiascmsg.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cmsg.cl.noticiascmsg.R;

/**
 * Created by ocantuarias on 28-06-2016.
 */
public class NoticiasAdapter extends BaseAdapter {

    Context context;
    ArrayList<Noticias> arrayListNoticias;


    public NoticiasAdapter(ArrayList<Noticias> arrayListHorasExtras, Context context) {
        super();
        this.arrayListNoticias = arrayListHorasExtras;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListNoticias.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListNoticias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_hoy, null);
        }
        Noticias noticias = arrayListNoticias.get(position);

        TextView lblTitulo,lblResumen;

        lblTitulo= (TextView) convertView.findViewById(R.id.lblTituloNoticia);
        lblResumen= (TextView) convertView.findViewById(R.id.lblResumen);

        lblTitulo.setText(noticias.getTitulo());
        lblResumen.setText(noticias.getResumen());





        return convertView;
    }
}
