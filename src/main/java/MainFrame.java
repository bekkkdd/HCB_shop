import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

public class MainFrame extends JFrame {

    CheckContainer checkContainer = new CheckContainer();

    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TAKE BOXES");
        setSize(500, 500);
        setLayout(null);

        checkContainer.setLocation(0,0);
        add(checkContainer);

        checkContainer.setVisible(true);
    }

//    JButton enterToStore;



//    public MainFrame() {
//        final Timer timer = new Timer(7000, new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                enterToStore.setEnabled(true);
//                enterToStore.setText("Enter to store");
//                if(true){
//                    setVisible(false);
//                    Main.shopFrame.setVisible(true);
//                }
//            }
//        });
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("BITLAB Application");
//        setSize(500, 500);
//        setLayout(null);
//
//        enterToStore = new JButton("Enter to store");
//        enterToStore.setSize(200, 30);
//        enterToStore.setLocation(50, 350);
//        enterToStore.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Test test = new Test();
//                Thread thread = new Thread(test);
//                thread.start();
//                enterToStore.setEnabled(false);
//                enterToStore.setText("Wait... 7 sec");
//                timer.start();
//            }
//        });
//        add(enterToStore);
//    }
}
