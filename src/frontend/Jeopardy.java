package frontend; 

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * Panel principal del juego Jeopardy que muestra las categorías, preguntas
 * y el seguimiento de puntajes por equipo.
 */
public class Jeopardy extends JPanel {
    private JFrame parentAlter;
    
    private QuestionWindow qw;
    
    private JPanel pnlTitle;
    private JPanel pnlQuestions;
    private JPanel pnlTeams;

    //private JLabel lblTitle;
    
    private List<JButton> lstButtonQuestions;
    private List<JSpinner> teamSpinners;

    private Integer[] configValues;
    private Integer intIDQuestion;
    private Integer teamSelect;
    
    /**
     * Constructor que inicializa el panel del juego Jeopardy.
     */
    public Jeopardy(JFrame parent) {
        this.parentAlter = parent;
        setLayout(new BorderLayout());
        NumberConfig();
        PanelConfig();
        TitleConfig();
        QuestionConfig();
        TeamConfig();
        setPreferredSize(new Dimension(parent.getWidth(), parent.getHeight()));
    }

    /**
     * Configura los valores iniciales del juego (columnas, filas, equipos).
     */
    public void NumberConfig() {
        configValues = new Integer[]{10, 5, 5}; // [Columnas, Filas, Equipos]
        intIDQuestion = 0;
    }

    /**
     * Configura los paneles principales del juego.
     */
    public void PanelConfig() {
        // Panel de título
        pnlTitle = new JPanel(new GridLayout(1, configValues[0], 5, 5));
        pnlTitle.setBackground(new Color(25, 25, 100));
        pnlTitle.setPreferredSize(new Dimension(getWidth(), 60));
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Panel de preguntas
        pnlQuestions = new JPanel(new GridLayout(configValues[1], configValues[0], 5, 5));
        pnlQuestions.setBackground(new Color(25, 25, 100));
        pnlQuestions.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Panel de equipos
        pnlTeams = new JPanel(new GridLayout(1, configValues[2], 5, 5));
        pnlTeams.setBackground(new Color(10, 10, 75));
        pnlTeams.setPreferredSize(new Dimension(getWidth(), 50));
        
        add(pnlTitle, BorderLayout.NORTH);
        add(pnlQuestions, BorderLayout.CENTER);
        add(pnlTeams, BorderLayout.SOUTH);
    }
    
    /**
     * Configura el título y botón de regreso del juego.
     */
    public void TitleConfig() {
        for(int i = 0; i < configValues[0]; i++) {
            JLabel lblTitle = new JLabel("Categoria " + (i + 1), SwingConstants.CENTER);
            lblTitle.setFont(new Font("Arial", Font.BOLD, 12));
            lblTitle.setForeground(new Color(250, 175, 25));
            pnlTitle.add(lblTitle);
        }
    }

    /**
     * Configura los botones de preguntas del juego.
     */
    public void QuestionConfig() {
        intIDQuestion = 0;
        lstButtonQuestions = new ArrayList<>();
        for(int i = 0; i < configValues[1]; i++) {
            for(int j = 0; j < configValues[0]; j++) {
                intIDQuestion += 1;
                final int intCurrentIDQuestion = intIDQuestion;
                final int valuePoints = (i + 1) * 100;
                JButton btnQuestion = new JButton("$" + (i + 1) * 100);
                btnQuestion.putClientProperty("questionId", intIDQuestion);
                btnQuestion.setFont(new Font("Arial", Font.BOLD, 12));
                btnQuestion.setBackground(new Color(25, 25, 100));
                btnQuestion.setForeground(new Color(250, 175, 25));
                btnQuestion.addActionListener(e -> {
                    qw = new QuestionWindow(parentAlter, intCurrentIDQuestion, configValues);
                    qw.setModal(true);
                    qw.setVisible(true);
                    teamSelect = qw.TeamSelected();
                    JButton btnClicked = (JButton) e.getSource();
                    btnClicked.setBackground(new Color(75,75,75));
                    btnClicked.setForeground(new Color(0,0,0));
                    if (teamSelect != null && teamSelect >= 0 && teamSelect < teamSpinners.size()) {
                        JSpinner spinner = teamSpinners.get(teamSelect);
                        int current = (int) spinner.getValue();
                        spinner.setValue(current + valuePoints);
                    }
                });
                lstButtonQuestions.add(btnQuestion);
                pnlQuestions.add(btnQuestion);
            }
        }
    }
    
    /**
     * Configura los controles de puntaje para cada equipo.
     */
    public void TeamConfig() {
        teamSpinners = new ArrayList<>();
        for(int i = 0; i < configValues[2]; i++) {
            JPanel pnlGroupTeam = new JPanel(new GridLayout(2, 1, 5, 5));
            pnlGroupTeam.setBackground(new Color(10, 10, 75));
            
            JLabel lblNameTeam = new JLabel("Team " + (i + 1));
            lblNameTeam.setFont(new Font("Arial", Font.BOLD, 12));
            lblNameTeam.setForeground(new Color(250, 175, 25));
            pnlGroupTeam.add(lblNameTeam);

            JSpinner spnCountPoints = new JSpinner(new SpinnerNumberModel(0, 0, 999999, 100));
            spnCountPoints.setFont(new Font("Arial", Font.BOLD, 12));
            spnCountPoints.setEditor(new JSpinner.DefaultEditor(spnCountPoints));
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setEditable(false);
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setBackground(new Color(10, 10, 75));
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setForeground(new Color(250, 175, 25));
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
            teamSpinners.add(spnCountPoints);
            pnlGroupTeam.add(spnCountPoints);

            pnlTeams.add(pnlGroupTeam);
        }
    }
}