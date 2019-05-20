package hadeel.com.bakingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import hadeel.com.bakingapp.DetailActivity;
import hadeel.com.bakingapp.Model.Steps;
import hadeel.com.bakingapp.R;
import hadeel.com.bakingapp.StepActivity;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private List<Steps> stepsList;
    private Context context;
    public DetailAdapter(Context context, List<Steps> stepsList){
        this.context = context;
        this.stepsList = stepsList;
        for(int i = 0 ; i<stepsList.size(); i++){
            System.out.println("in DetailAdapter: "+ stepsList.get(i).getShortDescription());
        }
    }
    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_card_step, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder viewHolder, int i) {
        final Steps step = stepsList.get(i);
        viewHolder.stepBtn.setText(step.getShortDescription());
        viewHolder.stepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Step Clicked!!!! :) I am happy");
                Intent intent = new Intent(context, StepActivity.class);
                intent.putExtra("oneStep", step);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final Button stepBtn;
        public final View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            stepBtn = view.findViewById(R.id.one_step_btn);

        }
    }
}
