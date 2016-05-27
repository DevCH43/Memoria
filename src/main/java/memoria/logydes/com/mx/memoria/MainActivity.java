package memoria.logydes.com.mx.memoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //context = getApplicationContext();
        context = getBaseContext();
    }

    public void guardarPreferencia(View v){
        SharedPreferences spc = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spc.edit();

        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        EditText etMail = (EditText) findViewById(R.id.etMail);

        String nombre =  etNombre.getText().toString();
        String mail   =  etMail.getText().toString();

        editor.putString("Nombre", nombre);
        editor.putString("Mail", mail);

        editor.commit();

        Toast finishedLoad = Toast.makeText(context, "Se ha creado la Preferencia Compartida", Toast.LENGTH_SHORT);
        finishedLoad.show();

        etNombre.setText("");
        etMail.setText("");

    }

    public void mostrarPreferencia(View v){
        SharedPreferences spc = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spc.edit();

        String nombre = spc.getString("Nombre","No existe ese nombre");
        String mail = spc.getString("Mail","No existe ese correo");

        TextView tvPC = (TextView) findViewById(R.id.tvPreferenciaCompartida);

        tvPC.setText("Nombre: "+nombre+" Email: "+mail);

    }

    public void generarArchivo(View v){
        try {

            EditText etNombre = (EditText) findViewById(R.id.etNombre);
            String nombre = etNombre.getText().toString();

            /*
            FileOutputStream fos = null;
            // fos = openFileOutput("MiArchivo.txt", Context.MODE_PRIVATE);
            fos = openFileOutput("MiArchivo.txt", Context.MODE_APPEND);
            fos.write(nombre.getBytes());
            fos.close();

            Toast.makeText(MainActivity.this, "El Archivo se ha creado: "+nombre.getBytes(), Toast.LENGTH_SHORT).show();

            etNombre.setText("");
            */
            FileOutputStream fos;
            fos = context.openFileOutput("MiArchivo.txt", context.MODE_APPEND);
            fos.write(nombre.getBytes());
            fos.close();
            Toast finishedLoad = Toast.makeText(context, "I Have Finished Loading", Toast.LENGTH_SHORT);
            finishedLoad.show();

        }catch (Exception e){

            e.printStackTrace();

            Toast.makeText(MainActivity.this, "Hubo un error en la escritura del archivo: ", Toast.LENGTH_SHORT).show();

        }
    }

}
