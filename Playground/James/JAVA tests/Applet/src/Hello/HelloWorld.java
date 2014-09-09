package Hello;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public class HelloWorld extends JApplet
{

    private JFrame contentFrame;
    private Container contentPane;

    /**
     * Enter a string n a field click on an execute button applet converts
     * string to uppcase print result in a field
     */
    @Override
    public void init()
    {
        contentFrame = new JFrame();
        contentPane = contentFrame.getContentPane();
        createGUI();
    }

    private void createGUI()
    {
        contentFrame.setVisible(true);
        JLabel label = createLabel("Hello World");
        JComboBox dropDownMenu = createDropDownSelector(new String[]
        {
            "Item 1", "Item 2", "Item 3", "Item 4"
        });

        contentPane.add(dropDownMenu, BorderLayout.PAGE_START);
        contentPane.add(label, BorderLayout.PAGE_START);
        contentPane.doLayout();
        contentPane.setVisible(true);
        
        try
        {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {

                }
            });
        } catch (InterruptedException | InvocationTargetException e)
        {
            System.err.println("createGUI didn't successfully complete");
        }

    }

    private void createLayoutContainer()
    {
        JPanel panel = new JPanel(new BorderLayout());
        contentPane.setLayout(new FlowLayout());
    }

    private JLabel createLabel(String title)
    {
        JLabel label = new JLabel(title);
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private JComboBox createDropDownSelector(String[] entries)
    {
        JComboBox localComboBox = new JComboBox();
        localComboBox.setModel(new DefaultComboBoxModel(entries));
        return localComboBox;
    }

    // TODO overwrite start(), stop() and destroy() methods
}
