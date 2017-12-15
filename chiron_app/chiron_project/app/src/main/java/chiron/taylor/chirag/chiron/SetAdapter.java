package chiron.taylor.chirag.chiron;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import chiron.taylor.chirag.chiron.models.SetModel;

/**
 * Created by 100502392 on 12/13/2017.
 */

public class SetAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    SetModel tempValues = null;
    int i = 0;

    public SetAdapter(Activity a, ArrayList d, Resources r) {
        activity = a;
        data = d;
        res = r;

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (data.size()<=0) {
            return 1;
        }
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    public static class ViewHolder {
        public TextView nameText;
        public TextView infoText;
        public Button setReps;
        public TextView setLoad;
        public Button finalize;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if(convertView==null) {

            vi = inflater.inflate(R.layout.workoutitem, null);

            holder = new ViewHolder();
            holder.nameText = (TextView) vi.findViewById(R.id.setName);
            holder.infoText = (TextView) vi.findViewById(R.id.setInfo);
            holder.setReps  = (Button) vi.findViewById(R.id.setReps);
            holder.setLoad  = (TextView) vi.findViewById(R.id.setLoad);
            holder.finalize = (Button) vi.findViewById(R.id.finalizeSet);

            vi.setTag( holder );

        }
        else {

            holder = (ViewHolder) vi.getTag();

        }

        if(data.size()<=0) {
            holder.nameText.setText("No sets found.");
        }
        else {

            tempValues = null;
            tempValues = (SetModel) data.get( position );

            holder.nameText.setText( tempValues.getName() );
            holder.infoText.setText( Integer.toString(tempValues.getReps())+"x"+Integer.toString(tempValues.getLoad()) );

            holder.setReps.setText( Integer.toString(0) );
            holder.setReps.setTag(position);

            holder.setLoad.setText( Integer.toString(tempValues.getLoad()) );
            holder.setLoad.setTag(position);

            holder.finalize.setTag(position);

            vi.setOnClickListener(new OnItemClickListener( position ));

        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("SetAdapter", "Row button clicked");
    }

    private class OnItemClickListener implements View.OnClickListener {

        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

            WorkoutActivity sct = (WorkoutActivity)activity;

            sct.onItemClick(mPosition);

        }

    }

}

