package com.group25.unibar.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group25.unibar.Db.BarsDb;
import com.group25.unibar.R;
import com.group25.unibar.adapter.BarInfoAdapter;
import com.group25.unibar.models.BarInfo;
import com.group25.unibar.viewmodels.BarItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link BarInfoListFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link BarInfoListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class BarInfoListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    List<BarInfo> barInfoList;
    private BarItemViewModel viewModel;

//    private OnFragmentInteractionListener mListener;

    public BarInfoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BarInfoListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarInfoListFragment newInstance() {
        BarInfoListFragment fragment = new BarInfoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        barInfoList = new ArrayList<>();
        barInfoList = BarsDb.getInstance().get_barList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_info_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        if(getParentFragment().toString().contains("ProfileInfoFragment")){
            Log.d("BarInfoListFragment", "Finding 4 random bars for you!");
            Random r = new Random();
            int min = 0;
            int max = 29;
            int randomNumber = r.nextInt(max-min) + min;
            barInfoList = barInfoList.subList(randomNumber, randomNumber + 4);
        }
        BarInfoAdapter barInfoAdapter = new BarInfoAdapter(getContext(), barInfoList);
        Log.d("TEST AF PARENTFRAGMENT", getParentFragment().toString());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(barInfoAdapter);
        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
