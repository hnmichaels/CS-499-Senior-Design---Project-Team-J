import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;

/**
 * Class which contains the main method
 */
public class MainGame
{
    /**
     * @param args Command line arguments
     * @throws IOException Thrown if there is a problem with input.
     */
    public static void main (String[] args) throws IOException, InterruptedException {

        JFrame window = Window.createWindow();  // create the window JFrame
        GridMap map = new GridMap(30, 30);
        SimInitializer initializer = new SimInitializer();
        try {
            try {
                initializer.initialize();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        SimMap posMap = SimMap.getInstance();

        Icon play = new ImageIcon("play.png");
        Icon save = new ImageIcon("save.png");
        JButton saveButton = new JButton(save);
        saveButton.setVisible(true);
        TimePanel timer = new TimePanel(posMap);
        saveButton.addActionListener(new saveAll(timer,posMap));

        window.add(map);
        window.add(timer);
        window.add(saveButton);
        window.pack();
    }
}

class saveAll implements ActionListener {
    TimePanel timer;
    SaveSession save;
    SimMap posMap;
    public saveAll(TimePanel timer, SimMap posMap) {
        this.timer = timer;
        this.save = new SaveSession(timer);
        this.posMap = posMap;
        posMap.save(save);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        save.saveTimer();
    }
}