package Interfaces;

import LogicaDeNegocios.DTOs.DatosTablaDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloLicencias extends AbstractTableModel {

    private List<DatosTablaDTO> datosTablaDTOS;
    private String[] columnas;

    public ModeloLicencias(List<DatosTablaDTO> datosTablaDTOS, String[] columnas){
        this.datosTablaDTOS = datosTablaDTOS;
        this.columnas = columnas;
    }

    @Override
    public int getRowCount() {
        return datosTablaDTOS.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int indice) {
        return this.columnas[indice];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return datosTablaDTOS.get(rowIndex).getDni();
            case 1: return datosTablaDTOS.get(rowIndex).getNombre();
            case 2: return datosTablaDTOS.get(rowIndex).getApellido();
            case 3: return datosTablaDTOS.get(rowIndex).getClasesLicencia();
            case 4: return datosTablaDTOS.get(rowIndex).getFechaAlta();
            case 5: return datosTablaDTOS.get(rowIndex).getEstadoLicencia();
            default: return null;
        }
    }

    public List<DatosTablaDTO> getDatosTablaDTOS() {
        return datosTablaDTOS;
    }

    public void setDatosTablaDTOS(List<DatosTablaDTO> datosTablaDTOS) {
        this.datosTablaDTOS = datosTablaDTOS;
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }
}
