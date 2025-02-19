package roborace;

import COSC3P91.graphics.ImageManager;
import COSC3P91.xml.XMLReader;
import javax.swing.JDialog;
import javax.swing.JFrame;
import roborace.client.ImageAndAnimationFactory;
import roborace.client.Player;
import roborace.common.Channel;
import roborace.common.GameDialogs;
import roborace.common.Port;
import roborace.server.GameMaster;

public class RoboRace {
      
    public static void main(String[] args) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
	JDialog.setDefaultLookAndFeelDecorated(true);
        ImageManager.getInstance().setImagePath("./Images/");
        ImageAndAnimationFactory.getInstance();
	XMLReader.setXMLPath("./");
	XMLReader.setXSDPath("./XSD/");
        
    	int nHuman = 0;
    	while (nHuman == 0 || nHuman > 4) {
            try {
                nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of players", "Please, input the number of players (1-4):",null));
            } catch (NumberFormatException e) {}
        }
        String[] names = new String[nHuman];
        Port[] ports = new Port[nHuman];
        for (int i = 0; i < nHuman; i++) {
            names[i] = GameDialogs.showInputDialog("Player #" + (i + 1), "Name of Player #" + (i + 1) + ":",null);
            Channel c = new Channel();
            ports[i] = c.asPort1();
            Player p = new Player(i,c.asPort2());
            (new Thread(p)).start();
        }
    	(new GameMaster(names,ports)).run();
    }	   
}
