package frontend;

import java.awt.*;
import javax.swing.*;

public class MatterSelection extends JPanel {
    private JFrame parentAlter;
    private MainMenu mainMenu;
    
    private JPanel pnlTitle;
    private JPanel pnlContent;
    private JPanel pnlExtra;
    private JPanel pnlButtonMenu;

    private String[] nameOptions;

    public MatterSelection(JFrame parent, MainMenu mainMenu) {
        this.parentAlter = parent;
        this.mainMenu = mainMenu;
        setLayout(new BorderLayout());
        PanelConfig();
        ButtonConfig();
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
    
    /**
     * Configura los botones de opciones del menú principal.
     */
    public void ButtonConfig() {
        nameOptions = new String[]{
            "Matemáticas",
            "Historia", 
            "Ciencias",
            "Literatura",
            "Geografía",
            "Arte"
        };
        pnlButtonMenu = new JPanel(new GridLayout(nameOptions.length, 1));
        pnlButtonMenu.setBackground(new Color(25, 25, 100));
        pnlButtonMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        for(int i = 0; i < nameOptions.length; i++) {
            final int optionIndex = i;
            JButton button = new JButton(nameOptions[i]);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBackground(new Color(25, 25, 100));
            button.setForeground(new Color(250, 175, 25));
            button.addActionListener(e -> {
                switch (optionIndex) {
                    case 0:
                        System.out.print(optionIndex);
                        break;
                    case 1:
                        System.out.print(optionIndex);
                        break;
                    case 2:
                        System.out.print(optionIndex);
                        break;
                }
                mainMenu.showJeopardyPanel();
            });            
            pnlButtonMenu.add(button);
        }
        pnlContent.add(pnlButtonMenu);
    }
}
