package cmsg.cl.noticiascmsg.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cmsg.cl.noticiascmsg.DetalleActivity;
import cmsg.cl.noticiascmsg.R;
import cmsg.cl.noticiascmsg.clases.MiDbHelper;
import cmsg.cl.noticiascmsg.clases.Noticias;
import cmsg.cl.noticiascmsg.clases.NoticiasAdapter;



public class HoyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PEND = 179;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;
    MiDbHelper miDbHelper;
    TextView lblNombre;
    ListView listViewNoticias;
    ArrayList<Noticias> arrayListNoticias = new ArrayList<>();

    public HoyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
    // * @param param1 Parameter 1.
    // * @param param2 Parameter 2.
     * @return A new instance of fragment HoyFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static HoyFragment newInstance(String param1, String param2) {
        HoyFragment fragment = new HoyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // llenarLista();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hoy, container, false);

        listViewNoticias = (ListView) view.findViewById(R.id.lstNoticiasHOY);

        arrayListNoticias.clear();
        arrayListNoticias.add(
                new Noticias("Noticia 1","Tolerably earnestly middleton extremely distrusts she boy now not. " +
                        "Add and offered prepare how cordial two promise. Greatly who affixed suppose but enquire compact prepare all put." +
                        " Added forth chief trees but rooms think may. Wicket do manner others seemed enable rather in. " +
                        "Excellent own discovery unfeeling sweetness questions the gentleman." +
                        " Chapter shyness matters mr parlors if mention thought"));
        arrayListNoticias.add(
                new Noticias("Noticia 2","Tolerably earnestly middleton extremely distrusts she boy now not. " +
                        "Add and offered prepare how cordial two promise. Greatly who affixed suppose but enquire compact prepare all put." +
                        " Added forth chief trees but rooms think may. Wicket do manner others seemed enable rather in. " +
                        "Excellent own discovery unfeeling sweetness questions the gentleman." +
                        " Chapter shyness matters mr parlors if mention thought"));
        arrayListNoticias.add(
                new Noticias("Noticia 3","Tolerably earnestly middleton extremely distrusts she boy now not. " +
                        "Add and offered prepare how cordial two promise. Greatly who affixed suppose but enquire compact prepare all put." +
                        " Added forth chief trees but rooms think may. Wicket do manner others seemed enable rather in. " +
                        "Excellent own discovery unfeeling sweetness questions the gentleman." +
                        " Chapter shyness matters mr parlors if mention thought"));
        arrayListNoticias.add(
                new Noticias("Noticia 4","Tolerably earnestly middleton extremely distrusts she boy now not. " +
                        "Add and offered prepare how cordial two promise. Greatly who affixed suppose but enquire compact prepare all put." +
                        " Added forth chief trees but rooms think may. Wicket do manner others seemed enable rather in. " +
                        "Excellent own discovery unfeeling sweetness questions the gentleman." +
                        " Chapter shyness matters mr parlors if mention thought"));
        arrayListNoticias.add(
                new Noticias("Noticia 5","Tolerably earnestly middleton extremely distrusts she boy now not. " +
                        "Add and offered prepare how cordial two promise. Greatly who affixed suppose but enquire compact prepare all put." +
                        " Added forth chief trees but rooms think may. Wicket do manner others seemed enable rather in. " +
                        "Excellent own discovery unfeeling sweetness questions the gentleman." +
                        " Chapter shyness matters mr parlors if mention thought"));

        NoticiasAdapter noticiasAdapter = new NoticiasAdapter(arrayListNoticias, getContext());
        if(listViewNoticias==null){
            Log.e("Omar", "Listview Noticias es nulo");
        }
        if(noticiasAdapter==null){
            Log.e("Omar", "Adapter Noticias es nulo");
        }

        listViewNoticias.setAdapter(noticiasAdapter);


        listViewNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(getContext(), DetalleActivity.class);
                    Noticias noticias = arrayListNoticias.get(i);
                    intent.putExtra("Rut", noticias.getTitulo());
                    intent.putExtra("fecha", noticias.getFecha());
                    startActivity(intent);

            }
        });

        return view;
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

   /* void llenarLista() {


        listViewNoticias = (ListView) rootView.findViewById(R.id.lstNoticiasHOY);

        arrayListNoticias.clear();
        arrayListNoticias.add(new Noticias("Noticia","Tolerably earnestly middleton extremely distrusts she boy now not. Add and offered prepare how cordial two promise. Greatly who affixed suppose but enquire compact prepare all put. Added forth chief trees but rooms think may. Wicket do manner others seemed enable rather in. Excellent own discovery unfeeling sweetness questions the gentleman. Chapter shyness matters mr parlors if mention thought"));
        NoticiasAdapter noticiasAdapter = new NoticiasAdapter(arrayListNoticias, getContext());
        if(listViewNoticias==null){
            Log.e("Omar", "Listview Noticias es nulo");
        }
        if(noticiasAdapter==null){
            Log.e("Omar", "Adapter Noticias es nulo");
        }

        listViewNoticias.setAdapter(noticiasAdapter);
    }*/
}
