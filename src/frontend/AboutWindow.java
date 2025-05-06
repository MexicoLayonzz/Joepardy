package frontend;

import java.awt.*;

import javax.swing.*;

public class AboutWindow extends JDialog {
	private JFrame parentAlter;
	
    private JPanel pnlTitle;
    private JPanel pnlContent;
    private JPanel pnlButton;

    private JLabel lblTitle;
    //private JLabel lblText;
    
    private JButton btnAccept;
    
    public AboutWindow(JFrame parent) {
    	super(parent, "Acerca de Jeopardy", true);
    	this.parentAlter = parent;
        Config();
        PanelConfig();
        TitleConfig();
        ContentConfig();
    }

    public void Config() {
        setSize(640,360);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentAlter);
    }

    public void PanelConfig() {
        pnlTitle = new JPanel(new BorderLayout());
        pnlTitle.setBackground(new Color(50, 50, 150));
        pnlTitle.setPreferredSize(new Dimension(getWidth(), 30));
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(new Color(25, 25, 100));
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        pnlButton = new JPanel(new BorderLayout());
        pnlButton.setBackground(new Color(10, 10, 75));
        pnlButton.setPreferredSize(new Dimension(getWidth(), 30));
        pnlButton.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 100));
        
        add(pnlTitle, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);
        add(pnlButton, BorderLayout.SOUTH);
    }
    
    public void TitleConfig() {
        lblTitle = new JLabel("Acerca De Jeopardy", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(250, 175, 25));
        pnlTitle.add(lblTitle, BorderLayout.CENTER);
    }

    public void ContentConfig() {
    	JTextArea txtContent = new JTextArea();
    	txtContent.setText("Version 1.0.0\n\n" +
    			"Este programa fue creado para uso educativo\n" +
                "Favor de darle un buen uso\n\n" +
                "Creadores Del Proyecto:\n" +
                "Aguilar Martinez, Fernando.\n" +
                "Gonzalez Ordaz, Ariel.");
    	txtContent.setFont(new Font("Arial", Font.BOLD, 12));
    	txtContent.setForeground(new Color(250, 175, 25));
    	txtContent.setBackground(new Color(25, 25, 100));
    	txtContent.setEditable(false);
    	txtContent.setHighlighter(null);
    	pnlContent.add(new JScrollPane(txtContent), BorderLayout.CENTER);
    	
    	btnAccept = new JButton("Aceptar");
    	btnAccept.addActionListener(e -> dispose());
    	pnlButton.add(btnAccept);
    }
}
