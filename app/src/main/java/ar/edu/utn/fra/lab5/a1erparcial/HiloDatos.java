package ar.edu.utn.fra.lab5.a1erparcial;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alumno on 28/06/2018.
 */

public class HiloDatos implements Runnable {

    public static final String POST = "POST";
    public static final String GET = "GET";

    private String url;
    private String metodo;
    private Handler handler;
    private HashMap<String, String> hashMap;

    public HiloDatos(String url, String metodo, Handler handler) {
        this.url = url;
        this.metodo = metodo;
        this.handler = handler;
    }

    @Override
    public void run() {
        HttpConection conn = new HttpConection();
        conn.setMetodo(this.metodo);
        Message msg = new Message();
        if(this.metodo.equalsIgnoreCase("POST")) {
            Uri.Builder params = new Uri.Builder();
            Object[] objs = this.hashMap.keySet().toArray();
            JSONObject jObj = new JSONObject();
            for(Object key : objs) {
                Log.d("key.toString()", key.toString());
                //params.appendQueryParameter(key.toString(), this.hashMap.get(key));
                try {
                    jObj.put(key.toString(), this.hashMap.get(key).toString());
                    conn.setDatos(jObj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("params: ", params.toString());
            conn.setParams(params);
            try {
                String datos = conn.getStringData(this.url);
                JSONObject jsonObject = new JSONObject(new String(datos));
                msg.arg1 = 1;
                msg.obj = jsonObject.get("type");
                this.handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(this.metodo.equalsIgnoreCase(GET)) {
            List<Modelo> modelos = new ArrayList<>();
            try {
                byte[] datos = conn.getBytesData(this.url);
                JSONArray jsonArray = new JSONArray(new String(datos));
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Modelo m = new Modelo(jsonObject.getString("first_name"), jsonObject.getString("last_name"), jsonObject.getString("phone"));
                    modelos.add(m);
                }
                msg.arg1 = 2;
                msg.obj = modelos;
                this.handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }
}
