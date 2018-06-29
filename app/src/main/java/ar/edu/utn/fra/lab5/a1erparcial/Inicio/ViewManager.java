package ar.edu.utn.fra.lab5.a1erparcial.Inicio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.HashMap;

import ar.edu.utn.fra.lab5.a1erparcial.HiloDatos;
import ar.edu.utn.fra.lab5.a1erparcial.MainActivity;
import ar.edu.utn.fra.lab5.a1erparcial.R;

/**
 * Created by lkdml on 8/5/2017.
 */

public class ViewManager implements View.OnClickListener {

    private Activity activity;
    private TextView mail;
    private TextView clave;
    private CheckBox remember;
    private Button ingresar;
    private Handler handler;


    public ViewManager(Activity activity, Handler handler) {
        this.activity = activity;
        this.mail = (TextView) this.activity.findViewById(R.id.IL_Mail);
        this.clave = (TextView) this.activity.findViewById(R.id.IL_Clave);
        this.remember = (CheckBox) this.activity.findViewById(R.id.IL_Remember);
        this.ingresar = (Button) this.activity.findViewById(R.id.IL_LogIn);
        this.ingresar.setOnClickListener(this);
        this.handler = handler;
        SharedPreferences preferences = activity.getSharedPreferences("config_login", Context.MODE_PRIVATE);
        String clave = preferences.getString("password", "");
        String mail = preferences.getString("email", "");
        if(!clave.isEmpty() && !mail.isEmpty()) {
            this.ingreso(mail, clave);
        }
        /*if(preferences != null) {
            //this.mail.setText(preferences.getString("email", ""));
            //this.clave.setText(preferences.getString("password", ""));
            //this.ingreso(preferences.getString("email", ""), preferences.getString("password", ""));
        }*/
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onClick(View v) {
        if  (this.ingresar.getId() == v.getId()){
            //TODO: Completar con funcionalidad de recordarme

            this.ingreso(this.mail.getText().toString(), this.clave.getText().toString());

        }

    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Boolean recordar() {
        return this.remember.isChecked();
    }

    public String getEmail() {
        return this.mail.getText().toString();
    }

    public String getPassword() {
        return this.clave.getText().toString();
    }

    public void ingreso(String user, String pass) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", user);
        hashMap.put("password", pass);
        HiloDatos hd = new HiloDatos("http://192.168.2.202:3000/login", "POST", this.handler);
        hd.setHashMap(hashMap);
        Thread t = new Thread(hd);
        t.start();
    }
}
