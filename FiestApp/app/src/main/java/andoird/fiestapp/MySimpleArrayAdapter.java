package andoird.fiestapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by nicod on 12/12/2017.
 */

public class MySimpleArrayAdapter extends ArrayAdapter<SoireePourClient> {
    private final Context context;
    private final ListeDeSoireesPourClient liste;
    private static RadioButton radioSellect;
    private final MyApplication app;

    public MySimpleArrayAdapter(Context context, ListeDeSoireesPourClient liste, MyApplication app) {
        super(context, R.layout.activity_soiree_item,liste.TabSoireesClient);
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
    public SoireePourClient getItem(int position) {
        return liste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.activity_soiree_item, parent, false);

        if(position<liste.size()) {
            Log.d("test", "Get View : Position :"+position+" Size :"+liste.size());

            SoireePourClient soiree = liste.get(position);

            TextView textTitreSoiree = (TextView) rowView.findViewById(R.id.titre_soiree);
            TextView textLocalisation = (TextView) rowView.findViewById(R.id.localisation);
            TextView textHeure = (TextView) rowView.findViewById(R.id.heure);
            TextView textIdSoiree = (TextView) rowView.findViewById(R.id.id_soiree);

            textIdSoiree.setText(String.valueOf(soiree.id_soiree));
            textTitreSoiree.setText(soiree.titre_soiree);
            textLocalisation.setText(String.valueOf(soiree.localisation));
            textHeure.setText(String.valueOf(soiree.heure));
        }
        return rowView;
    }

}