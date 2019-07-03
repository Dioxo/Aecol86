package me.dioxo.estudiante.CheckList;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.estudiante.Constantes;
import me.dioxo.estudiante.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.checkbox1)
    CheckBox checkbox1;
    @BindView(R.id.checkbox2)
    CheckBox checkbox2;
    @BindView(R.id.checkbox3)
    CheckBox checkbox3;
    @BindView(R.id.checkbox4)
    CheckBox checkbox4;
    @BindView(R.id.checkbox5)
    CheckBox checkbox5;
    @BindView(R.id.checkbox6)
    CheckBox checkbox6;
    @BindView(R.id.checkbox7)
    CheckBox checkbox7;
    @BindView(R.id.checkbox8)
    CheckBox checkbox8;
    @BindView(R.id.checkbox9)
    CheckBox checkbox9;
    @BindView(R.id.checkbox10)
    CheckBox checkbox10;
    @BindView(R.id.checkbox11)
    CheckBox checkbox11;
    @BindView(R.id.checkbox12)
    CheckBox checkbox12;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    public CheckListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckListFragment newInstance(String param1, String param2) {
        CheckListFragment fragment = new CheckListFragment();
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
        View view = inflater.inflate(R.layout.fragment_check_list, container, false);
        ButterKnife.bind(this, view);

        loadPreferences();

        return view;
    }

    private void loadPreferences() {

        if(getActivity().getSharedPreferences(Constantes.CHECKLIST, 0) != null){
             settings= getActivity().getSharedPreferences(Constantes.CHECKLIST, 0);
              editor = settings.edit();
        }

        if (settings != null) {
            checkbox1.setChecked(settings.getBoolean("checkbox1", false));
            checkbox2.setChecked(settings.getBoolean("checkbox2", false));
            checkbox3.setChecked(settings.getBoolean("checkbox3", false));
            checkbox4.setChecked(settings.getBoolean("checkbox4", false));
            checkbox5.setChecked(settings.getBoolean("checkbox5", false));
            checkbox6.setChecked(settings.getBoolean("checkbox6", false));
            checkbox7.setChecked(settings.getBoolean("checkbox7", false));
            checkbox8.setChecked(settings.getBoolean("checkbox8", false));
            checkbox9.setChecked(settings.getBoolean("checkbox9", false));
            checkbox10.setChecked(settings.getBoolean("checkbox10", false));
            checkbox11.setChecked(settings.getBoolean("checkbox11", false));
            checkbox12.setChecked(settings.getBoolean("checkbox12", false));

        }else{
            Log.i("CheckList", "TODO A FALSO");
            checkbox1.setChecked(false);
            checkbox2.setChecked(false);
            checkbox3.setChecked(false);
            checkbox4.setChecked(false);
            checkbox5.setChecked(false);
            checkbox6.setChecked(false);
            checkbox7.setChecked(false);
            checkbox8.setChecked(false);
            checkbox9.setChecked(false);
            checkbox10.setChecked(false);
            checkbox11.setChecked(false);
            checkbox12.setChecked(false);
        }



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

    @OnClick(R.id.checkbox1)
    public void onCheckbox1Clicked() {

        editor.putBoolean("checkbox1", checkbox1.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox2)
    public void onCheckbox2Clicked() {
        editor.putBoolean("checkbox2", checkbox2.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox3)
    public void onCheckbox3Clicked() {
        editor.putBoolean("checkbox3", checkbox3.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox4)
    public void onCheckbox4Clicked() {
        editor.putBoolean("checkbox4", checkbox4.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox5)
    public void onCheckbox5Clicked() {
        editor.putBoolean("checkbox5", checkbox5.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox6)
    public void onCheckbox6Clicked() {
        editor.putBoolean("checkbox6", checkbox6.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox7)
    public void onCheckbox7Clicked() {
        editor.putBoolean("checkbox7", checkbox7.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox8)
    public void onCheckbox8Clicked() {
        editor.putBoolean("checkbox8", checkbox8.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox9)
    public void onCheckbox19Clicked() {
        editor.putBoolean("checkbox9", checkbox9.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox10)
    public void onCheckbox10Clicked() {
        editor.putBoolean("checkbox10", checkbox10.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox11)
    public void onCheckbox11Clicked() {
        editor.putBoolean("checkbox11", checkbox11.isChecked());
        // Commit the edits!
        editor.commit();
    }

    @OnClick(R.id.checkbox12)
    public void onCheckbox12Clicked() {
        editor.putBoolean("checkbox12", checkbox12.isChecked());
        // Commit the edits!
        editor.commit();
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
