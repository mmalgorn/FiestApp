package andoird.fiestapp;

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

public class MySimpleArrayAdapter extends ArrayAdapter<Soiree> {
    private final Context context;
    private final List<Soiree> liste;
    private static RadioButton radioSellect;
    private final MyApplication app;

    public MySimpleArrayAdapter(Context context, List<Soiree> liste, MyApplication app) {
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
    public Soiree getItem(int position) {
        return liste.get(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.activity_soiree_item, parent, false);

        if(position<liste.size()) {
          //  Log.d("test", "Get View : Position :"+position+" Size :"+liste.size());

            Soiree soiree = liste.get(position);

            TextView textTitreSoiree = (TextView) rowView.findViewById(R.id.nom_ami);
            TextView textLocalisation = (TextView) rowView.findViewById(R.id.localisation);
            TextView textHeure = (TextView) rowView.findViewById(R.id.heure);

            textTitreSoiree.setText(soiree.getNom_soiree());
            textLocalisation.setText(String.valueOf(soiree.getPosition()[0])+String.valueOf(soiree.getPosition()[1]));
            textHeure.setText(String.valueOf(soiree.getDate()));
        }
        return rowView;
    }

}