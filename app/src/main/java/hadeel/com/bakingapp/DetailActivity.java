package hadeel.com.bakingapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hadeel.com.bakingapp.Model.Ingredients;
import hadeel.com.bakingapp.Model.Recipe;
import hadeel.com.bakingapp.Model.Steps;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    DetailFragment detailFragment;
    StepFragment stepFragment;

    private boolean isTwoPane = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);




        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");

        List<Steps> steps = recipe.getSteps();
        List<Ingredients> ingredients = recipe.getIngredients();

        CheckMode checkMode = new CheckMode();
        final boolean isPhone = checkMode.isPhone(this);

        if(savedInstanceState == null){
            if(!isPhone){
                System.out.println("IT IS (((NOT))) A PHONE MODE YA HADEEL :))))");
                detailFragment = DetailFragment.newInstance(recipe);
                //stepFragment = StepFragment.newInstance(recipe.getSteps());
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_view_large_for_detail, detailFragment);
                //ft.replace(R.id.step_fragment_container_fl, stepFragment);
                ft.commit();
            }else{
                System.out.println("It is a phone mode in DetailActivity.java :)");
                detailFragment = DetailFragment.newInstance(recipe);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.detail_fragment_container, detailFragment);
                ft.commit();

            }



        }
    }
}
