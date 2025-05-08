package frontend;

import java.awt.*;
import javax.swing.*;

import com.google.gson.JsonObject;

public class QuestionWindow extends JDialog {
	private JFrame parentAlter;

    private JPanel pnlTitle;
    private JPanel pnlContent;
    private JPanel pnlTeamsButton;

    private JLabel lblTitle;
    //private JLabel lblText;
    
    private JButton btnAccept;

	private Integer[] configValues;
    private Integer teamSelect;
	
	public QuestionWindow(JFrame parent, JsonObject pregunta, Integer[] configValues) {
		super(parent, "Pregunta #", true);
    	this.parentAlter = parent;
    	this.configValues = configValues;
    	
        Config();
        PanelConfig();
        TitleConfig();
        ContentConfig(pregunta);
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
        lblTitle = new JLabel("Pregunta"  + "\n", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(250, 175, 25));
        pnlTitle.add(lblTitle, BorderLayout.CENTER);
    }

    public void ContentConfig(JsonObject pregunta) {
        pnlContent.removeAll();  // Limpia contenido anterior si lo hubiera
        pnlContent.setLayout(new BorderLayout());
    
        JTextArea txtContent = new JTextArea();
    
        String textoPregunta = pregunta.has("pregunta") 
            ? pregunta.get("pregunta").getAsString() 
            : "Pregunta no disponible.";
    
        txtContent.setText(textoPregunta);
        txtContent.setFont(new Font("Arial", Font.BOLD, 20));
        txtContent.setForeground(new Color(250, 175, 25));
        txtContent.setBackground(new Color(25, 25, 100));
        txtContent.setEditable(false);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setHighlighter(null);
    
        pnlContent.add(new JScrollPane(txtContent), BorderLayout.CENTER);
    
        // === Panel para el botón y la respuesta ===
        JPanel pnlRespuesta = new JPanel(new BorderLayout());
        pnlRespuesta.setBackground(new Color(25, 25, 100));
    
        JLabel lblRespuesta = new JLabel("", SwingConstants.CENTER);
        lblRespuesta.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRespuesta.setForeground(Color.GREEN);
    
        JButton btnMostrarRespuesta = new JButton("Mostrar respuesta");
        btnMostrarRespuesta.setFont(new Font("Arial", Font.BOLD, 15));
        btnMostrarRespuesta.addActionListener(e -> {
            String textoRespuesta = pregunta.has("respuesta")
                ? pregunta.get("respuesta").getAsString()
                : "Respuesta no disponible.";
            lblRespuesta.setText("<html><center><b>Respuesta:</b> " + textoRespuesta + "</center></html>");
            lblRespuesta.setFont(new Font("Arial", Font.BOLD, 20));
        });
    
        pnlRespuesta.add(btnMostrarRespuesta, BorderLayout.NORTH);
        pnlRespuesta.add(lblRespuesta, BorderLayout.CENTER);
        pnlContent.add(pnlRespuesta, BorderLayout.SOUTH);
    
        // === Botones para los equipos ===
        pnlTeamsButton.removeAll();
        for (int i = 0; i < configValues[2]; i++) {
            final int intCurrentIDTeam = i;
            btnAccept = new JButton("Equipo " + (i + 1));
            btnAccept.addActionListener(e -> {
                teamSelect = intCurrentIDTeam;
                dispose();
            });
            pnlTeamsButton.add(btnAccept);
        }
    
        pnlContent.revalidate();
        pnlContent.repaint();
    }
    
    
    
    public Integer TeamSelected() {
        return teamSelect;
    }
}
