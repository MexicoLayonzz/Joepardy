package frontend;

import java.awt.*;
import javax.swing.*;

public class EditTopic extends JPanel {
    private JFrame parentAlter;
    
    private JPanel pnlTitle;
    private JPanel pnlContent;
    private JPanel pnlExtra;

    public EditTopic(JFrame parent) {
        this.parentAlter = parent;
        setLayout(new BorderLayout());
        PanelConfig();
        setPreferredSize(new Dimension(parent.getWidth(), parent.getHeight()));
    }

    public void PanelConfig() {
        // Panel de título
        pnlTitle = new JPanel(new BorderLayout());
        pnlTitle.setBackground(new Color(25, 25, 100));
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Panel de preguntas
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(new Color(25, 25, 100));
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Panel de equipos
        pnlExtra = new JPanel(new BorderLayout());
        pnlExtra.setBackground(new Color(10, 10, 75));
        
        add(pnlTitle, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);
        add(pnlExtra, BorderLayout.SOUTH);
    }
}
