import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

public class EditImage {

    public static void main(String[] args) {
        CanvasFrame canvas = new CanvasFrame("Web Cam");
        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        opencv_core.IplImage img;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        try {
            grabber.start();
            for (int i = 0; i < 60; i++) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                cvSaveImage("faces\\Bekdaulet-" + i + ".jpg", img);

                // brightness enhacement of face.jpg to better find face
                String imgFile = "faces\\Bekdaulet-" + i + ".jpg";
                Mat src = Imgcodecs.imread(imgFile, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                double alpha = 1;
                double beta = 60;
                try {
                    Mat destination = new Mat(src.rows(), src.cols(), src.type());
                    src.convertTo(destination, -1, alpha, beta);
                } catch (Exception e) {
                    System.out.println("error: " + e.getMessage());
                }

                src = Imgcodecs.imread(imgFile, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                String xmlFile = "lbpcascade_frontalface.xml";
                CascadeClassifier cc = new CascadeClassifier(xmlFile);

                MatOfRect faceDetection = new MatOfRect();
                cc.detectMultiScale(src, faceDetection);


                if (faceDetection.toArray().length == 0) {
                    i--;
                    continue;
                }

                int x = 0;
                int y = 0;
                int h = 0;
                int w = 0;
                for (Rect rect : faceDetection.toArray()) {
                    Imgproc.rectangle(src, new org.opencv.core.Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 3);
                    x = rect.x;
                    y = rect.y;
                    w = rect.width;
                    h = rect.height;
                }
//
                try {

                    File pathToFile = new File("faces\\Bekdaulet-" + i + ".jpg");
                    Image image = ImageIO.read(pathToFile);
                    if (x == 0 && y == 0 && w == 0 && h == 0)
                        canvas.showImage(image);
                    else {
                        canvas.showImage(((BufferedImage) image).getSubimage(x, y, w, h));
                        BufferedImage im = ((BufferedImage) image).getSubimage(x, y, w, h);
                        File outputfile = new File("faces\\Bekdaulet-" + i + ".jpg");
                        ImageIO.write(im, "jpg", outputfile);
                        resize("faces\\Bekdaulet-" + i + ".jpg","faces\\Bekdaulet-" + i + ".jpg",294,294);
                        Thread.sleep(500);
                        System.out.println(i);
//                        grabber.stop();
//                        canvas.dispose();
//                        break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
}
