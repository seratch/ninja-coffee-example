package conf;

import controllers.RootController;
import controllers.WebJarAssetsController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        router.GET().route("/").with(RootController.class, "index");
        router.GET().route("/assets/.*").with(AssetsController.class, "serve");
        router.GET().route("/webjars/.*").with(WebJarAssetsController.class, "serve");
    }

}
