package frontend;

import java.awt.*;
import javax.swing.*;

public class QuestionWindow extends JDialog {
	private JFrame parentAlter;

    private JPanel pnlTitle;
    private JPanel pnlContent;
    private JPanel pnlTeamsButton;

    private JLabel lblTitle;
    private JLabel lblText;
    
    private JButton btnAccept;

	private Integer[] configValues;
    private Integer intIDQuestion;
    private Integer teamSelect;
	
	public QuestionWindow(JFrame parent, Integer intIDQuestion, Integer[] configValues) {
		super(parent, "Pregunta #" + intIDQuestion, true);
    	this.parentAlter = parent;
    	this.configValues = configValues;
    	this.intIDQuestion = intIDQuestion;
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

        pnlTeamsButton = new JPanel();
        pnlTeamsButton.setLayout(new GridLayout(1, configValues[2], 5, 5));
        pnlTeamsButton.setPreferredSize(new Dimension(getWidth(), 30));
        pnlTeamsButton.setBackground(new Color(10, 10, 75));
		
        add(pnlTitle, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);
        add(pnlTeamsButton, BorderLayout.SOUTH);
    }

    public void TitleConfig() {
        lblTitle = new JLabel("Pregunta" + intIDQuestion + "\n", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(250, 175, 25));
        pnlTitle.add(lblTitle, BorderLayout.CENTER);
    }

    public void ContentConfig() {
    	JTextArea txtContent = new JTextArea();
    	txtContent.setText("Hola Mundo");
    	txtContent.setFont(new Font("Arial", Font.BOLD, 12));
    	txtContent.setForeground(new Color(250, 175, 25));
    	txtContent.setBackground(new Color(25, 25, 100));
    	txtContent.setEditable(false);
    	txtContent.setHighlighter(null);
    	pnlContent.add(new JScrollPane(txtContent), BorderLayout.CENTER);
    	
    	for(int i = 0;i < configValues[2];i += 1) {
            final int intCurrentIDTeam = i;
    		btnAccept = new JButton("Equipo " + (i + 1));
        	btnAccept.addActionListener(e -> {
        		teamSelect = intCurrentIDTeam;
        		dispose();
        	});
        	pnlTeamsButton.add(btnAccept);
    	}
    }
    
    public Integer TeamSelected() {
        return teamSelect;
    }
}
