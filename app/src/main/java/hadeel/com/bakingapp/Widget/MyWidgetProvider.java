package hadeel.com.bakingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import hadeel.com.bakingapp.MainActivity;
import hadeel.com.bakingapp.R;

import static hadeel.com.bakingapp.Widget.WidgetInfo.*;

public class MyWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int widget : appWidgetIds){
            /*Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            views.setOnClickPendingIntent(R.id.my_widget_btn, pendingIntent);

            appWidgetManager.updateAppWidget(widget, views);*/
            String widgetTxt = "bla bla bla";
            SharedPreferences sharedPreferences = context.getSharedPreferences(WidgetInfo.WIDGET_INGREDIEMNTS, context.MODE_PRIVATE);
            widgetTxt = sharedPreferences.getString(WidgetInfo.WIDGET_ING_STRING , widgetTxt);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            views.setTextViewText(R.id.widget_tv, widgetTxt);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            views.setOnClickPendingIntent(R.id.widget_tv, pendingIntent);
            appWidgetManager.updateAppWidget(widget, views);

        }
    }
}
