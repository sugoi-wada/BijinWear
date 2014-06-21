package bijinwear.com.bijinwear;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

    public interface OnLoadImageListener {
        public void onLoad(Bitmap image, String name);
    }

    private OnLoadImageListener listener;
    private Bijin bijin;

	public ImageDownloadTask(OnLoadImageListener listener, Bijin bijin)
    {
        this.listener = listener;
        this.bijin = bijin;
	}

	@Override protected Bitmap doInBackground(String... urls) {
		HttpClient dhc = new DefaultHttpClient();
		HttpResponse httpResponse;
		try 
		{
			httpResponse = dhc.execute(new HttpGet(urls[0]));
    		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
    		{  
    			HttpEntity httpEntity = httpResponse.getEntity();  
    			final InputStream in = httpEntity.getContent();  
    			Bitmap bitmap = BitmapFactory.decodeStream(in);
    			in.close();

    	    	float times = 1.0f;/*
    	    	if(bitmap.getWidth()>bitmap.getHeight()){//画像が横長の場合 縦幅を横幅に合わせる
    	    		times = 80.0f / bitmap.getWidth();
    	    	}else if(bitmap.getWidth() < bitmap.getHeight()){//画像が縦長の場合、横幅を縦幅に合わせる
    	    		times = 80.0f / bitmap.getHeight();
    	    	}*/
    	    	
				return Bitmap.createScaledBitmap(bitmap , (int)(bitmap.getWidth()*times), (int)(bitmap.getHeight()*times), true);
    		}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}  
    	return null;
	}
	
	@Override protected void onPostExecute(Bitmap result) {
		if(result == null)
		{
			//this.content.setStrPhotoUrl("");
		}
		else if (listener != null)
		{
            listener.onLoad(result, bijin.getCategory());
		}
	}
}