package com.example.aniolcivit.deuresequips;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aniolcivit.deuresequips.BBDD.JugadorDao;
import com.example.aniolcivit.deuresequips.BBDD.JugadorHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;


public class Informacio extends OrmLiteBaseActivity<JugadorHelper> {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText infonom;
    private EditText infoval;
    private ImageView infofoto;
    private TextView infoequip;
    private Button infook;
    private Button infocancel;
    private Informacio infoActivity;
    private int id;
    private Button infoelimina;
    ImageView infoimatge;
    private byte[] infobyte;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacio);
        infonom = (EditText) findViewById(R.id.nameinfo);
        infoval = (EditText) findViewById(R.id.valinfo);
        infofoto = (ImageView) findViewById(R.id.photoinfo);
        infoequip= (TextView) findViewById(R.id.equip);
        infook = (Button) findViewById(R.id.buttonok);
        infocancel = (Button) findViewById(R.id.buttoncancel);
        infoelimina = (Button) findViewById(R.id.elimina);


        infoActivity = this;

        infoimatge = (ImageView) findViewById(R.id.image);




        /*FALTA:
        -assignar funció al botó ok
        -revisar el de cancel
        -modificar la info del main activity des de informació
         */
        infonom.setText(getIntent().getExtras().getString("Nom"));
        infoval.setText(getIntent().getExtras().getString("Valoració"));
        //infofoto.setImageBitmap((android.graphics.Bitmap) getIntent().getExtras().get("Foto"));
        infoequip.setText(getIntent().getExtras().getString("Equip"));
        id = getIntent().getExtras().getInt("Id");

        infocancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                startActivity(intent);*/
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                infoActivity.finish();
                //comentari

            }
        });

        infook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infonom.getText().toString().equals("") || infoval.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Falten dades", Toast.LENGTH_SHORT).show();
                }else{



                    try {
                        Dao<JugadorDao,Integer> dao = getHelper().getDao();
                        Log.d("Id",id+"");

                        JugadorDao jugadorDao = dao.queryForId(id);
                        jugadorDao.setName(infonom.getText().toString());
                        jugadorDao.setEquip(infoequip.getText().toString());
                        jugadorDao.setValoracio(infoval.getText().toString());
                        jugadorDao.setFoto(infobyte);


                        Log.d("Player:  ", jugadorDao.getName().toString() + jugadorDao.getValoracio().toString() + jugadorDao.getEquip().toString());

                        dao.update(jugadorDao);

                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    infoActivity.finish();



                }


            }
        });


        infoelimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Dao<JugadorDao,Integer> dao = getHelper().getDao();


                    JugadorDao jugadorDao = dao.queryForId(id);

                    dao.delete(jugadorDao);

                }catch (SQLException e) {
                    e.printStackTrace();
                }






                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                infoActivity.finish();

            }
        });







        infofoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }



            }

        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            infofoto.setImageBitmap(imageBitmap);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte [] byteArray = stream.toByteArray();
            infobyte = byteArray;

            try {
                Dao<JugadorDao,Integer> dao = getHelper().getDao();
                Log.d("Id",id+"");

                JugadorDao jugadorDao = dao.queryForId(id);
                jugadorDao.setFoto(infobyte);
                jugadorDao.setPersonalitzada(true);



                dao.update(jugadorDao);

            }catch (SQLException e) {
                e.printStackTrace();
            }



            //bytearray ES LLISTA PIXELS FOTO

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
