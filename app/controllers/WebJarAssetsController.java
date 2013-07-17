package controllers;

import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ninja.Context;
import ninja.Renderable;
import ninja.Result;
import ninja.Results;
import ninja.utils.HttpCacheToolkit;
import ninja.utils.MimeTypes;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webjars.WebJarAssetLocator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * WebJars Controller for Ninja apps
 * <p/>
 * based on ninja.AssetsController.
 */
@Singleton
public class WebJarAssetsController {

    private static Logger logger = LoggerFactory.getLogger(WebJarAssetsController.class);

    private final MimeTypes mimeTypes;
    private final HttpCacheToolkit httpCacheToolkit;

    @Inject
    public WebJarAssetsController(HttpCacheToolkit httpCacheToolkit, MimeTypes mimeTypes) {
        this.httpCacheToolkit = httpCacheToolkit;
        this.mimeTypes = mimeTypes;
    }

    public Result serve(Context context) {
        return Results.status(200).render(new Renderable() {
            @Override
            public void render(Context context, Result result) {
                String path = context.getRequestPath();
                WebJarAssetLocator locator = new WebJarAssetLocator();
                try {
                    locator.getFullPath(path);
                } catch (IllegalArgumentException e) {
                    logger.info("Expected WebJars resource (" + path + ") is not found.");
                    context.finalizeHeaders(Results.notFound());
                    return;
                }
                try {
                    URL url = this.getClass().getClassLoader().getResource(locator.getFullPath(path));
                    URLConnection urlConnection = url.openConnection();
                    Long lastModified = urlConnection.getLastModified();
                    httpCacheToolkit.addEtag(context, result, lastModified);
                    if (result.getStatusCode() == Result.SC_304_NOT_MODIFIED) {
                        context.finalizeHeaders(result);
                    } else {
                        // found
                        result.status(200);
                        String mimeType = mimeTypes.getContentType(context, path);
                        if (!mimeType.isEmpty()) {
                            result.contentType(mimeType);
                        }
                        InputStream inputStream = urlConnection.getInputStream();
                        OutputStream outputStream = context.finalizeHeaders(result).getOutputStream();
                        try {
                            ByteStreams.copy(inputStream, outputStream);
                        } finally {
                            IOUtils.closeQuietly(inputStream);
                            IOUtils.closeQuietly(outputStream);
                        }
                    }
                } catch (FileNotFoundException e) {
                    logger.error("Streaming file error (path: " + path + ")", e);
                } catch (IOException e) {
                    logger.error("Streaming file error (path: " + path + ")", e);
                }
            }
        });
    }

}
