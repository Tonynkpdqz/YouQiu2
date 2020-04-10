package com.example.tonyn.youqiu;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyn on 2017/3/14.
 */

public class ActionCollor {
    private static List<Activity> list = new ArrayList<>();

    public static void addlist(Activity activity){
        list.add(activity);
    }

    public void removelist(Activity activity){
        list.remove(activity);
    }

    public static void finashall(){
        if (list != null){
            for (Activity activity : list){
                if (!activity.isFinishing())
                    activity.finish();
            }
        }
    }
}
