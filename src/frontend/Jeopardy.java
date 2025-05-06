package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Jeopardy extends JPanel
{
    private JFrame parentAlter;

    private QuestionWindow qw;

    private JPanel pnlTitle;
    private JPanel pnlQuestions;
    private JPanel pnlTeams;

    private JLabel lblTitle;

    private List<JButton> lstButtonQuestions;
    private List<JSpinner> teamSpinners = new ArrayList<>();

    private Integer[] configValues;
    private Integer intIDQuestion;
    private Integer teamSelect;

    public Jeopardy(JFrame parent)
    {
        this.parentAlter = parent;
        setLayout(new BorderLayout());
        NumberConfig();
        PanelConfig();
        TitleConfig();
        QuestionConfig();
        TeamConfig();
    }

    //Obtener Valores (Configurar El JSON)
    public void NumberConfig() {
    	//Arreglo Del Siguiente Orden [Columnas, Filas, NumeroEquipos]
    	configValues = new Integer[]{10, 5, 5};
    	//Setear ID en 0
    	intIDQuestion = 0;
    }

    //Configuracion De Paneles
    public void PanelConfig() {
    	//Panel De Titulo
        pnlTitle = new JPanel(new BorderLayout());//panel con ubicacion especifica
        pnlTitle.setBackground(new Color(50, 50, 150));//Color Del Fondo
        pnlTitle.setPreferredSize(new Dimension(getWidth(), 60));//Altura Especifica
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));//Bordes

        //Panel De Botones De Preguntas
        pnlQuestions = new JPanel(new GridLayout(configValues[1], configValues[0], 5, 5));//panel con ubicacion especifica
        pnlQuestions.setBackground(new Color(25, 25, 100));//Color Del Fondo
        pnlQuestions.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //Panel De Muestra De Equipos
		pnlTeams = new JPanel(new GridLayout(1, configValues[2], 5, 5));//panel Con Forma De Celdas
		pnlTeams.setBackground(new Color(10, 10, 75));//Color Del Fondo
		pnlTeams.setPreferredSize(new Dimension(getWidth(), 50));//Altura Especifica
        
		//Ubicacion De Los Paneles¿
        add(pnlTitle, BorderLayout.NORTH);//Norte
        add(pnlQuestions, BorderLayout.CENTER);//Centro
        add(pnlTeams, BorderLayout.SOUTH);//Sur
    }
    
    //Funcion De Panel Titulo
    public void TitleConfig() {
        lblTitle = new JLabel("Jeopardy", SwingConstants.CENTER);//Titulo Centrado
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));//Configuración De Letra
        lblTitle.setForeground(new Color(250, 175, 25));//Color De Letra
        pnlTitle.add(lblTitle, BorderLayout.CENTER);//Añadir Panel Al Centro
    }

    //Funcion Del Panel De Preguntas
    public void QuestionConfig() {
    	intIDQuestion = 0;//Conteo Inicial
        lstButtonQuestions = new ArrayList<>();//Lista De Botones
        for(int i = 0; i < configValues[1]; i++) {
            for(int j = 0; j < configValues[0]; j++) {
            	intIDQuestion += 1;//Aumento De Conteo
                final int intCurrentIDQuestion = intIDQuestion;//Obtener Conteo Actual
                JButton btnQuestion = new JButton("$" + (i+1)*100);//Crear boton
                btnQuestion.putClientProperty("questionId", intIDQuestion);//Asociar ID al boton
                btnQuestion.setFont(new Font("Arial", Font.BOLD, 12));//Tipo De Letra
                btnQuestion.setBackground(new Color(25, 25, 100));//Color De Fondo
                btnQuestion.setForeground(new Color(250, 175, 25));//Color De Letra
                btnQuestion.addActionListener(e -> {
                    qw = new QuestionWindow(parentAlter, intCurrentIDQuestion, configValues);//Invocar JDialog De Pregunta
                    qw.setModal(true);//Bloquear Interaccion Con Panel Actual
                    qw.setVisible(true);//Ver Panel
                    teamSelect = qw.TeamSelected();//Obtener ID Del Equipo
                    JButton btnClicked = (JButton) e.getSource();//Saber Que Boton Fue Presionado
                    btnClicked.setBackground(new Color(75,75,75));//Color De Fondo
                    btnClicked.setForeground(new Color(0,0,0));//Color De Letra
                    //Añadir Valor Al JSpinner Del Equipo
                    if (teamSelect != null && teamSelect >= 0 && teamSelect < teamSpinners.size()) {
                        JSpinner spinner = teamSpinners.get(teamSelect);//Obtener JSpinner Del Equipo Seleccionado
                        int current = (int) spinner.getValue();//Obtener Valor actual del boton
                        spinner.setValue(current + 100);//sumarle 100 al Jspinner seleccionado
                    }
                });
                lstButtonQuestions.add(btnQuestion);//Añadir boton a la lista
                pnlQuestions.add(btnQuestion);//añadir boton al panel
            }
        }
    }
    
    //Funcion Del Panel De Equipos
    public void TeamConfig() {
    	//Añadir Conteo De Puntaje De Equipo
        for(int i = 0; i < configValues[2]; i++) {
        	//Panel De Agrupar En Forma De Columnas
        	JPanel pnlGroupTeam = new JPanel(new GridLayout(2, 1, 5, 5));
        	pnlGroupTeam.setBackground(new Color(10, 10, 75));//Color De Fondo
        	
            JLabel lblNameTeam = new JLabel("Team " + (i + 1));//Texto De Equipo
            lblNameTeam.setFont(new Font("Arial", Font.BOLD, 12));//Formato De Letra
            lblNameTeam.setForeground(new Color(250, 175, 25));//Color De Letra
            pnlGroupTeam.add(lblNameTeam);//Añadir Label

            //Crear JSPinner desde 0 hasta 999999 y aumentos en 100
            JSpinner spnCountPoints = new JSpinner(new SpinnerNumberModel(0, 0, 999999, 100));
            spnCountPoints.setFont(new Font("Arial", Font.BOLD, 12));//Formato De Letra
            spnCountPoints.setEditor(new JSpinner.DefaultEditor(spnCountPoints));
            //Hacer Que No Se Modifique El JSpinner
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setEditable(false);
            //Setear Color De Fondo
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setBackground(new Color(10, 10, 75));
            //Setear Color De Letra
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setForeground(new Color(250, 175, 25));
            //Configurar Horizontalmente Al Centro
            ((JSpinner.DefaultEditor)spnCountPoints.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
            teamSpinners.add(spnCountPoints);//Añadir Conteo Al JSPinner
            pnlGroupTeam.add(spnCountPoints);//Añadir JSpiner

            pnlTeams.add(pnlGroupTeam);//añadir JSpinner Al Panel DE Equipos
        }
    }

}
