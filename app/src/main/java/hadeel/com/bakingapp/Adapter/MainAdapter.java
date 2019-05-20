package hadeel.com.bakingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hadeel.com.bakingapp.DetailActivity;
import hadeel.com.bakingapp.Model.Ingredients;
import hadeel.com.bakingapp.Model.Recipe;
import hadeel.com.bakingapp.Model.Steps;
import hadeel.com.bakingapp.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Recipe> recipes;
    private List<Ingredients> ingredients = new ArrayList<>();
    private Context context;

    public MainAdapter(Context context, List<Recipe> recipes){
        this.context = context;
        this.recipes = recipes;
        for(int i = 0; i< recipes.size(); i++){
            System.out.println("In Recipe Adapter: "+recipes.get(i));
        }
    }
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_card_recipe, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int i) {
        //final String[] ingText = {null};
        final Recipe recipe = recipes.get(i);
        viewHolder.recipeBtn.setText(recipe.getName());
        viewHolder.recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("On clickeeed.");
                Intent intent = new Intent(context, DetailActivity.class);
                StringBuffer stringBuffer = new StringBuffer();
                for(Ingredients ing : recipe.getIngredients()){
                    stringBuffer.append(ing.getIngredient() + " - "
                            + ing.getQuantity() +" of: "+ ing.getMeasure()+"\n");
                }

                System.out.println("in MainAdapter.java: "+ stringBuffer.toString());

                List<Steps> steps = recipe.getSteps();
                intent.putExtra("Recipe", recipe);
                intent.putExtra("Ingrediants",stringBuffer.toString());
                intent.putExtra("Steps",  (Serializable) steps);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final Button recipeBtn;
        public final TextView ing;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            recipeBtn = view.findViewById(R.id.main_btn);
            ing = view.findViewById(R.id.ingrediants_tv);
        }
    }
}
