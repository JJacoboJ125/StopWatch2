package co.edu.unipiloto.stopwatch2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {
    //determinar si el cronometro esta en ejecucion
    private boolean running;

    //contador con los segundo cuando se ejecuta
    private int segundos=0;

    private String[] guardados = new String[5];
    private int guardarNo = 0;

    String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Button button=findViewById(R.id.boton_iniciar);
        // button.setOnClickListener(new View.onClickListener());
        runTimer();
        for(int i = 0; i<5;i++){
            guardados[i]="";
        }

    }

    public void onClickStart(View view){

        running=true;
    }
    public void onClickPause(View view){
        running=false;

    }

    public void onClickReset(View view){
        TextView timeView2 = (TextView) findViewById(R.id.Vueltas);
        running=false;
        segundos=0;
        resultado = "";
        timeView2.setText("");
        for(int i = 0; i<5;i++){
            guardados[i]="";
        }
        guardarNo = 0;
    }

    public void onClickSave(View view){
        TextView timeView2 = (TextView) findViewById(R.id.Vueltas);
        int hora = segundos/3600;
        int minutes = (segundos %3600)/60;
        int secs = segundos %60;
        String tiempo2 = String.format(Locale.getDefault(),"%d:%02d:%02d",hora,minutes,secs);
        resultado = "";
        guardados[guardarNo] = tiempo2;
        for(int i = 0; i<5;i++){
            resultado += guardados[i]+"\n";
        }
        guardarNo++;
        if (guardarNo==5)
            guardarNo = 0;
        timeView2.setText(resultado);
    }


    private void runTimer(){
        //relacionar un objeto textview con el elemento grafico correspondiente
        TextView timeView = (TextView) findViewById(R.id.tiempoVivo);

        //declarar un handler para manejar un hilo
        Handler handler = new Handler();
        //invocar el metodo post e instanciar un objeto runneable
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hora = segundos/3600;
                int minutes = (segundos %3600)/60;
                int secs = segundos %60;

                String tiempo = String.format(Locale.getDefault(),"%d:%02d:%02d",hora,minutes,secs);

                timeView.setText(tiempo);

                if (running)
                    segundos++;
                handler.postDelayed(this,1000);

            }
        });
    }

}