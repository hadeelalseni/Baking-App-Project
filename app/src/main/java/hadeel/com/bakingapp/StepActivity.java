package hadeel.com.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import hadeel.com.bakingapp.Model.Steps;

public class StepActivity extends AppCompatActivity {

    private DetailFragment detailFragment;

    private StepFragment stepFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Steps step = (Steps) b.getSerializable("oneStep");
        System.out.println("in StepActivity: "+step.getShortDescription());


        CheckMode checkMode = new CheckMode();
        final boolean isPhone = checkMode.isPhone(this);




        if(savedInstanceState == null){
            stepFragment = StepFragment.newInstance(step);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.step_fragment_container, stepFragment);
            ft.commit();
        }
    }
}
