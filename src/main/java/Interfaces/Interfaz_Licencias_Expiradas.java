package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz_Licencias_Expiradas {
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
    private JPanel panelResultados;

    public JPanel getPane(){
        return rootPane;
    }

    public Interfaz_Licencias_Expiradas(final MainFrame frame) {
        String[] columns = {"DNI titular","Nombre titular", "Apellido titular", "Clase(s)", "Fecha Alta","Estado licencia" };
        Object[][] data = {{}};

        TableModel tableModel;
        tableModel = new DefaultTableModel(columns,2);
        table_resultados= new JTable(tableModel);
        scrollPane.setViewportView(table_resultados);


        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialogCancelar c = new JDialogCancelar(frame);
                if(c.fueCancelado()) {
                    frame.backPreviousPane();
                }
            }
        });
    }
}
