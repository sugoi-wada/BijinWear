package bijinwear.com.bijinwear;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class MainActivity extends Activity implements ImageDownloadTask.OnLoadImageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 画像取得
        String url = "http://www.bjin.me/images/71382.jpg";
        new ImageDownloadTask(this).execute(url);
        // 画像のペンディングインテント

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
    public void onLoad(Bitmap image) {
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(image);

        int notificationId = 2;
        String eventTitle = "Hello Wear";
        String eventText = "This is a first application.";
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
                        .setContentIntent(viewPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
