import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class FrmDistribucionFrecuencias extends JFrame {

    // variables globales
    private String[] respuestas = new String[1000];
    private int totalRespuestas = -1;
    private JComboBox cmbRespuesta;
    private JList lstRespuestas;

    // metodo constructor
    public FrmDistribucionFrecuencias() {
        setTitle("Distribución de frecuencias");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        JLabel lblPregunta = new JLabel("Pregunta:");
        lblPregunta.setBounds(10, 10, 100, 20);
        add(lblPregunta);

        JTextArea txtPregunta = new JTextArea(
                "¿Cómo considera la calidad de la señal de internet que entra al barrio?");
        txtPregunta.setBounds(120, 10, 300, 50);
        txtPregunta.setEditable(false);
        txtPregunta.setLineWrap(true);
        add(txtPregunta);

        JLabel lblRespuesta = new JLabel("Respuesta:");
        lblRespuesta.setBounds(10, 65, 100, 20);
        add(lblRespuesta);

        cmbRespuesta = new JComboBox();
        cmbRespuesta.setBounds(120, 65, 100, 25);
        add(cmbRespuesta);

        String[] respuestas = { "Excelente", "Buena", "Regular", "Mala" };
        cmbRespuesta.setModel(new DefaultComboBoxModel(respuestas));

        JButton btnAgregar = new JButton(">>");
        btnAgregar.setBounds(10, 95, 100, 25);
        add(btnAgregar);

        JButton btnQuitar = new JButton("<<");
        btnQuitar.setBounds(10, 125, 100, 25);
        add(btnQuitar);

        lstRespuestas = new JList();
        JScrollPane spRespuestas = new JScrollPane(lstRespuestas);
        spRespuestas.setBounds(110, 95, 100, 100);
        add(spRespuestas);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(10, 200, 100, 25);
        add(btnCalcular);

        JTable tblFrecuencias = new JTable();
        JScrollPane spFrecuencias = new JScrollPane(tblFrecuencias);
        spFrecuencias.setBounds(10, 230, 470, 200);
        add(spFrecuencias);

        String[] encabezados = { "Variable",
                "Frecuencia absoluta (f)",
                "Frecuencia acumulada (F)",
                "Frecuencia relativa (fr)",
                "Frecuencia porcentual (%f)" };

        DefaultTableModel modelo = new DefaultTableModel(null, encabezados);
        tblFrecuencias.setModel(modelo);

        // eventos
        btnAgregar.addActionListener(e -> {
            agregarRespuesta();
        });
        btnQuitar.addActionListener(e -> {
            quitarRespuesta();
        });
    }

    private void agregarRespuesta() {
        totalRespuestas++;
        respuestas[totalRespuestas] = cmbRespuesta.getSelectedItem().toString();
        mostrarRespuestas();
    }

    private void mostrarRespuestas() {
        String[] respuestasAMostrar = new String[totalRespuestas + 1];
        for (int i = 0; i <= totalRespuestas; i++) {
            respuestasAMostrar[i] = respuestas[i];
        }
        lstRespuestas.setListData(respuestasAMostrar);
    }

    private void quitarRespuesta() {
        if (lstRespuestas.getSelectedIndex() >= 0) {
            for (int i = lstRespuestas.getSelectedIndex() + 1; i <= totalRespuestas; i++) {
                respuestas[i - 1] = respuestas[i];
            }
            totalRespuestas--;
            mostrarRespuestas();
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe escoger la respuesta a retirar");
        }
    }

}
