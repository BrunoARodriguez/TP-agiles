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
import java.time.*;
import java.util.*;

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
    private JDateChooser tf_desde;
    private JDateChooser tf_hasta;
    private JTable table_resultados;
    private JButton volverButton;
    private JButton buscarButton;
    private JButton imprimirButton;
    private JPanel panelResultados;
    private JScrollPane scrollPane;


    private Long criterioDNI;
    private String criterioNombre;
    private String criterioApellido;
    private List<ClaseLicencia> criterioClasesLicencia = new ArrayList<>();
    private LocalDateTime criterioFechaAltaLicenciaDesde;
    private LocalDateTime criterioFechaAltaLicenciaHasta;


    private  ArrayList<LicenciaDTO> listaLicenciasDTO = new ArrayList<>();

    public JPanel getPane(){
        return rootPane;
    }

    private void createUIComponents()
    {
        tf_desde = new JDateChooser();
        tf_desde.setDate(Date.from(Instant.now()));

        tf_hasta = new JDateChooser();
        tf_hasta.setDate(Date.from(Instant.now()));
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

                if (claseACheckBox.isSelected()) {
                    if (!criterioClasesLicencia.contains(ClaseLicencia.CLASE_A)) {
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_A);
                    }
                }
                if (claseBCheckBox.isSelected()) {
                    if (!criterioClasesLicencia.contains(ClaseLicencia.CLASE_B)) {
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_B);
                    }
                }
                if (claseCCheckBox.isSelected()) {
                    if (!criterioClasesLicencia.contains(ClaseLicencia.CLASE_C)) {
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_C);
                    }
                }
                if (claseDCheckBox.isSelected()) {
                    if (!criterioClasesLicencia.contains(ClaseLicencia.CLASE_D)) {
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_D);
                    }
                }
                if (claseECheckBox.isSelected()) {
                    if (!criterioClasesLicencia.contains(ClaseLicencia.CLASE_E)) {
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_E);
                    }
                }
                if (claseFCheckBox.isSelected()) {
                    if (!criterioClasesLicencia.contains(ClaseLicencia.CLASE_F)) {
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_F);
                    }
                }
                if (claseGCheckBox.isSelected()) {
                    if (!criterioClasesLicencia.contains(ClaseLicencia.CLASE_G)) {
                        criterioClasesLicencia.add(ClaseLicencia.CLASE_G);
                    }
                }

                System.out.println(criterioClasesLicencia.size());


                if (tf_desde.getDate()!=null) {
                    //TODO ver si cambiamos el ingreso de fechas
                    criterioFechaAltaLicenciaDesde= tf_desde.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
                }
                if(tf_hasta.getDate()!=null){
                    criterioFechaAltaLicenciaHasta= tf_hasta.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(LocalTime.MAX);
                }

                CriteriosDTO criteriosDTO = new CriteriosDTO();
                criteriosDTO.setDniTitular(criterioDNI);
                criteriosDTO.setNombreTitular(criterioNombre);
                criteriosDTO.setApellidoTitular(criterioApellido);
                criteriosDTO.setClaseLicencias(criterioClasesLicencia);
                criteriosDTO.setFechaAltaDesde(criterioFechaAltaLicenciaDesde);
                criteriosDTO.setFechaAltaHasta(criterioFechaAltaLicenciaHasta);


                System.out.println(criteriosDTO.toString());

                //TODO llamar al metodo de db que busque las licencias de acuerdo a los criterios


            }
        });
    }
}
