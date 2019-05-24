package hadeel.com.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hadeel.com.bakingapp.Adapter.DetailAdapter;
import hadeel.com.bakingapp.Adapter.MainAdapter;
import hadeel.com.bakingapp.Model.Ingredients;
import hadeel.com.bakingapp.Model.Recipe;
import hadeel.com.bakingapp.Model.Steps;
import hadeel.com.bakingapp.Widget.WidgetInfo;

import static android.content.Context.MODE_PRIVATE;

public class DetailFragment extends Fragment {
    private Recipe recipe;
    private TextView recipeName, ingrediants;
    private RecyclerView rv;
    private DetailAdapter detailAdapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe = (Recipe) getArguments().getSerializable("Recipe");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        recipeName = view.findViewById(R.id.recipe_name_tv);
        recipeName.setText(recipe.getName());

        StringBuffer stringBuffer = new StringBuffer();
        for(Ingredients ing : recipe.getIngredients()){
            stringBuffer.append(ing.getIngredient() + " - "
                    + ing.getQuantity() +" of: "+ ing.getMeasure()+"\n");
        }
        ingrediants = view.findViewById(R.id.ingrediants_tv);
        ingrediants.setText(stringBuffer.toString());

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(WidgetInfo.WIDGET_INGREDIEMNTS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WidgetInfo.WIDGET_ING_STRING, stringBuffer.toString());
        editor.apply();



        List<Steps> stepsList = recipe.getSteps();
        rv = view.findViewById(R.id.steps_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        detailAdapter = new DetailAdapter(getContext(), stepsList);
        rv.setAdapter(detailAdapter);
        detailAdapter.notifyDataSetChanged();

        return view;
    }

    public static DetailFragment newInstance(Recipe recipe) {
        DetailFragment detailFragment= new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("Recipe", recipe);
        detailFragment.setArguments(args);
        return detailFragment;
    }

}
