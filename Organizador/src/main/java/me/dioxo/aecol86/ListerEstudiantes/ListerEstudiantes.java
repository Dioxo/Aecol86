package me.dioxo.aecol86.ListerEstudiantes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dioxo.aecol86.Estudiante;
import me.dioxo.aecol86.Information.InformationActivity;
import me.dioxo.aecol86.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListerEstudiantes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListerEstudiantes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListerEstudiantes extends Fragment implements ListerView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.list_item)
    ListView listItem;

    ArrayAdapter<Estudiante> mLeadsAdapter;
    private ListerPresenter listerPresenter;

    private OnFragmentInteractionListener mListener;

    public ListerEstudiantes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListerEstudiantes.
     */
    // TODO: Rename and change types and number of parameters
    public static ListerEstudiantes newInstance(String param1, String param2) {
        ListerEstudiantes fragment = new ListerEstudiantes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lister_estudiantes, container, false);

        //Importante agregarla en Fragments
        /*
        * ButterKnife debe saber a cual view está relacionado los diferentes elementos visuales
        * Mirar la documentación de ButterKnife, parte de "Non Activity Binding"
        *
        * https://jakewharton.github.io/butterknife/
        *
        *
        * */
        ButterKnife.bind(this,view);



        listerPresenter = new ListerPresenterImpl(this);
        listerPresenter.onCreate();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listerPresenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        chercherPersonas();

    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public void chercherPersonas() {
        listerPresenter.chercherPersonas();
    }

    @Override
    public void afficherPersonas(ArrayList<Estudiante> estudiantes) {
        Log.i("Fragment", estudiantes.toString());


        mLeadsAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_2, android.R.id.text1, estudiantes) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(estudiantes.get(position).getNombre_estudiante());
                text2.setText("Encargado de Buscar: " + estudiantes.get(position).getNombre_organizador());
                return view;
            }
        };

        listItem.setAdapter(mLeadsAdapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listerInformation((Estudiante) parent.getItemAtPosition(position));
            }
        });

    }

    @Override
    public void agregarPersona() {
        Intent intent = new Intent(getContext(), InformationActivity.class);
        startActivity(intent);
    }

    @Override
    public void listerInformation(Estudiante estudiante) {
        Intent intent = new Intent(getContext(), InformationActivity.class);
        intent.putExtra("Estudiante", estudiante);
        startActivity(intent);
    }

    @Override
    public void afficherError(String message) {
        Toast.makeText(getContext(), message , Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
