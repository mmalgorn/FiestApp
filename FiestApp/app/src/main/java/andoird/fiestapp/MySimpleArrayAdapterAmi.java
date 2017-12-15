package andoird.fiestapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import andoird.fiestapp.Object.Soiree;

/**
 * Created by nicod on 12/12/2017.
 */

public class MySimpleArrayAdapterAmi extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> liste;
    private static CheckBox check;
    private final MyApplication app;

    public MySimpleArrayAdapterAmi(Context context, List<String> liste, MyApplication app) {
        super(context, R.layout.activity_ami_item,liste);
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
    public String getItem(int position) {
        return liste.get(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.activity_ami_item, parent, false);

        if(position<liste.size()) {
           // Log.d("test", "Get View : Position :"+position+" Size :"+liste.size());

            String ami = liste.get(position);

            TextView textNomAmi = (TextView) rowView.findViewById(R.id.nom_ami);

            textNomAmi.setText(ami);
        }
        return rowView;
    }

}