package Interfaces;

import LogicaDeNegocios.DTOs.CarnetDTO;
import LogicaDeNegocios.DTOs.CriteriosDTO;
import LogicaDeNegocios.DTOs.DatosTablaDTO;
import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import com.itextpdf.text.Font;
import com.toedter.calendar.JDateChooser;
import gestores.GestorLicencia;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
    private JDateChooser tf_desde;
    private JDateChooser tf_hasta;
    private JTable table_resultados;
    private JButton volverButton;
    private JButton buscarButton;
    private JButton imprimirButton;
    private JPanel panelResultados;
    private JScrollPane scrollPane;
    private JLabel labelFecha;
    private Long criterioDNI;
    private String criterioNombre;
    private String criterioApellido;
    private List<ClaseLicencia> criterioClasesLicencia = new ArrayList<>();
    private LocalDateTime criterioFechaAltaLicenciaDesde;
    private LocalDateTime criterioFechaAltaLicenciaHasta;

    private List<DatosTablaDTO> datosTablaDTOS;
    private ArrayList<LicenciaDTO> listaLicenciasDTO = new ArrayList<>();

    public JPanel getPane(){
        return rootPane;
    }

    private void createUIComponents()
    {
        tf_desde = new JDateChooser();
        tf_desde.getDateEditor().setEnabled(false);
        ((JTextField)tf_desde.getDateEditor().getUiComponent()).setDisabledTextColor(Color.black);
        ((JTextField) tf_desde.getDateEditor().getUiComponent()).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount()==2){
                    ((JTextField)tf_desde.getDateEditor()).setText("");
                    tf_desde.setCalendar(null);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        tf_hasta = new JDateChooser();
        tf_hasta.getDateEditor().setEnabled(false);
        ((JTextField)tf_hasta.getDateEditor().getUiComponent()).setDisabledTextColor(Color.black);
        ((JTextField) tf_hasta.getDateEditor().getUiComponent()).addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount()==2){
                    ((JTextField)tf_hasta.getDateEditor()).setText("");
                    tf_hasta.setCalendar(null);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    public Interfaz_Imprimir_Licencia(final MainFrame frame) {
        String[] columns = {"DNI titular", "Nombre titular", "Apellido titular", "Clase(s)", "Fecha Alta"};
        datosTablaDTOS = new ArrayList<>();

        ModeloLicencias modeloLicencias = new ModeloLicencias(datosTablaDTOS, columns);
        table_resultados = new JTable(modeloLicencias);
        table_resultados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(table_resultados);

        AbstractDocument doc = (AbstractDocument) tf_dni.getDocument();
        doc.setDocumentFilter(new LimiteTexto(8));

        AbstractDocument doc_1 = (AbstractDocument) tf_nombre.getDocument();
        doc_1.setDocumentFilter(new LimiteTexto(30));

        AbstractDocument doc_2 = (AbstractDocument) tf_apellido.getDocument();
        doc_2.setDocumentFilter(new LimiteTexto(30));

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

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int[] indicesLicencias =table_resultados.getSelectedRows();
                List<Integer> indiceLicenciaList = new ArrayList<Integer>();
                List<Long> idLicenciasSeleccionadas = new ArrayList<>();

                for (int value : indicesLicencias) {
                    indiceLicenciaList.add(value);
                }

                //Integer indiceLicencia = table_resultados.getSelectedRow();
                if(indiceLicenciaList.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar al menos una licencia", "Imprimir Licencia", JOptionPane.ERROR_MESSAGE);
                }else {
                    for(Integer indice : indiceLicenciaList){
                        System.out.println(" indice : " + indice);
                        idLicenciasSeleccionadas.add(datosTablaDTOS.get(indice).getIdLicencia());
                    }
                    ArrayList listaCarnetImprimir = new ArrayList<CarnetDTO>();
                    for(Long idLicencia : idLicenciasSeleccionadas){
                        System.out.println("id licencia : " + idLicencia);
                        CarnetDTO carnetDTO = GestorLicencia.buscarCarnetDTO(idLicencia);
                        listaCarnetImprimir.add(carnetDTO);
                    }
                    frame.cambiarPanelConLicencias(MainFrame.PANE_VER_FORMATO_LICENCIA, listaCarnetImprimir);
                }
                //this.setContentPane(new LicenciaDeConducir().getPane());
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                criterioDNI = null;
                criterioFechaAltaLicenciaDesde = null;
                criterioFechaAltaLicenciaHasta = null;
                criterioClasesLicencia = new ArrayList<>();

                if(!tf_dni.getText().isEmpty()){
                    try {
                        criterioDNI = Long.valueOf(tf_dni.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Documento Ingresado Invalido", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                criterioNombre = tf_nombre.getText();
                criterioApellido = tf_apellido.getText();

                if (claseACheckBox.isSelected()) {
                    criterioClasesLicencia.add(ClaseLicencia.CLASE_A);
                }
                if (claseBCheckBox.isSelected()) {
                    criterioClasesLicencia.add(ClaseLicencia.CLASE_B);
                }
                if (claseCCheckBox.isSelected()) {
                    criterioClasesLicencia.add(ClaseLicencia.CLASE_C);
                }
                if (claseDCheckBox.isSelected()) {
                    criterioClasesLicencia.add(ClaseLicencia.CLASE_D);
                }
                if (claseECheckBox.isSelected()) {
                    criterioClasesLicencia.add(ClaseLicencia.CLASE_E);
                }
                if (claseFCheckBox.isSelected()) {
                    criterioClasesLicencia.add(ClaseLicencia.CLASE_F);
                }
                if (claseGCheckBox.isSelected()) {
                    criterioClasesLicencia.add(ClaseLicencia.CLASE_G);
                }

                if (tf_desde.getDate()==null && tf_hasta.getDate()==null) {
                    JOptionPane.showMessageDialog(frame, "Debe ingresar al menos una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(tf_desde.getDate()!=null && tf_hasta.getDate()!=null){
                    Period period = Period.between(tf_desde.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), tf_hasta.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    if(period.getYears()<0 || period.getMonths()<0 || period.getDays()<0){
                        JOptionPane.showMessageDialog(frame, "La fecha hasta no puede ser menor que la fecha desde.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (tf_desde.getDate()!=null) {
                    criterioFechaAltaLicenciaDesde= tf_desde.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(3,0,0);
                    Period periodo = Period.between(LocalDateTime.now().toLocalDate(),criterioFechaAltaLicenciaDesde.toLocalDate());
                    int anios = periodo.getYears();
                    if (anios > 6) {
                        JOptionPane.showMessageDialog(frame, "La fecha de vencimiento no puede ser de más de 6 años en el futuro.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if(tf_hasta.getDate()!=null){
                    criterioFechaAltaLicenciaHasta= tf_hasta.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(3,0,0);
                    Period periodo = Period.between(LocalDateTime.now().toLocalDate(),criterioFechaAltaLicenciaHasta.toLocalDate());
                    int anios = periodo.getYears();
                    if (anios > 6) {
                        JOptionPane.showMessageDialog(frame, "La fecha de vencimiento no puede ser de más de 6 años en el futuro.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                CriteriosDTO criteriosDTO = new CriteriosDTO();
                if (criterioDNI == null) {
                    criteriosDTO.setDniTitular("");
                } else {
                    criteriosDTO.setDniTitular(criterioDNI.toString());
                }
                criteriosDTO.setNombreTitular(criterioNombre);
                criteriosDTO.setApellidoTitular(criterioApellido);
                criteriosDTO.setClaseLicencias(criterioClasesLicencia);
                criteriosDTO.setFechaAltaDesde(criterioFechaAltaLicenciaDesde);
                criteriosDTO.setFechaAltaHasta(criterioFechaAltaLicenciaHasta);

                datosTablaDTOS = GestorLicencia.listarLicencias(criteriosDTO);

                if(datosTablaDTOS.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "No se encontraron resultados.", "Error", JOptionPane.ERROR_MESSAGE);
                    datosTablaDTOS.clear();
                    modeloLicencias.setDatosTablaDTOS(datosTablaDTOS);
                    table_resultados.updateUI();
                    return;
                } else {
                    JOptionPane.showMessageDialog(frame, "Busqueda completada.", "Consulta de licencias.", JOptionPane.INFORMATION_MESSAGE);
                    modeloLicencias.setDatosTablaDTOS(datosTablaDTOS);
                }
                //TODO ver si hacemos que al fallar busquedas se vacie la tabla -> si , hay que vaciar la tabla.

                table_resultados.updateUI();

            }
        });
    }
}
