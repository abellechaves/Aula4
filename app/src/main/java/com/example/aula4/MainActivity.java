package com.example.aula4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickCont(View view){
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        int SELECIONAR_CONTATO = 0;
        startActivityForResult(intent, SELECIONAR_CONTATO);

    }


    public void onClickWeb(View view) {

        Uri uri = Uri.parse("http://www.unisc.br");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);

    }

    public void onClickCall(View view) {
        Uri uri = Uri.parse("tel: 99887766");
        Intent it = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(it);

    }


    public void onClickMap1(View view){
        Uri uriGeo = Uri.parse("geo:0,0?q=Sete+de+Setembro,Curitíba");
        Intent it = new Intent(Intent.ACTION_VIEW,uriGeo);
        startActivity(it);

    }


    public void onClickMap2(View view){
    String localizacao = "geo:-25.443195.-49.280977";
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(localizacao)));

    }

    public void onClickMap3(View view){
    String partida = "-25.443195, -49.280977 ";
    String destino = "-25.442207, -49.278403 ";
    String url = "http://maps.google.com/maps?f=d&saddr="+partida+"&daddr="+destino+"&hl=pt";
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    static final int REQUST_IMAGE_CAPTURE = 1;

    public void onClickPrint(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!= null) {
            startActivityForResult(takePictureIntent, REQUST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected  void onActivityResult(int codigo, int resultado, Intent intent){
    super.onActivityResult(codigo, resultado, intent);
    if(codigo == REQUST_IMAGE_CAPTURE && resultado == RESULT_OK){
        Bundle extras = intent.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        ImageView imagemView = findViewById(R.id.imageView);
        imagemView.setImageBitmap(imageBitmap);

    }



    }

}
