package cc.isotopestudio.photometa;
/*
 * Created by david on 10/17/2017.
 * Copyright ISOTOPE Studio
 */

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileMetadataDirectory;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputField;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoMeta {

    private static final SimpleDateFormat dt = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
    private static final String pngPath = "C:\\Users\\david\\Desktop\\Photo\\Photo\\New folder\\PNG\\";
    private static final String jpgOutPath = "C:\\Users\\david\\Desktop\\Photo\\Photo\\New folder\\JPG Output\\";
    private static final String jpgOut2Path = "C:\\Users\\david\\Desktop\\Photo\\Photo\\New folder\\JPG Output 2\\";

    public static void main(String[] args) {

        for (File pngPhoto : new File(pngPath).listFiles()) {
            try {
                Metadata meta = ImageMetadataReader.readMetadata(pngPhoto);
                FileMetadataDirectory fileMeta = meta.getDirectoriesOfType(FileMetadataDirectory.class).iterator().next();
                Date date = fileMeta.getDate(FileMetadataDirectory.TAG_FILE_MODIFIED_DATE);

                //read image file
                BufferedImage bufferedImage = ImageIO.read(pngPhoto);

                // create a blank, RGB, same width and height, and a white background
                BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                        bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

                // write to jpeg file
                File outputJPG = new File(jpgOutPath
                        + pngPhoto.getName().toLowerCase().replace("png", "jpg"));
                ImageIO.write(newBufferedImage, "jpg", outputJPG);

                File output2JPG = new File(jpgOut2Path + outputJPG.getName());

                try (FileOutputStream fos = new FileOutputStream(output2JPG);
                     OutputStream os = new BufferedOutputStream(fos)) {

                    TiffOutputSet outputSet = null;

                    // note that metadata might be null if no metadata is found.
                    final ImageMetadata metadata = Imaging.getMetadata(outputJPG);
                    final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
                    if (null != jpegMetadata) {
                        // note that exif might be null if no Exif metadata is found.
                        final TiffImageMetadata exif = jpegMetadata.getExif();

                        if (null != exif) {
                            // TiffImageMetadata class is immutable (read-only).
                            // TiffOutputSet class represents the Exif data to write.
                            //
                            // Usually, we want to update existing Exif metadata by
                            // changing
                            // the values of a few fields, or adding a field.
                            // In these cases, it is easiest to use getOutputSet() to
                            // start with a "copy" of the fields read from the image.
                            outputSet = exif.getOutputSet();
                        }
                    }

                    // if file does not contain any exif metadata, we create an empty
                    // set of exif metadata. Otherwise, we keep all of the other
                    // existing tags.
                    if (null == outputSet) {
                        outputSet = new TiffOutputSet();
                    }

                    {
                        // Example of how to add a field/tag to the output set.
                        //
                        // Note that you should first remove the field/tag if it already
                        // exists in this directory, or you may end up with duplicate
                        // tags. See above.
                        //
                        // Certain fields/tags are expected in certain Exif directories;
                        // Others can occur in more than one directory (and often have a
                        // different meaning in different directories).
                        //
                        // TagInfo constants often contain a description of what
                        // directories are associated with a given tag.
                        //
                        final TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();
                        // make sure to remove old value if present (this method will
                        // not fail if the tag does not exist).
                        for (TiffOutputField field : exifDirectory.getFields()) {
                            System.out.println(field.tagInfo);
                        }
                        exifDirectory.removeField(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
                        exifDirectory.removeField(ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED);
                        exifDirectory.removeField(ExifTagConstants.EXIF_TAG_SUB_SEC_TIME_DIGITIZED);
                        exifDirectory.removeField(ExifTagConstants.EXIF_TAG_SUB_SEC_TIME_ORIGINAL);

                        String dateString = dt.format(date);

                        exifDirectory.add(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL, dateString);
                        exifDirectory.add(ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED, dateString);
                        exifDirectory.add(ExifTagConstants.EXIF_TAG_SUB_SEC_TIME_DIGITIZED, "00");
                        exifDirectory.add(ExifTagConstants.EXIF_TAG_SUB_SEC_TIME_ORIGINAL, "00");
                    }
                    new ExifRewriter().updateExifMetadataLossless(outputJPG, os,
                            outputSet);
                }
            } catch (ImageProcessingException | IOException | ImageReadException | ImageWriteException e) {
                e.printStackTrace();
            }

            System.out.println("[" + dt.format(System.currentTimeMillis()) + "]" + pngPhoto.getName() + ": Done");
        }
    }

}
