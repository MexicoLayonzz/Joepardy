package frontend;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class MatterSelection extends JPanel {
    //private JFrame parentAlter;
    private MainMenu mainMenu;
    
    private JPanel pnlTitle;
    private JPanel pnlContent;
    private JPanel pnlExtra;
    private JPanel pnlButtonMenu;

    //private String[] nameOptions;

    public MatterSelection(JFrame parent, MainMenu mainMenu) {
        //this.parentAlter = parent;
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
    /**
     * Configura los botones de opciones del menú principal basados en los archivos JSON en la carpeta "storage".
     */
    public void ButtonConfig() {
        pnlButtonMenu = new JPanel(new GridLayout(0, 1)); // Crear un GridLayout dinámico
        pnlButtonMenu.setBackground(new Color(25, 25, 100));
        pnlButtonMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Obtener la lista de archivos JSON en la carpeta "storage"
        File carpeta = new File("storage");
        File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".json"));

        if (archivos != null) {
            for (File archivo : archivos) {
                // Obtener el nombre de la materia (sin la extensión .json)
                String nombreMateria = archivo.getName().replace(".json", "");

                // Crear un botón para cada archivo 
                JButton boton = new JButton(nombreMateria);
                boton.setFont(new Font("Arial", Font.BOLD, 12));
                boton.setBackground(new Color(25, 25, 100));
                boton.setForeground(new Color(250, 175, 25));

                // Agregar un ActionListener al botón
                boton.addActionListener(e -> {
                    // Aquí podrías abrir una ventana con las categorías de esta materia
                    System.out.println("Elegiste: " + nombreMateria);
                    mainMenu.showJeopardyPanel(); // Cambiar al panel de Jeopardy
                });

                // Agregar el botón al panel
                pnlButtonMenu.add(boton);
            }
        } else {
            // Si no hay archivos, mostrar un mensaje
            JLabel lblNoArchivos = new JLabel("No hay materias disponibles.");
            lblNoArchivos.setForeground(Color.WHITE);
            lblNoArchivos.setHorizontalAlignment(SwingConstants.CENTER);
            pnlButtonMenu.add(lblNoArchivos);
        }

        // Agregar el panel de botones al contenido principal
        pnlContent.add(pnlButtonMenu);
    }
}
