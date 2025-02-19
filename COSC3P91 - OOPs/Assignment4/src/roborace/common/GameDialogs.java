package roborace.common;

import COSC3P91.graphics.ImageManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GameDialogs {
	
    private static ImageIcon logo = null;
    private static final Color MY_RED = new Color(230,65,40);

    public static String showInputDialog(String title, String text, Frame parent) {
        if (logo==null) {
            logo = new ImageIcon(ImageManager.getInstance().loadImage("Miscellaneous/logo.gif"));
        }
        JTextField textField = new JTextField(10);
        Object[] array = {text+"\n",textField};
        final JOptionPane pane = new JOptionPane(array,JOptionPane.QUESTION_MESSAGE,JOptionPane.DEFAULT_OPTION,logo,null);
        final JDialog dialog = new JDialog(parent,title,Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setResizable(false);
        dialog.setContentPane(pane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        pane.addPropertyChangeListener(
            new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    String prop = e.getPropertyName();
                    if (dialog.isVisible() && (e.getSource() == pane) && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                        dialog.setVisible(false);
                    }
                }
            }
        );
        recolor(pane,MY_RED);
        dialog.pack();
        if (parent == null) {
            DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
            dialog.setLocation((mode.getWidth()-dialog.getWidth())/2,(mode.getHeight()-dialog.getHeight())/2);
        } else {
            dialog.setLocationRelativeTo(parent);
        }
        dialog.setVisible(true);
        return textField.getText();
    }

    public static void showMessageDialog(String title, String text, Frame parent) {
        if (logo==null) {
            logo = new ImageIcon("../Images/logo.gif");
        }
        final JOptionPane pane = new JOptionPane(text,JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION,logo,null);
        final JDialog dialog = new JDialog(parent,title,Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setResizable(false);
        dialog.setContentPane(pane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        pane.addPropertyChangeListener(
            new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    String prop = e.getPropertyName();
                    if (dialog.isVisible() && (e.getSource() == pane) && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                        dialog.setVisible(false);
                    }
                }
            }
        );
        recolor(pane,MY_RED);
        dialog.pack();
        if (parent == null) {
            DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
            dialog.setLocation((mode.getWidth()-dialog.getWidth())/2,(mode.getHeight()-dialog.getHeight())/2);
        } else {
            dialog.setLocationRelativeTo(parent);
        }
        dialog.setVisible(true);
    }

    private static void recolor(Component component, Color color) {
        if (!(component instanceof JTextField) && !(component instanceof JButton)) {
            component.setBackground(color);
            if (component instanceof Container) {
                Container container = (Container) component;
                for(int i=0; i<container.getComponentCount(); ++i)
                    recolor(container.getComponent(i), color);
            }
        }
    }
}