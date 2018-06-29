package ar.edu.utn.fra.lab5.a1erparcial;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by alumno on 28/06/2018.
 */

public class DialogInform extends DialogFragment {

    public static final String ERROR = "ERROR";
    public static final String PERMISO = "PERMISO";
    private String queHago;
    private String mensaje;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        if(this.queHago.equalsIgnoreCase(ERROR)) {
            build.setTitle("Error: ");
            build.setMessage("Debe ingresar un mail y un password valido");
        } else {
            build.setTitle("Permiso: ");
            build.setMessage(this.mensaje);
        }
        build.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return build.create();
    }

    public String getQueHago() {
        return queHago;
    }

    public void setQueHago(String queHago) {
        this.queHago = queHago;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
