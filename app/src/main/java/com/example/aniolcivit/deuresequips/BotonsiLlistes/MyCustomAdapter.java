package com.example.aniolcivit.deuresequips.BotonsiLlistes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aniolcivit.deuresequips.BBDD.JugadorDao;
import com.example.aniolcivit.deuresequips.BotonsiLlistes.Jugador;
import com.example.aniolcivit.deuresequips.R;

import java.io.ByteArrayOutputStream;
import java.util.List;


public class MyCustomAdapter extends ArrayAdapter {
    private List<JugadorDao> mJugadors;
    private Context mContext;
    private int mResource;

    public MyCustomAdapter(Context context,List<JugadorDao> data) {
        super(context, R.layout.jugadors,data);
        mContext=context;
        mJugadors=data;
        mResource=R.layout.jugadors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = layoutInflater.inflate(mResource,parent,false);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView val = (TextView) rowView.findViewById(R.id.val);
        ImageView foto = (ImageView) rowView.findViewById(R.id.photo);
        name.setText(mJugadors.get(position).getName());
        val.setText(mJugadors.get(position).getValoracio());




      /* if (mJugadors.get(position).getPersonalitzada() == true){
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();

            //mjugadors.getposition.getfoto es byte[]

            Bitmap bitmap = BitmapFactory.decodeByteArray(mJugadors.get(position).getFoto(),100,mJugadors.get(position).getFoto().length);



            foto.setImageBitmap(bitmap);

        }else{

           foto.setImageResource(R.drawable.ic_launcher);


        }*/


        return rowView;

    }
}
