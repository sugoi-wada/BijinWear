package bijinwear.com.bijinwear;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartFragment extends Fragment {
    private Boolean mSensorUse;
    private int mRefreshTimeValue;
    private Button mStartBtn;
    private Button mStopBtn;
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


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
