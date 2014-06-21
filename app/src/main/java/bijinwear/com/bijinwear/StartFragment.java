package bijinwear.com.bijinwear;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.VolleyError;

import java.util.List;

public class StartFragment extends Fragment implements BijinApi.BijinCallback, ImageDownloadTask.OnLoadImageListener {
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
            // mSensorUse = getArguments().getBoolean(null);
            mRefreshTimeValue = getArguments().getInt(RefreshTimeWizardFragment.VALUE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_start, container, false);
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

    public class ClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.equals(mStartBtn)) {
                // 開始ボタン押した時の処理
                BijinApi.getBijin(getActivity(), 1, StartFragment.this);
            } else {
                // 終了ボタンを押した時の処理
            }
        }
    }

    @Override
    public void onGetBijin(List<Bijin> bijin) {
        if (bijin.isEmpty())
            return;

        Bijin bj = bijin.get(0);
        String url = bj.getPic();
        // 画像取得
        // String url = "http://www.bjin.me/images/pic78940.jpg";
        new ImageDownloadTask(this, bj).execute(url);
        // 画像のペンディングインテント

    }

    @Override
    public void onLostBijin(VolleyError error) {

    }

    @Override
    public void onLoad(Bitmap image, String name) {
        int notificationId = 2;
        String eventTitle = name;
        String eventText = "Bijin Wear";
        String extraName = "extraName";
        String extraString = "Tap AndroidWear";
        Intent viewIntent = new Intent(getActivity(), MainActivity.class);
        viewIntent.putExtra(extraName, extraString);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(getActivity(), 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setLargeIcon(image)
                        .setContentTitle(eventTitle)
                        .setContentText(eventText)
                        .setContentIntent(viewPendingIntent)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(image));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        Notification notification =
                new WearableNotifications.Builder(notificationBuilder)
                        .setHintHideIcon(true)
                        .setMinPriority() // show only on clock
                        .build();
        notificationManager.notify(notificationId, notification);
    }
}
