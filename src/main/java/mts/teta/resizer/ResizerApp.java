package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.ImageProcessor;
import picocli.CommandLine;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
        name = "convert",
        version = "resizer 1.0",
        headerHeading = "Version: resizer 1.0 https://github.com/sl1ed256/CropCrop\n",
        sortOptions = false,
        separator = " ",
        synopsisHeading = "Availble formats: jpeg png\n" +
                "Usage: convert input-file [options ...] output-file\n",
        customSynopsis = {"Option Settings:"}
)

public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {
    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp())
                .setUsageHelpLongOptionsMaxWidth(40)
                .execute(args);
    }

    @Override
    public Integer call() throws Exception {
        ImageProcessor imageProcessor = new ImageProcessor();
        try {
            imageProcessor.processImage(ImageIO.read(inputFile), this);
        } catch (IIOException e) {
            throw new IIOException("Can't read input file!");
        }
        return 0;
    }

}
