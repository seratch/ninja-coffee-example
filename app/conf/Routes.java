package conf;

import controllers.RootController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        router.GET().route("/").with(RootController.class, "index");
        router.GET().route("/assets/.*").with(AssetsController.class, "serve");
    }

}
