package hadeel.com.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hadeel.com.bakingapp.Adapter.MainAdapter;
import hadeel.com.bakingapp.Model.Recipe;
import hadeel.com.bakingapp.Retrofit.GetData;
import hadeel.com.bakingapp.Retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {


    private ArrayAdapter<Recipe> adapterItems;
    private ListView lvItems;
    private RecyclerView rv;
    private List<Recipe> recipes = new ArrayList<>();
    private MainAdapter mainAdapter;

    //private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);

        rv = view.findViewById(R.id.main_rv);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mainAdapter = new MainAdapter(getContext(), (ArrayList<Recipe>) recipes);
        rv.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();



        getRecipes();
        return view;
    }

    public void getRecipes(){
        GetData getData = RetrofitInstance.getRetrofit().create(GetData.class);
        Call<List<Recipe>> call = getData.getAllRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    for(Recipe recipe : response.body()){
                        recipes.add(recipe);
                        System.out.println("Recipe added to list in getRecipes method in mainfragment.java");
                    }
                    mainAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

                System.out.println("Error in RecipeFragmant.java onFailure method."+t);
            }
        });

    }
/*    private OnListItemSelectedListener listener;

    public interface OnListItemSelectedListener {
        public void onItemSelected(Recipe recipe);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnListItemSelectedListener) {
            listener = (OnListItemSelectedListener) activity;
        } else {
            throw new ClassCastException(
                    activity.toString()
                            + " must implement ItemsListFragment.OnListItemSelectedListener");
        }
    }*/
}
