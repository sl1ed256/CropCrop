package mts.teta.resizer;

import java.io.File;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class ConsoleAttributes {

    private Integer[] resizeParams = new Integer[2];

    @Option(
            names = "--resize",
            paramLabel = "width height",
            description = "resize the image",
            type = Integer.class
    )
    public void setResizeParams(Integer[] value) {
        this.resizeParams = value;
    }

    public Integer[] getResizeParams() {
        return resizeParams;
    }

    public void setResizeWidth(Integer reducedPreviewWidth) {
        resizeParams[0] = reducedPreviewWidth;
    }

    public void setResizeHeight(Integer reducedPreviewHeight) {
        resizeParams[1] = reducedPreviewHeight;
    }

    private int quality;

    @Option(
            names = "--quality",
            paramLabel = "value",
            description = "JPEG/PNG compression level",
            type = Integer.class
    )
    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    private Integer[] cropParams = new Integer[4];

    @Option(
            names = "--crop",
            paramLabel = "width height x y",
            description = "cut out one rectangular area of the image",
            type = Integer.class
    )
    public Integer[] getCrop() {
        return cropParams;
    }

    public void setCrop(Integer[] crop) {
        this.cropParams = crop;
    }

    public void setCropWidth(Integer cropWidth) {
        cropParams[0] = cropWidth;
    }

    public void setCropHeight(Integer cropHeight) {
        cropParams[1] = cropHeight;
    }

    public void setCropX(Integer cropX) {
        cropParams[2] = cropX;
    }

    public void setCropY(Integer cropY) {
        cropParams[4] = cropY;
    }

    private int blur;

    @Option(
            names = "--blur",
            paramLabel = "{radius}",
            description = "reduce image noise detail levels",
            type = Integer.class
    )
    public void setBlur(Integer blur) {
        this.blur = blur;
    }

    private String format;

    @Option(
            names = "--format",
            paramLabel = "\"output format\"",
            description = "the image format type",
            type = String.class
    )
    public void setFormat(String format) {
        this.format = format;
    }

    File inputFile;

    @Parameters(
            index = "0",
            hidden = true
    )

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    private File outputFile;

    @Parameters(
            index = "1",
            hidden = true
    )

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Integer[] getCropParams() {
        return cropParams;
    }

    public void setCropParams(Integer[] cropParams) {
        this.cropParams = cropParams;
    }

    public int getBlur() {
        return blur;
    }

    public void setBlur(int blur) {
        this.blur = blur;
    }

    public String getFormat() {
        return format;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}
