package hadeel.com.bakingapp.Retrofit;

import java.util.ArrayList;
import java.util.List;


import hadeel.com.bakingapp.Model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getAllRecipes();
    //Call<ArrayList<ResultRespone>> getResult();
}
