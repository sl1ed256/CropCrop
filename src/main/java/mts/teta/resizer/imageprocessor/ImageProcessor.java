package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvinplugins.MarvinPluginCollection;
import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.Resizers;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageProcessor {

    Map<String, Boolean> paramsFlags = new HashMap<>();
    private float quality;

    public void processImage(BufferedImage image, ResizerApp app) throws BadAttributesException, IOException {
        validParams(app);
        MarvinImage marvinImage = new MarvinImage(image);
        if (paramsFlags.get("resize")) {
            resizeWrapper(marvinImage, app);
        } else if (paramsFlags.get("crop")) {
            cropWrapper(marvinImage, app);
        }
        if (paramsFlags.get("blur")) {
            blurWrapper(marvinImage, app);
        }
        if (paramsFlags.get("quality")) {
            quality = app.getQuality() / 100f;
        } else {
            quality = 1f;
        }
        writeOut(marvinImage.getBufferedImageNoAlpha(), app);

    }

    private void validParams(ResizerApp app) throws BadAttributesException {
        paramsFlags.put("resize", app.getResizeHeight() != null);
        paramsFlags.put("crop", app.getCropY() != null);
        paramsFlags.put("blur", app.getBlur() != 0);
        paramsFlags.put("quality", app.getQuality() != 0);
        paramsFlags.put("format", app.getFormat() != null);

        if (paramsFlags.get("resize") & paramsFlags.get("crop")) {
            throw new BadAttributesException("Composition of crop and resize is not specified");
        }
        if (paramsFlags.get("resize") && (app.getResizeWidth() < 1 || app.getResizeHeight() < 1)) {
            throw new BadAttributesException("Please check resize params!");
        }
        if (paramsFlags.get("quality") && (app.getQuality() < 1 || app.getQuality() > 100)) {
            throw new BadAttributesException("Please check params!");
        }
        if (paramsFlags.get("crop") && (app.getCropWidth() < 1 || app.getCropHeight() < 1 || app.getCropX() < 1 || app.getCropY() < 1)) {
            throw new BadAttributesException("Please check params!");
        }
        if (paramsFlags.get("blur") && app.getBlur() < 0) {
            throw new BadAttributesException("Please check params!");
        }

    }

    private void resizeWrapper(MarvinImage image, ResizerApp app) {
        MarvinPluginCollection.scale(image.clone(), image, app.getResizeWidth(), app.getResizeHeight());
    }

    private void cropWrapper(MarvinImage image, ResizerApp app) {
        MarvinPluginCollection.crop(image.clone(), image, app.getCropX(), app.getCropY(), app.getCropWidth(), app.getCropHeight());
    }

    private void blurWrapper(MarvinImage image, ResizerApp app) {
        MarvinPluginCollection.gaussianBlur(image.clone(), image, app.getBlur());
    }

    private void writeOut(BufferedImage image, ResizerApp app) throws IOException {
        if (!paramsFlags.get("format")) {
            Thumbnails.of(image).resizer(Resizers.NULL).scale(1).outputQuality(quality).toFile(app.getOutputFile());
        } else {
            Thumbnails.of(image).resizer(Resizers.NULL).scale(1).outputQuality(quality).outputFormat(app.getFormat()).toFile(app.getOutputFile());
        }
    }

}
