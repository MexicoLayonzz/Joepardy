package frontend;

import java.awt.*; 
import javax.swing.*; 

public class QuestionWindow extends JDialog 
{ 
    private JFrame parentAlter; // Referencia al JFrame padre para posicionar esta ventana.

    private JPanel pnlTitle; // Panel para contener el título.
    private JPanel pnlContent; // Panel para el contenido principal.
    private JPanel pnlTeamsButton; // Panel para los botones de los equipos.

    private JLabel lblTitle; // Etiqueta para mostrar el título de la pregunta.
    private JButton btnAccept; // Botón para aceptar una acción.

    private Integer[] configValues; // Configuración pasada como un arreglo de enteros.
    private Integer intIDQuestion; // ID de la pregunta actual.
    private Integer teamSelect; // ID del equipo seleccionado.

    // Constructor que inicializa la ventana con parámetros específicos.
    public QuestionWindow(JFrame parent, Integer intIDQuestion, Integer[] configValues) {
        super(parent, "Pregunta #" + intIDQuestion, true); // Llama al constructor de JDialog con título y modalidad.
        this.parentAlter = parent; // Guarda la referencia al JFrame padre.
        this.configValues = configValues; // Guarda la configuración.
        this.intIDQuestion = intIDQuestion; // Guarda el ID de la pregunta.
        Config(); // Configura las propiedades básicas de la ventana.
        PanelConfig(); // Configura los paneles de la ventana.
        TitleConfig(); // Configura el título de la ventana.
        ContentConfig(); // Configura el contenido principal de la ventana.
    }

    // Configura las propiedades básicas de la ventana.
    public void Config() {
        setSize(640, 360); // Establece el tamaño de la ventana.
        setResizable(true); // Permite redimensionar la ventana.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana al hacer clic en "cerrar".
        setLocationRelativeTo(parentAlter); // Centra la ventana respecto al JFrame padre.
    }

    // Configura los paneles principales de la ventana.
    public void PanelConfig() {
        pnlTitle = new JPanel(new BorderLayout()); // Crea un panel con diseño de borde para el título.
        pnlTitle.setBackground(new Color(50, 50, 150)); // Establece un color de fondo azul oscuro.
        pnlTitle.setPreferredSize(new Dimension(getWidth(), 30)); // Define la altura del panel del título.
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Agrega un margen interno.

        pnlContent = new JPanel(new BorderLayout()); // Crea un panel con diseño de borde para el contenido.
        pnlContent.setBackground(new Color(25, 25, 100)); // Establece un color de fondo más oscuro.
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Agrega un margen interno.

        pnlTeamsButton = new JPanel(); // Crea un panel para los botones de los equipos.
        pnlTeamsButton.setLayout(new GridLayout(1, configValues[2], 5, 5)); // Diseño de cuadrícula con una fila y columnas según configValues[2].
        pnlTeamsButton.setPreferredSize(new Dimension(getWidth(), 30)); // Define la altura del panel de botones.
        pnlTeamsButton.setBackground(new Color(10, 10, 75)); // Establece un color de fondo aún más oscuro.
        
        add(pnlTitle, BorderLayout.NORTH); // Agrega el panel del título en la parte superior.
        add(pnlContent, BorderLayout.CENTER); // Agrega el panel del contenido en el centro.
        add(pnlTeamsButton, BorderLayout.SOUTH); // Agrega el panel de botones en la parte inferior.
    }

    // Configura el título de la ventana.
    public void TitleConfig() {
        lblTitle = new JLabel("Pregunta" + intIDQuestion + "\n", SwingConstants.CENTER); // Crea una etiqueta centrada con el texto de la pregunta.
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18)); // Establece la fuente y el tamaño del texto.
        lblTitle.setForeground(new Color(250, 175, 25)); // Establece un color de texto amarillo-anaranjado.
        pnlTitle.add(lblTitle, BorderLayout.CENTER); // Agrega la etiqueta al centro del panel del título.
    }

    // Configura el contenido principal de la ventana.
    public void ContentConfig() {
        JTextArea txtContent = new JTextArea(); // Crea un área de texto para mostrar contenido.
        txtContent.setText("Hola Mundo"); // Establece un texto inicial.
        txtContent.setFont(new Font("Arial", Font.BOLD, 12)); // Establece la fuente y el tamaño del texto.
        txtContent.setForeground(new Color(250, 175, 25)); // Establece un color de texto amarillo-anaranjado.
        txtContent.setBackground(new Color(25, 25, 100)); // Establece un color de fondo oscuro.
        txtContent.setEditable(false); // Hace que el área de texto sea de solo lectura.
        txtContent.setHighlighter(null); // Desactiva el resaltado de texto.
        pnlContent.add(new JScrollPane(txtContent), BorderLayout.CENTER); // Agrega el área de texto con un scroll al panel de contenido.
        
        // Crea botones dinámicamente según el número de equipos (configValues[2]).
        for (int i = 0; i < configValues[2]; i++) {
            final int intCurrentIDTeam = i; // Variable final para usar en la expresión lambda.
            btnAccept = new JButton("Equipo " + (i + 1)); // Crea un botón para cada equipo.
            btnAccept.addActionListener(e -> { // Agrega un listener para manejar clics en el botón.
                teamSelect = intCurrentIDTeam; // Guarda el ID del equipo seleccionado.
                dispose(); // Cierra la ventana.
            });
            pnlTeamsButton.add(btnAccept); // Agrega el botón al panel de botones.
        }
    }
    
    // Devuelve el ID del equipo seleccionado.
    public Integer TeamSelected() {
        return teamSelect;
    }
}