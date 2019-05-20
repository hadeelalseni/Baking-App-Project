package hadeel.com.bakingapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hadeel.com.bakingapp.Model.Recipe;
import hadeel.com.bakingapp.Model.Steps;
import android.content.Intent;

public class DetailActivity extends AppCompatActivity {

    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String ingrediants = b.getString("Ingrediants");
        Steps steps = (Steps) b.getSerializable("Steps");
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");
        System.out.println("in DetailActivity.java: "+ ingrediants);

        if(savedInstanceState == null){
            detailFragment = DetailFragment.newInstance(recipe);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.detail_fragment_container, detailFragment);
            ft.commit();
        }
    }
}
