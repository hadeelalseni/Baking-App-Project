package hadeel.com.bakingapp;

import android.content.Context;
import android.content.res.Configuration;

public class CheckMode {
    public CheckMode(){

    }
    public boolean isPhone(Context context){

        //boolean xLarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)==4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);

        if(large){
            return false;
        }
        return true;


    }

}
