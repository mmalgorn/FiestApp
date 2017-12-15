package andoird.fiestapp;

/**
 * Created by mathieu on 15/12/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import andoird.fiestapp.Object.Notification;
import andoird.fiestapp.Object.Soiree;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.List;

import andoird.fiestapp.Object.Soiree;

/**
 * Created by nicod on 12/12/2017.
 */

public class MySimpleArrayAdapterNotif extends ArrayAdapter<Notification> {
    private final Context context;
    private final ArrayList<Notification> liste;
    private static RadioButton radioSellect;
    private final MyApplication app;

    public MySimpleArrayAdapterNotif(Context context, ArrayList<Notification> liste, MyApplication app) {
        super(context, R.layout.activity_soiree_item,liste);
        this.context = context;
        this.liste = liste;
        this.app = app;
    }

    @Override
    public int getCount(){
        if (liste.size()<=0)
            return 1;
        return liste.size();
    }
    @Override
    public Notification getItem(int position) {
        return liste.get(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.notification, parent, false);

        if(position<liste.size()) {
            //  Log.d("test", "Get View : Position :"+position+" Size :"+liste.size());

            Notification notif = liste.get(position);

            TextView textTitle = (TextView) rowView.findViewById(R.id.title);
            TextView textContent = (TextView) rowView.findViewById(R.id.content);
            textTitle.setText(notif.getTitle());
            textContent.setText(notif.getContent());
        }
        return rowView;
    }

}