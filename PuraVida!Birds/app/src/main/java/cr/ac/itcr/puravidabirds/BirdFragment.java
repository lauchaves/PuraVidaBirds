package cr.ac.itcr.puravidabirds;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cr.ac.itcr.puravidabirds.Adapter.AdapterBird;
import cr.ac.itcr.puravidabirds.access_data.BirdRepo;
import cr.ac.itcr.puravidabirds.access_data.DBHelper;
import cr.ac.itcr.puravidabirds.entitys.Bird;

/**
 * Created by Laurens on 12/04/2016.
 */
public class BirdFragment extends Fragment {

    AdapterBird adapter;
    BirdRepo birdRepo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "BirdFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BirdFragment() {
        // Required empty public constructor
    }
    private SQLiteDatabase newDB;

    public ArrayList<Bird> LoadData() {
        ArrayList<Bird> resultList = new ArrayList<>();

        try {
            DBHelper dbHelper = new DBHelper(getContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT _id,birdName, birdScientificName FROM " +
                    "BIRDS", null);

            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String firstName = c.getString(c.getColumnIndex("birdName"));
                        String age = c.getString(c.getColumnIndex("birdScientificName"));
                        int miid = Integer.parseInt(c.getString(0));
                        Bird b = new Bird();
                        b.setId(miid);
                        b.setBirdName(firstName);
                        b.setBirdScientificName(age);
                        resultList.add(b);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
        newDB.close();
        return resultList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BirdFragment newInstance(String param1, String param2) {
        BirdFragment fragment = new BirdFragment();
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
        View view = inflater.inflate(R.layout.fragment_listbirds, container, false);

        final ListView lista = (ListView) view.findViewById(R.id.ContenlistView);

        adapter = new AdapterBird(getActivity().getApplicationContext(),LoadData());
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                TextView nameB = (TextView) view.findViewById(R.id.name);
                TextView nameC = (TextView) view.findViewById(R.id.ScientificName);
                TextView nameid = (TextView) view.findViewById(R.id._id);

                Intent i = new Intent(getContext().getApplicationContext(), editDelBird.class);
                i.putExtra("birdname", nameB.getText().toString());
                i.putExtra("birdScientific", nameC.getText().toString());
                i.putExtra("idaaa", nameid.getText().toString());
                startActivity(i);



            }
        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_test, container, false);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        final ListView lista = (ListView) getActivity().findViewById(R.id.ContenlistView);

        adapter = new AdapterBird(getActivity().getApplicationContext(),LoadData());
        lista.setAdapter(adapter);
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
