## Сrop-crop

This program allows you to resize an image

## Используемые технологии

- Java.
- Frameworlks:
    - Work with console options [picocli.info](https://picocli.info/).
    - Work with an image [thumbnailator](https://github.com/coobird/thumbnailator).
    - Work with images and videos [marvin project](https://github.com/gabrielarchanjo/marvin-framework).
    - Tests [junit5](https://github.com/junit-team/junit5).

## Интерфейс взаимодействия

```bash
Version: name version https://gitlab.com/link/
Available formats: jpeg png
Usage: convert input-file [options ...] output-file
Options Settings:
  --resize width height       resize the image
  --quality value             PEG/PNG compression level
  --crop width height x y     сut out one or more rectangular regions of the image
  --blur {radius}             reduce image noise and reduce detail levels 
  --format "outputFormat"     the image format type
```

## Описание параметров

**--resize width height** — reduces, enlarges the picture or sets the required size for the image.

**--quality value** — sets the compression level for JPEG/PNG files. Image formats can be JPEG and PNG, quality from 1 (lowest image quality and highest compression) to 100 (best quality but least effective compression).

**--crop width height x y** — cuts out a rectangular area of the image. The rendered image must have width(**width**) and height(**height**). The reference point is given by the values **x** and **y.**

**--blur radius** — adds blur or sharpens.

**--format "outputFormat"** — converts the image to "outputFormat". The "outputFormat" parameter can be JPEG/PNG.
