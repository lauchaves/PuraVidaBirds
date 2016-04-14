package cr.ac.itcr.puravidabirds;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cr.ac.itcr.puravidabirds.access_data.BirdRepo;
import cr.ac.itcr.puravidabirds.access_data.IRepositoryBird;
import cr.ac.itcr.puravidabirds.entitys.Bird;
import cr.ac.itcr.puravidabirds.entitys.User;

/**
 * Created by Laurens on 12/04/2016.
 */

public class addBirdFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "AddBirdFragment";
    public static ArrayList<Bird> test2;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public addBirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatePlaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addBirdFragment newInstance(String param1, String param2) {
        addBirdFragment fragment = new addBirdFragment();
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

        View view = inflater.inflate(R.layout.addbird_fragment, container, false);


        Button addButton = (Button) view.findViewById(R.id.btn_addbird);
        EditText name = (EditText) view.findViewById(R.id.input_nameBird);
        final EditText birdScientificName = (EditText) view.findViewById(R.id.input_birdScientificName);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText name = (EditText) getActivity().findViewById(R.id.input_nameBird);
                if (name.getText().toString().equals("")) {
                    name.setError("at least 3 characters");
                    Toast.makeText(getContext().getApplicationContext(), "The field name is empty",
                            Toast.LENGTH_SHORT).show();
                }
                EditText nameScientific = (EditText) getActivity().findViewById(R.id.input_birdScientificName);

                if (nameScientific.getText().toString().equals("")) {
                    nameScientific.setError("at least 3 characters");
                    Toast.makeText(getContext().getApplicationContext(), "The field Bird Scientific name's is empty",
                            Toast.LENGTH_SHORT).show();
                } else {

                    IRepositoryBird repository = new BirdRepo(getContext().getApplicationContext());

                    ArrayList<Bird> test = repository.GetAll();
                    String size = String.valueOf(test.size());
                    Log.d("Size testbird", size);

                    Bird bird = new Bird();
                    bird.setBirdName(name.getText().toString());
                    bird.setBirdScientificName(nameScientific.getText().toString());
                    repository.Save(bird);




                    Toast.makeText(getContext(), "Bird Successfully Added!", Toast.LENGTH_LONG).show();
                    name.setText("");
                    birdScientificName.setText("");
                }
            }
        });
        return view;
    }

        /*
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<test2.size();i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("name", "Name: " + paises[i]);
            hm.put("ScientificName","Scientific Name: " + poblacion[i]);

            aList.add(hm);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.list_view, from, to);

        setListAdapter(adapter);

        return view;
    }*/


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
