package Interfaces;

import LogicaDeNegocios.Enumerations.ClaseLicencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Interfaz_Renovar_Licencia {
    private JPanel rootPane;
    private JTextField tf_dni;
    private JTextField tf_nombre;
    private JTextField tf_apellido;
    private JCheckBox aCheckBox;
    private JCheckBox bCheckBox;
    private JCheckBox cCheckBox;
    private JCheckBox dCheckBox;
    private JCheckBox eCheckBox;
    private JCheckBox fCheckBox;
    private JCheckBox gCheckBox;
    private JTextField tf_desde;
    private JTextField tf_hasta;
    private JTable table_resultados;
    private JButton volverButton;
    private JButton buscarButton;
    private JButton renovarButton;
    private JScrollPane scrollPane;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private Long dni;
    private String nombre;
    private String apellido;
    private List<ClaseLicencia> claseLicenciaList;
    private LocalDateTime fechaDesde, fechaHasta;

    public JPanel getPane() {
        return rootPane;
    }

    public Interfaz_Renovar_Licencia(final MainFrame frame) {
        String[] columns = {"DNI titular", "Nombre titular", "Apellido titular", "Clase(s)", "Fecha Alta"};
        Object[][] data = {{}};

        TableModel tableModel;
        tableModel = new DefaultTableModel(columns, 0);
        table_resultados = new JTable(tableModel);
        scrollPane.setViewportView(table_resultados);

        renovarButton.setEnabled(false);

        dni = null;
        fechaDesde = null;
        fechaHasta = null;
        claseLicenciaList = new ArrayList<>();


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (!tf_dni.getText().isEmpty()) {
                    dni = Long.valueOf(tf_dni.getText());
                }

                //TODO Ver si mandamos cadena vacia o null
                nombre = tf_nombre.getText();
                apellido = tf_apellido.getText();

                if (aCheckBox.isSelected()) {
                    claseLicenciaList.add(ClaseLicencia.CLASE_A);
                }
                if (bCheckBox.isSelected()) {
                    claseLicenciaList.add(ClaseLicencia.CLASE_B);
                }
                if (cCheckBox.isSelected()) {
                    claseLicenciaList.add(ClaseLicencia.CLASE_C);
                }
                if (dCheckBox.isSelected()) {
                    claseLicenciaList.add(ClaseLicencia.CLASE_D);
                }
                if (eCheckBox.isSelected()) {
                    claseLicenciaList.add(ClaseLicencia.CLASE_E);
                }
                if (fCheckBox.isSelected()) {
                    claseLicenciaList.add(ClaseLicencia.CLASE_F);
                }
                if (gCheckBox.isSelected()) {
                    claseLicenciaList.add(ClaseLicencia.CLASE_G);
                }
                if (!tf_desde.getText().isEmpty()) {
                    fechaDesde = LocalDateTime.parse(tf_desde.getText(), dateTimeFormatter);
                }
                if (!tf_hasta.getText().isEmpty()) {
                    fechaHasta = LocalDateTime.parse(tf_hasta.getText(), dateTimeFormatter);
                }

            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialogCancelar c = new JDialogCancelar(frame);
                if (c.fueCancelado()) {
                    frame.backPreviousPane();
                }
            }
        });


    }


}
