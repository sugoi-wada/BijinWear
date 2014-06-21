package bijinwear.com.bijinwear;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;


/**
 * mNextWizardBtn...次のウィザード画面へ進むボタン
 * mRefreshTimePicker...更新間隔を決めるNumberPicker
 *
 */
public class RefreshTimeWizardFragment extends Fragment{
    private NumberPicker mRefreshTimePicker;
    private Button mNextWizardBtn;
    private static final int MAX_VALUE = 10;
    private static final int MIN_VALUE = 1;
    public static final String VALUE = "value";

    // TODO: Rename and change types and number of parameters
    public static RefreshTimeWizardFragment newInstance() {
        RefreshTimeWizardFragment fragment = new RefreshTimeWizardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public RefreshTimeWizardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_wizard_refresh_time, container, false);
        mNextWizardBtn = (Button) mView.findViewById(R.id.nextBtn);
        mNextWizardBtn.setOnClickListener(new ButtonClickListener());
        mRefreshTimePicker = (NumberPicker) mView.findViewById(R.id.refreshTimePicker);
        // Pickerの最大値,最小値のセット
        mRefreshTimePicker.setMaxValue(MAX_VALUE);
        mRefreshTimePicker.setMinValue(MIN_VALUE);
        // 初期値
        mRefreshTimePicker.setValue(MIN_VALUE);

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

    public  class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // ここでNumberPickerの現在の値を取得し、値を添えて次のウィザード画面に遷移
            Bundle extra = new Bundle();
            extra.putInt(VALUE, mRefreshTimePicker.getValue());
            Fragment mFragment = StartFragment.newInstance(extra);
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mFragment).commit();
        }
    }
}
