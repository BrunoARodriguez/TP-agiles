package Interfaces;

import LogicaDeNegocios.DTOs.CriteriosDTO;
import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Interfaz_Imprimir_Licencia {
    private JPanel rootPane;
    private JTextField tf_dni;
    private JTextField tf_nombre;
    private JTextField tf_apellido;
    private JCheckBox claseACheckBox;
    private JCheckBox claseBCheckBox;
    private JCheckBox claseCCheckBox;
    private JCheckBox claseDCheckBox;
    private JCheckBox claseECheckBox;
    private JCheckBox claseFCheckBox;
    private JCheckBox claseGCheckBox;
    private JTextField tf_desde;
    private JTextField tf_hasta;
    private JTable table_resultados;
    private JButton volverButton;
    private JButton buscarButton;
    private JButton imprimirButton;
    private JPanel panelResultados;
    private JScrollPane scrollPane;
    private JDateChooser JDateChooser1;

    private Long criterioDNI;
    private String criterioNombre;
    private String criterioApellido;
    private List<ClaseLicencia> criterioClasesLicencia = new ArrayList<>();
    private LocalDate criterioFechaAltaLicencia;


    private  ArrayList<LicenciaDTO> listaLicenciasDTO = new ArrayList<>();

    public JPanel getPane(){
        return rootPane;
    }

    private void createUIComponents()
    {
        JDateChooser1 = new JDateChooser();
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


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                if(!tf_dni.getText().isEmpty()){
                    criterioDNI= Long.valueOf(tf_dni.getText());
                }

                criterioNombre =tf_nombre.getText();
                criterioApellido =tf_apellido.getText();

                if (claseACheckBox.isSelected()){
                    if(!criterioClasesLicencia.contains(ClaseLicencia.CLASE_A)){
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_A);
                        System.out.println(ClaseLicencia.CLASE_A.toString());
                    }

                    System.out.println(criterioClasesLicencia.size());
                }

                tf_desde.getText();
                tf_hasta.getText();


                //TODO ver si cambiamos el ingreso de fechas
                System.out.println(JDateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
                JDateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();



                CriteriosDTO criteriosDTO = new CriteriosDTO();
                criteriosDTO.setDniTitular(criterioDNI);
                criteriosDTO.setNombreTitular(criterioNombre);
                criteriosDTO.setApellidoTitular(criterioApellido);
                criteriosDTO.setClaseLicencias(criterioClasesLicencia);

                System.out.println(criteriosDTO.toString());

                //TODO llamar al metodo de db que busque las licencias de acuerdo a los criterios


            }
        });
    }
}
