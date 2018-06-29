package ar.edu.utn.fra.lab5.a1erparcial.Inicio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import ar.edu.utn.fra.lab5.a1erparcial.DialogInform;
import ar.edu.utn.fra.lab5.a1erparcial.MainActivity;
import ar.edu.utn.fra.lab5.a1erparcial.R;


public class Inicio_Login extends AppCompatActivity implements Handler.Callback {

    private ViewManager vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__login);


        Handler handler = new Handler(this);
        this.vm = new ViewManager(this, handler);
        //vm.setHandler(handler);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1) {
            Log.d("Login", msg.obj.toString());
            String type = msg.obj.toString();
            if(type.equalsIgnoreCase("Admin") || type.equalsIgnoreCase("User")) {
                if(this.vm.recordar()) {
                    SharedPreferences prefs = getSharedPreferences("config_login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", this.vm.getEmail());
                    editor.putString("password", this.vm.getPassword());
                    editor.commit();
                }
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("tipo", type);
                startActivity(intent);
            } else {
                DialogInform di = new DialogInform();
                di.setQueHago(DialogInform.ERROR);
                di.show(getFragmentManager(), "");
            }
        }
        return false;
    }
}
