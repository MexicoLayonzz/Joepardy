package frontend;

import backend.*;
import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateTopic extends JPanel {
    //private JFrame parentAlter;
    private JPanel pnlTitle, pnlCamposCategorias, pnlPreguntas;
    private JScrollPane scrollPane, scrollPanePreguntas;
    private String materiaName;
    private int numCategoriasInt;

    private final ArrayList<JTextField> camposCategorias = new ArrayList<>();
    private final ArrayList<JTextField> camposNumPreguntas = new ArrayList<>();
    private final ArrayList<JTextField> camposPreguntas = new ArrayList<>();
    private final ArrayList<JTextField> camposRespuestas = new ArrayList<>();
    private final ArrayList<JComboBox<String>> camposDificultad = new ArrayList<>();

    private final Color fondoPrincipal = new Color(25, 25, 100);
    private final Color fondoCampos = new Color(35, 35, 120);

    public CreateTopic(JFrame parent) {
        //this.parentAlter = parent;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(parent.getWidth(), parent.getHeight()));
        initPanel();
    }

    private void initPanel() {
        // Panel superior con nombre de materia y número de categorías
        pnlTitle = new JPanel(new BorderLayout());
        pnlTitle.setBackground(fondoPrincipal);
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblMaterianame = new JLabel("Nombre de la materia:");
        JTextField txtMateria = new JTextField(20);
        JLabel lblNumCategorias = new JLabel("Número de categorías:");
        JTextField txtNumCategorias = new JTextField(5);
        JButton btnCrear = new JButton("Crear");

        lblMaterianame.setFont(new Font("Arial", Font.BOLD, 14));
        lblMaterianame.setForeground(Color.WHITE);
        lblNumCategorias.setFont(new Font("Arial", Font.BOLD, 14));
        lblNumCategorias.setForeground(Color.WHITE);
        btnCrear.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel pnlFormulario = new JPanel(new GridBagLayout());
        pnlFormulario.setBackground(fondoPrincipal);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; pnlFormulario.add(lblMaterianame, gbc);
        gbc.gridx = 1; pnlFormulario.add(txtMateria, gbc);
        gbc.gridx = 0; gbc.gridy = 1; pnlFormulario.add(lblNumCategorias, gbc);
        gbc.gridx = 1; pnlFormulario.add(txtNumCategorias, gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        pnlFormulario.add(btnCrear, gbc);

        pnlTitle.add(pnlFormulario, BorderLayout.CENTER);

        // Panel dinámico para categorías y preguntas
        pnlCamposCategorias = new JPanel();
        pnlCamposCategorias.setLayout(new BoxLayout(pnlCamposCategorias, BoxLayout.Y_AXIS));
        pnlCamposCategorias.setBackground(fondoPrincipal);
        scrollPane = new JScrollPane(pnlCamposCategorias);
        scrollPane.getViewport().setBackground(fondoPrincipal);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        pnlPreguntas = new JPanel();
        pnlPreguntas.setLayout(new BoxLayout(pnlPreguntas, BoxLayout.Y_AXIS));
        pnlPreguntas.setBackground(fondoPrincipal);
        scrollPanePreguntas = new JScrollPane(pnlPreguntas);
        scrollPanePreguntas.getViewport().setBackground(fondoPrincipal);
        scrollPanePreguntas.setBorder(BorderFactory.createEmptyBorder());

        btnCrear.addActionListener(e -> {
            materiaName = txtMateria.getText().trim();
            String numCategoriasStr = txtNumCategorias.getText().trim();
            if (materiaName.isEmpty() || numCategoriasStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.");
                return;
            }

            try {
                numCategoriasInt = Integer.parseInt(numCategoriasStr);
                if (numCategoriasInt <= 0) throw new NumberFormatException();

                pnlCamposCategorias.removeAll();
                camposCategorias.clear();
                camposNumPreguntas.clear();
                pnlPreguntas.removeAll();

                for (int i = 0; i < numCategoriasInt; i++) {
                    JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    fila.setBackground(fondoPrincipal);

                    JLabel lblCategoria = new JLabel("Categoría " + (i + 1) + ":");
                    lblCategoria.setForeground(Color.WHITE);
                    JTextField txtCategoria = new JTextField(15);
                    txtCategoria.setBackground(fondoCampos);
                    txtCategoria.setForeground(Color.WHITE);

                    JLabel lblNumPreguntas = new JLabel("Preguntas:");
                    lblNumPreguntas.setForeground(Color.WHITE);
                    JTextField txtNumPreguntas = new JTextField(5);
                    txtNumPreguntas.setBackground(fondoCampos);
                    txtNumPreguntas.setForeground(Color.WHITE);

                    camposCategorias.add(txtCategoria);
                    camposNumPreguntas.add(txtNumPreguntas);

                    fila.add(lblCategoria);
                    fila.add(txtCategoria);
                    fila.add(lblNumPreguntas);
                    fila.add(txtNumPreguntas);
                    pnlCamposCategorias.add(fila);
                }

                JButton btnCrearPreguntas = new JButton("Crear preguntas");
                btnCrearPreguntas.setFont(new Font("Arial", Font.BOLD, 14));
                btnCrearPreguntas.addActionListener(ev -> {
                    pnlPreguntas.removeAll();
                    camposPreguntas.clear();
                    camposRespuestas.clear();
                    camposDificultad.clear();

                    for (int i = 0; i < camposCategorias.size(); i++) {
                        String categoria = camposCategorias.get(i).getText().trim();
                        String numPregStr = camposNumPreguntas.get(i).getText().trim();
                        if (categoria.isEmpty() || numPregStr.isEmpty()) continue;

                        int numPreguntas;
                        try {
                            numPreguntas = Integer.parseInt(numPregStr);
                            if (numPreguntas <= 0) throw new NumberFormatException();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Número inválido de preguntas para: " + categoria);
                            return;
                        }

                        JLabel lblTitulo = new JLabel("Preguntas para: " + categoria);
                        lblTitulo.setForeground(Color.ORANGE);
                        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        panelTitulo.setBackground(fondoPrincipal);
                        panelTitulo.add(lblTitulo);
                        pnlPreguntas.add(panelTitulo);

                        for (int j = 0; j < numPreguntas; j++) {
                            JPanel filaPregunta = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            filaPregunta.setBackground(fondoPrincipal);

                            JLabel lblPregunta = new JLabel("Pregunta " + (j + 1) + ":");
                            lblPregunta.setForeground(Color.WHITE);
                            JTextField txtPregunta = new JTextField(15);
                            txtPregunta.setBackground(fondoCampos);
                            txtPregunta.setForeground(Color.WHITE);

                            JLabel lblRespuesta = new JLabel("Respuesta:");
                            lblRespuesta.setForeground(Color.WHITE);
                            JTextField txtRespuesta = new JTextField(15);
                            txtRespuesta.setBackground(fondoCampos);
                            txtRespuesta.setForeground(Color.WHITE);

                            JLabel lblDificultad = new JLabel("Dificultad:");
                            lblDificultad.setForeground(Color.WHITE);
                            JComboBox<String> comboDificultad = new JComboBox<>(
                                new String[]{"Fácil", "Medio", "Difícil", "Muy Difícil", "Extremo"}
                            );
                            comboDificultad.setBackground(fondoCampos);
                            comboDificultad.setForeground(Color.WHITE);

                            filaPregunta.add(lblPregunta);
                            filaPregunta.add(txtPregunta);
                            filaPregunta.add(lblRespuesta);
                            filaPregunta.add(txtRespuesta);
                            filaPregunta.add(lblDificultad);
                            filaPregunta.add(comboDificultad);

                            camposPreguntas.add(txtPregunta);
                            camposRespuestas.add(txtRespuesta);
                            camposDificultad.add(comboDificultad);

                            pnlPreguntas.add(filaPregunta);
                        }
                    }

                    pnlPreguntas.revalidate();
                    pnlPreguntas.repaint();

                    if (scrollPanePreguntas.getParent() != this) {
                        add(scrollPanePreguntas, BorderLayout.SOUTH);
                        revalidate();
                        repaint();
                    }
                });

                JPanel filaFinal = new JPanel(new FlowLayout(FlowLayout.LEFT));
                filaFinal.setBackground(fondoPrincipal);
                filaFinal.add(btnCrearPreguntas);
                pnlCamposCategorias.add(filaFinal);
                pnlCamposCategorias.revalidate();
                pnlCamposCategorias.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número de categorías inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel inferior con botón guardar
        JPanel pnlInferior = new JPanel(new BorderLayout());
        pnlInferior.setBackground(fondoPrincipal);

        // Crear el botón "Guardar y Finalizar"
        JButton btnGuardar = new JButton("Guardar y Finalizar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(25, 25, 100));
        btnGuardar.setForeground(Color.WHITE);

        // Agregar ActionListener para guardar la materia
        btnGuardar.addActionListener(e -> {
            Materia nuevaMateria = CrearMateria();
            if (nuevaMateria != null) {
                CreacionArchivo(nuevaMateria);
                JOptionPane.showMessageDialog(this, "Materia guardada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear la materia. Verifica los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar el botón al panel inferior
        pnlInferior.add(btnGuardar, BorderLayout.CENTER);

        // Agregar el panel inferior al panel principal
        add(pnlInferior, BorderLayout.SOUTH);

        add(pnlTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public Materia CrearMateria() {
        Materia materia = new Materia(materiaName);
        int preguntaIndex = 0;

        for (int i = 0; i < camposCategorias.size(); i++) {
            String nombreCategoria = camposCategorias.get(i).getText().trim();
            String numPreguntasStr = camposNumPreguntas.get(i).getText().trim();
            if (nombreCategoria.isEmpty() || numPreguntasStr.isEmpty()) continue;

            int numPreguntas;
            try {
                numPreguntas = Integer.parseInt(numPreguntasStr);
                if (numPreguntas <= 0) continue;
            } catch (NumberFormatException ex) {
                continue;
            }

            Categoria categoria = new Categoria(nombreCategoria);
            ArrayList<Pregunta> preguntas = new ArrayList<>();

            for (int j = 0; j < numPreguntas; j++) {
                if (preguntaIndex >= camposPreguntas.size()) break;

                String texto = camposPreguntas.get(preguntaIndex).getText().trim();
                String respuesta = camposRespuestas.get(preguntaIndex).getText().trim();
                String dificultadStr = (String) camposDificultad.get(preguntaIndex).getSelectedItem();

                Dificultad dificultad;
                switch (dificultadStr) {
                    case "Fácil":
                        dificultad = Dificultad.FACIL;
                        break;
                    case "Medio":
                        dificultad = Dificultad.MEDIO;
                        break;
                    case "Difícil":
                        dificultad = Dificultad.DIFICIL;
                        break;
                    case "Muy Difícil":
                        dificultad = Dificultad.MUY_DIFICIL;
                        break;
                    case "Extremo":
                        dificultad = Dificultad.EXTREMO;
                        break;
                    default:
                        dificultad = null;
                        break;
                }

                if (!texto.isEmpty() && !respuesta.isEmpty() && dificultad != null) {
                    preguntas.add(new Pregunta(texto, respuesta, dificultad));
                }
                preguntaIndex++;
            }
            categoria.agregarPreguntas(preguntas);
            materia.getCategorias().add(categoria);
        }
        return materia;
    }

    public static void CreacionArchivo(Materia materia) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("storage/" + materia.getNombre() + ".json"))) {
            gson.toJson(materia, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
