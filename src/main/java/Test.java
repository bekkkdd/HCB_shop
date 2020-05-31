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

import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
//
//public class Test implements Runnable{
//    public static void main(String[] args) {
////        Canvas canvas1 = new Canvas();
////        canvas1.setSize(500,500);
//
//        CanvasFrame canvas = new CanvasFrame("Web Cam");
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//        canvas.setCanvasSize(200, 150);
//        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
//        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
//        opencv_core.IplImage img;
//        int i = 0;
//        try {
//            grabber.start();
//            JButton button = new JButton("asd");
//            button.setSize(150, 50);
//            button.setLocation(200, 400);
//            canvas.add(button);
//            while (true) {
//                Frame frame = grabber.grab();
//                img = converter.convert(frame);
//                //the grabbed frame will be flipped, re-flip to make it right
//                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
//                //save
//                cvSaveImage("face.jpg", img);
//
//                String imgFile = "face.jpg";
//                Mat src = Imgcodecs.imread(imgFile);
//
//                String xmlFile = "lbpcascade_frontalface.xml";
//                CascadeClassifier cc = new CascadeClassifier(xmlFile);
//
//                MatOfRect faceDetection = new MatOfRect();
//                cc.detectMultiScale(src, faceDetection);
//
////                System.out.println(String.format("Detected faces: %d", faceDetection.toArray().length));
//
//                int x = 0;
//                int y = 0;
//                int h = 0;
//                int w = 0;
//                for (Rect rect : faceDetection.toArray()) {
//                    Imgproc.rectangle(src, new org.opencv.core.Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 3);
//                    x = rect.x;
//                    y = rect.y;
//                    w = rect.width;
//                    h = rect.height;
//                }
//
//                Imgcodecs.imwrite("face_out.png", src);
//
//                try {
//                    File pathToFile = new File("face_out.png");
//                    Image image = ImageIO.read(pathToFile);
//                    if (x == 0 && y == 0 && w == 0 && h == 0)
//                        canvas.showImage(image);
//                    else
//                        canvas.showImage(((BufferedImage) image).getSubimage(x, y, w, h));
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//                Thread.sleep(300);
////                canvas.showImage(converter.convert(img));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void run() {
//
//    }
//}


public class Test {
    final int INTERVAL = 100;///you may use interval
    static CanvasFrame canvas = new CanvasFrame("Web Cam");

    public Test() {
//        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public static void run() {
        int frameCount = 0;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        opencv_core.IplImage img;
        try {
            grabber.start();
            long startMil = System.currentTimeMillis();
            String result = null;
            while (true) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);



                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                cvSaveImage("face.jpg", img);

                // brightness enhacement of face.jpg to better find face
                String imgFile = "face.jpg";
                Mat src = Imgcodecs.imread(imgFile, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                double alpha = 1;
                double beta = 60;
                try {
                    Mat destination = new Mat(src.rows(), src.cols(), src.type());
                    src.convertTo(destination, -1, alpha, beta);
                    Imgcodecs.imwrite("face.jpg", destination);
//                    EditImage.resize("face.jpg", "face.jpg", 294,294);
                } catch (Exception e) {
                    System.out.println("error: " + e.getMessage());
                }
                src = Imgcodecs.imread(imgFile, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                String xmlFile = "lbpcascade_frontalface.xml";
                CascadeClassifier cc = new CascadeClassifier(xmlFile);

                MatOfRect faceDetection = new MatOfRect();
                cc.detectMultiScale(src, faceDetection);


                if (faceDetection.toArray().length == 0) {
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
                    File pathToFile = new File("face.jpg");
                    Image image = ImageIO.read(pathToFile);
                    if (x == 0 && y == 0 && w == 0 && h == 0)
                        canvas.showImage(image);
                    else {
                        canvas.showImage(((BufferedImage) image).getSubimage(x, y, w, h));
                        BufferedImage im = ((BufferedImage) image).getSubimage(x, y, w, h);
                        File outputfile = new File("face.jpg");
                        ImageIO.write(im, "jpg", outputfile);
                        EditImage.resize("face.jpg","face.jpg",294,294);
//                        grabber.stop();
//                        canvas.dispose();
//                        break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if(System.currentTimeMillis() - startMil > 6000){
                    grabber.stop();
                    canvas.dispose();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Test gs = new Test();
//        Thread th = new Thread(gs);
//        th.start();
    }
}