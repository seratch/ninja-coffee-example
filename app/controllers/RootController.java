package controllers;

import com.google.inject.Singleton;
import ninja.Context;
import ninja.Result;
import ninja.Results;

@Singleton
public class RootController {

    public Result index(Context context) {
        return Results.html();
    }

}
