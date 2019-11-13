package Interfaces;

import LogicaDeNegocios.DTOs.LicenciaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Interfaz_Imprimir_Licencia {
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
    private JButton imprimirButton;
    private JPanel panelResultados;
    private JScrollPane scrollPane;


    private  ArrayList<LicenciaDTO> listaLicenciasDTO = new ArrayList<LicenciaDTO>();

    public JPanel getPane(){
        return rootPane;
    }

    public Interfaz_Imprimir_Licencia(final MainFrame frame) {
        String[] columns = {"DNI titular","Nombre titular", "Apellido titular", "Clase(s)", "Fecha Alta" };
        Object[][] data = {{}};

        TableModel tableModel;
        tableModel = new DefaultTableModel(columns,0);
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
        imprimirButton.addActionListener(new ActionListener() {
            //TODO volver a configurar una vez que este resulta la logica de la busqueda en base de datos
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                  ArrayList<LicenciaDTO> listaLicenciasDTOImprimir = new ArrayList<LicenciaDTO>();

                int[] indicesLicencias =table_resultados.getSelectedRows();
                List<Integer> indiceLicenciaList = new ArrayList<Integer>();

                for (int value : indicesLicencias) {
                    indiceLicenciaList.add(value);
                }

                //TODO esto es solo de prueba:
                indiceLicenciaList.add(0);
                indiceLicenciaList.add(1);

                LicenciaDTO l1 = new LicenciaDTO();
                l1.setIdLicencia((long) 1);
                l1.setDNI((long) 40617525);
                l1.setFechaAltaLicencia(LocalDateTime.now());
                l1.setFechaVencimientoLicencia(LocalDateTime.now());
                l1.setEsDonante(true);

                LicenciaDTO l2 = new LicenciaDTO();
                l2.setIdLicencia((long) 2);
                l2.setDNI((long) 40617524);
                l2.setFechaAltaLicencia(LocalDateTime.now());
                l2.setFechaVencimientoLicencia(LocalDateTime.now());
                l2.setEsDonante(false);
                listaLicenciasDTO.add(l1);
                listaLicenciasDTO.add(l2);


                //Integer indiceLicencia = table_resultados.getSelectedRow();
                if(indiceLicenciaList.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar al menos una licencia", "Imprimir Licencia", JOptionPane.ERROR_MESSAGE);
                }else {

                    for(Integer indice : indiceLicenciaList){
                        LicenciaDTO licenciaSelecionada = listaLicenciasDTO.get(indice);
                        listaLicenciasDTOImprimir.add(licenciaSelecionada);
                    }

                    //LicenciaDTO licenciaSeleccionada = listaLicenciasDTO.get(indiceLicenciaList);
                    //frame.cambiarPanel(MainFrame.PANE_VER_FORMATO_LICENCIA);
                    frame.cambiarPanelConLicencias(MainFrame.PANE_VER_FORMATO_LICENCIA, listaLicenciasDTOImprimir);
                }


                //this.setContentPane(new LicenciaDeConducir().getPane());
            }
        });
    }
}
