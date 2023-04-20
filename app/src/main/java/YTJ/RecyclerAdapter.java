package YTJ;

import android.app.Activity;
import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.MainActivity;
import com.example.helloworld.R;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Item> dataSet;
    public static final String TAG = "RecyclerAdapter";

    public static class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView companyName;
        TextView companyId;
        TextView companyRegDate;
        TextView companyForm;
        LinearLayout visible;
        LinearLayout expandable;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.companyName = (TextView) itemView.findViewById(R.id.company_name);
            this.companyId = (TextView) itemView.findViewById(R.id.company_id);
            this.companyRegDate = (TextView) itemView.findViewById(R.id.company_regDate);
            this.companyForm = (TextView) itemView.findViewById(R.id.company_form);
            this.visible = (LinearLayout) itemView.findViewById(R.id.visible);
            this.expandable = (LinearLayout) itemView.findViewById(R.id.hidden);

            visible.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(expandable.getVisibility() == view.VISIBLE){
                        TransitionManager.beginDelayedTransition(expandable, new AutoTransition());
                        expandable.setVisibility(View.GONE);
                    }
                    else {
                        TransitionManager.beginDelayedTransition(expandable, new AutoTransition());
                        expandable.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public RecyclerAdapter(ArrayList<Item> data) {
        this.dataSet = data;
        Log.e("Adapter", String.valueOf(dataSet.size()));
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView companyName = holder.companyName;
        TextView companyId = holder.companyId;
        TextView companyRegDate = holder.companyRegDate;
        TextView companyForm = holder.companyForm;

        companyName.setText(dataSet.get(listPosition).getName());
        companyId.setText(dataSet.get(listPosition).getId());
        companyRegDate.setText(dataSet.get(listPosition).getRegistrationDate());
        companyForm.setText(dataSet.get(listPosition).getCompanyForm());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
