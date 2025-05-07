package frontend;

import java.awt.*;
import javax.swing.*;

/**
 * Panel del menú principal que permite navegar entre las diferentes
 * funcionalidades del juego Jeopardy.
 */
public class MainMenu extends JPanel {
    private Jeopardy jp;
    private MatterSelection ms;
    private CreateTopic ct;
    private EditTopic et;

    private JFrame parentAlter;
    
    private JPanel pnlTitle;
    private JPanel pnlContent;
    private JPanel pnlTeams;
    private JPanel pnlButtonMenu;

    private JLabel lblTitle;
    
    private JButton btnReturn;

    private String[] nameOptions;

    /**
     * Constructor que inicializa el panel del juego Jeopardy.
     */
    public MainMenu(JFrame parent) {
        this.parentAlter = parent;
        setLayout(new BorderLayout());
        PanelConfig();
        TitleConfig();
        ButtonConfig();
        setPreferredSize(new Dimension(parent.getWidth(), parent.getHeight()));
    }
    
    /**
     * Configura los paneles principales del menú.
     */
    public void PanelConfig() {
        pnlTitle = new JPanel(new BorderLayout());
        pnlTitle.setBackground(new Color(50, 50, 150));
        pnlTitle.setPreferredSize(new Dimension(getWidth(), 60));
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        pnlContent = new JPanel(new CardLayout());
        pnlContent.setBackground(new Color(25, 25, 100));

        pnlTeams = new JPanel(new BorderLayout());
        pnlTeams.setBackground(new Color(10, 10, 75));
        pnlTeams.setPreferredSize(new Dimension(getWidth(), 50));
        
        add(pnlTitle, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);
        add(pnlTeams, BorderLayout.SOUTH);
    }
    
    /**
     * Configura el título del menú principal.
     */
    public void TitleConfig() {
        JPanel pnlTitleContent = new JPanel(new BorderLayout());
        pnlTitleContent.setOpaque(false);

        lblTitle = new JLabel("Menu Principal", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(250, 175, 25));
        
        btnReturn = new JButton("← Volver");
        btnReturn.setFont(new Font("Arial", Font.BOLD, 12));
        btnReturn.setBackground(new Color(50, 50, 150));
        btnReturn.setForeground(new Color(250, 175, 25));
        btnReturn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnReturn.addActionListener(e -> returnToMenu());
        btnReturn.setVisible(false);
        
        pnlTitleContent.add(btnReturn, BorderLayout.WEST);
        pnlTitleContent.add(lblTitle, BorderLayout.CENTER);
        pnlTitle.add(pnlTitleContent, BorderLayout.CENTER);
    }

    /**
     * Configura los botones de opciones del menú principal.
     */
    public void ButtonConfig() {
        nameOptions = new String[]{
            "Agregar Materia",
            "Editar Materia",
            "Iniciar Jeopardy"
        };
        pnlButtonMenu = new JPanel(new GridLayout(nameOptions.length, 1));
        pnlButtonMenu.setBackground(new Color(25, 25, 100));
        pnlButtonMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        jp = new Jeopardy(parentAlter);
        
        pnlContent.add(pnlButtonMenu, "menu");
        pnlContent.add(jp, "jeopardy");
        
        for(int i = 0; i < nameOptions.length; i++) {
            final int optionIndex = i;
            JButton button = new JButton(nameOptions[i]);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBackground(new Color(25, 25, 100));
            button.setForeground(new Color(250, 175, 25));
            button.addActionListener(e -> {
                switch (optionIndex) {
                    case 0:
                        showCreateTopicPanel();
                        break;
                    case 1:
                        showEditTopicPanel();
                        break;
                    case 2:
                        showMatterPanel();
                        break;
                }
            });            
            pnlButtonMenu.add(button);
        }
    }

    /**
     * Muestra el panel del Añadir Tema.
     */
    public void showCreateTopicPanel() {
        if (ct != null) {
            pnlContent.remove(ct);
            ct = null;
        }
        ct = new CreateTopic(parentAlter);
        pnlContent.add(ct, "Agregar Materia");
        ((CardLayout)pnlContent.getLayout()).show(pnlContent, "Agregar Materia");
        setReturnButtonVisible(true, "Agregar Materia");
    }

    /**
     * Muestra el panel de Editar Tema.
     */
    public void showEditTopicPanel() {
        if (et != null) {
            pnlContent.remove(et);
            et = null;
        }
        et = new EditTopic(parentAlter);
        pnlContent.add(et, "Editar Materia");
        ((CardLayout)pnlContent.getLayout()).show(pnlContent, "Editar Materia");
        setReturnButtonVisible(true, "Editar Materia");
    }

    /**
     * Muestra el panel del materias disponibles.
     */
    public void showMatterPanel() {
        if (ms != null) {
            pnlContent.remove(ms);
            ms = null;
        }
        ms = new MatterSelection(parentAlter, this); // <- se pasa MainMenu
        pnlContent.add(ms, "Seleccion De Materias");
        ((CardLayout)pnlContent.getLayout()).show(pnlContent, "Seleccion De Materias");
        setReturnButtonVisible(true, "Seleccion De Materias");
    }

    /**
     * Muestra el panel del juego Jeopardy.
     */
    public void showJeopardyPanel() {
        if (jp != null) {
            pnlContent.remove(jp);
            jp = null;
        }
        jp = new Jeopardy(parentAlter);
        pnlContent.add(jp, "Jeopardy");
        ((CardLayout)pnlContent.getLayout()).show(pnlContent, "Jeopardy");
        setReturnButtonVisible(true, "Jeopardy");
    }

    public void setReturnButtonVisible(boolean visible, String panelTitle) {
        btnReturn.setVisible(visible);
        lblTitle.setText(panelTitle);
    }

    /**
     * Método para volver al menú.
     */
    private void returnToMenu() {
        String currentTitle = lblTitle.getText();
        String message;
    
        switch (currentTitle) {
            case "Jeopardy": 
                message = "¿Desea terminar el juego?";
                break;
            case "Seleccion De Materias": 
                message = "¿Desea Regresar?";
                break;
            case "Agregar Materia": 
                message = "¿Desea salir sin guardar los temas nuevos?";
                break;
            case "Editar Materia": 
                message = "¿Desea salir sin guardar los cambios?";
                break;
            default: 
                message = "¿Desea volver al menú principal?";
                break;
        }
    
        int confirm = JOptionPane.showConfirmDialog(this, message, "Confirmación", 
                         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    
        if (confirm == JOptionPane.YES_OPTION) {
            CardLayout cl = (CardLayout)(pnlContent.getLayout());
            cl.show(pnlContent, "menu");
            setReturnButtonVisible(false, "Menu Principal");
        }
    }
}