package LogicaDeNegocios.Enumerations;

public enum EstadoLicencia {

    VIGENTE("VIGENTE"),
    NO_VIGENTE("NO_VIGENTE"),
    VENCIDA("VENCIDA");

    private String name;
    EstadoLicencia(String name){this.name=name;}
    public String getName(){return this.name;}
}
