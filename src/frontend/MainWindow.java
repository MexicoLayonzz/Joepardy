package frontend;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Ventana principal de la aplicación Jeopardy que maneja la interfaz de usuario
 * y la navegación entre paneles.
 */
public class MainWindow extends JFrame {
    //private Jeopardy jp;
    private MainMenu main;
    private AboutWindow aw;
    private JMenuBar mbrOptionBar;
    private JFileChooser flcJSONSelect;

    public MainWindow() {
        Config();
        PanelConfig();
        SelectFile();
        MenuBar();
    }

    /**
     * Configura los parámetros básicos de la ventana principal.
     */
    public void Config() {
        setTitle("Jeopardy");
        setSize(854, 480);
        setResizable(true);
        setMinimumSize(new Dimension(427, 260));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }

    /**
     * Configura e inicializa el panel del menú principal.
     */
    public void PanelConfig() {
        add(main = new MainMenu(this));
        main.setVisible(true);
    }

    /**
     * Configura la barra de menú con sus opciones y acciones.
     */
    public void MenuBar() {
        mbrOptionBar = new JMenuBar();
        
        // Menú Archivo
        JMenu menuData = new JMenu("Archivo");
        menuData.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JMenuItem mitemLoadData = new JMenuItem("Cargar JSON");
        mitemLoadData.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mitemLoadData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        mitemLoadData.addActionListener(e -> loadJSON());
        menuData.add(mitemLoadData);
        menuData.addSeparator();
        
        JMenuItem mitemClose = new JMenuItem("Salir");
        mitemClose.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mitemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        mitemClose.addActionListener(e -> confirmExit());
        menuData.add(mitemClose);

        // Menú Ayuda
        JMenu menuHelp = new JMenu("Ayuda");
        menuHelp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JMenuItem mitemAbout = new JMenuItem("Acerca De");
        mitemAbout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mitemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        mitemAbout.addActionListener(e -> showAbout());
        menuHelp.add(mitemAbout);

        mbrOptionBar.add(menuData);
        mbrOptionBar.add(menuHelp);
        setJMenuBar(mbrOptionBar);
    }

    /**
     * Maneja la carga de archivos JSON para el juego.
     */
    private void loadJSON() {
        flcJSONSelect.setBackground(new Color(240, 240, 240));
        flcJSONSelect.setForeground(new Color(0, 102, 153));
        
        if (flcJSONSelect.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                JOptionPane.showMessageDialog(this, 
                    "Datos cargados correctamente desde: " + flcJSONSelect.getSelectedFile().getName(),
                    "Carga exitosa", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al cargar el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Muestra un diálogo de confirmación antes de salir de la aplicación.
     */
    private void confirmExit() {
        if (JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea salir?", "Confirmar salida", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Muestra la ventana "Acerca De" de la aplicación.
     */
    private void showAbout() {
        aw = new AboutWindow(this);
        aw.setModal(true);
        aw.setVisible(true);
    }

    /**
     * Configura el selector de archivos para cargar datos JSON.
     */
    public void SelectFile() {
        flcJSONSelect = new JFileChooser();
        flcJSONSelect.setDialogTitle("Seleccionar archivo JSON");
        flcJSONSelect.setFileFilter(new FileNameExtensionFilter("Archivos JSON (*.json)", "json"));
        flcJSONSelect.setCurrentDirectory(new File(System.getProperty("user.home")));
        flcJSONSelect.setBackground(new Color(240, 240, 240));
        flcJSONSelect.setForeground(new Color(0, 102, 153));
    }
}