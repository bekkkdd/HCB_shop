import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.bytedeco.javacpp.opencv_core;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;

import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    static MainFrame mainFrame = new MainFrame();
    public static void main(String[] args) throws IOException, SQLException {
        Database.init();
        mainFrame.setVisible(true);
    }
}
