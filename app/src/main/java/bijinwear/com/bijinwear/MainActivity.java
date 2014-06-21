package bijinwear.com.bijinwear;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.VolleyError;

import java.util.List;


public class MainActivity extends Activity implements ImageDownloadTask.OnLoadImageListener,
BijinApi.BijinCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BijinApi.getBijin(this, 1, this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoad(Bitmap image, String name) {
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(image);

        int notificationId = 2;
        String eventTitle = name;
        String eventText = "Bijin Wear";
        String extraName = "extraName";
        String extraString = "Tap AndroidWear";
        Intent viewIntent = new Intent(this, MainActivity.class);
        viewIntent.putExtra(extraName, extraString);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setLargeIcon(image)
                        .setContentTitle(eventTitle)
                        .setContentText(eventText)
                        .setContentIntent(viewPendingIntent)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(image));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        Notification notification =
                new WearableNotifications.Builder(notificationBuilder)
                        .setHintHideIcon(true)
                        .setMinPriority() // show only on clock
                        .build();
        notificationManager.notify(notificationId, notification);
    }

    @Override
    public void onGetBijin(List<Bijin> bijin) {
        if (bijin.isEmpty())
            return;

        Bijin bj = bijin.get(0);
        String url = bj.getPic();
        // 画像取得
        //String url = "http://www.bjin.me/images/pic78940.jpg";
        new ImageDownloadTask(this, bj).execute(url);
        // 画像のペンディングインテント

    }

    @Override
    public void onLostBijin(VolleyError error) {

    }
}
