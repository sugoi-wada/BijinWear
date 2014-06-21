package bijinwear.com.bijinwear;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

        void onLostBijin(VolleyError error);
    }

    private static final String API_HOST = "http://bjin.me/api/?";
    private static final String PIC_HOST = "http://bjin.me/images/";

    /**
     * 美人を返します。
     * 
     * @param context
     * @param count 欲しい美人の人数
     * @return
     */
    public static void getBijin(Context context, int count, final BijinCallback callback) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Keys.TYPE, "rand");
        params.put(Keys.COUNT, String.valueOf(count));
        params.put(Keys.FORMAT, "json");

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = setParams(API_HOST, params);

        queue.add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(BijinApi.class.getName(), response);
                Type listType = new TypeToken<List<Bijin>>() {}.getType();
                List<Bijin> bijins = new Gson().fromJson(response, listType);

                for (Bijin bijin : bijins) {
                    bijin.setPic(PIC_HOST + "pic" + bijin.getId() + ".jpg");
                }

                callback.onGetBijin(bijins);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.onLostBijin(error);
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
