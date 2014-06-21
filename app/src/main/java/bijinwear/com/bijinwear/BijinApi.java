package bijinwear.com.bijinwear;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sugoi_wada on 2014/06/21.
 */
public class BijinApi {
    private interface Keys {
        String TYPE = "type";
        String COUNT = "count";
        String FORMAT = "format";
    }

    public interface BijinCallback {
        void onGetBijin(List<Bijin> bijin);
    }

    private static final String HOST = "http://bjin.me/api/?";

    /**
     * 美人を返します。
     * 
     * @param context
     * @param count 欲しい美人の人数
     * @return
     */
    public static void getBijin(Context context, int count, BijinCallback callback) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Keys.TYPE, "rand");
        params.put(Keys.COUNT, String.valueOf(count));
        params.put(Keys.FORMAT, "json");

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = setParams(HOST, params);

        queue.add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(BijinApi.class.getName(), response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }));
    }

    private static String setParams(String url, Map<String, String> params) {
        for (Map.Entry<String, String> e : params.entrySet()) {
            url += e.getKey() + "=" + e.getValue() + "&";
        }
        return url;
    }
}
