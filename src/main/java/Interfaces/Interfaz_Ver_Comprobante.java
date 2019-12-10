package Interfaces;

import LogicaDeNegocios.DTOs.CarnetDTO;
import gestores.GestorImpresion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class Interfaz_Ver_Comprobante {
    private JLabel lbnombreTitular;
    private JLabel lbClasesLicencia;
    private JLabel lbCostoTramite;
    private JButton imprimirComprobanteButton;
    private JButton cancelarButton;
    private JPanel rootPane;
    private JLabel lbFechaYHora;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Interfaz_Ver_Comprobante(final MainFrame frame,CarnetDTO carnetDTO) {

        StringBuffer nombreApellido = new StringBuffer().append(carnetDTO.getNombre()).append(" ").append(carnetDTO.getApellido());

        lbnombreTitular.setText(nombreApellido.toString());
        lbClasesLicencia.setText(carnetDTO.getClasesLicencia().toString());
        lbCostoTramite.setText(carnetDTO.getComprobante().getCostoTramite().toString());
        lbFechaYHora.setText(dateTimeFormatter.format(carnetDTO.getComprobante().getFechaCreacion()));



        imprimirComprobanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(GestorImpresion.imprimirComprobante(carnetDTO.getComprobante())){
                    JOptionPane.showMessageDialog(frame, "Impresion exitosa", "Imprimir comprobante", JOptionPane.INFORMATION_MESSAGE);
                    frame.setSize(1200,400);
                    frame.setLocationRelativeTo(null);
                    frame.backPreviousPane();
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialogCancelar c = new JDialogCancelar(frame);
                if(c.fueCancelado()) {
                    frame.setSize(1200,400);
                    frame.setLocationRelativeTo(null);
                    frame.backPreviousPane();
                }
            }
        });
    }


    public JPanel getPane() {
        return rootPane;
    }
}
