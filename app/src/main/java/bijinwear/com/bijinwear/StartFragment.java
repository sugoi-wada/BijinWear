package bijinwear.com.bijinwear;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartFragment extends Fragment {
    private Boolean mSensorUse;
    private int mRefreshTimeValue;
    private Button mStartBtn;
    private Button mStopBtn;

    private OnFragmentInteractionListener mListener;
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance(Bundle mBundle) {
        StartFragment fragment = new StartFragment();
        Bundle args = mBundle;
        fragment.setArguments(args);
        return fragment;
    }
    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mSensorUse = getArguments().getBoolean(null);
            mRefreshTimeValue = getArguments().getInt(RefreshTimeWizardFragment.VALUE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_start,container,false);
        mStartBtn = (Button) mView.findViewById(R.id.startBtn);
        mStopBtn = (Button) mView.findViewById(R.id.stopBtn);

        mStartBtn.setOnClickListener(new ClickListner());
        mStopBtn.setOnClickListener(new ClickListner());
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class ClickListner implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.equals(mStartBtn)){
                // 開始ボタン押した時の処理
            }else{
                // 終了ボタンを押した時の処理
            }
        }
    }
}
