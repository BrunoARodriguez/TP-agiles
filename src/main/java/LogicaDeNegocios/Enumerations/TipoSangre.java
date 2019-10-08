package LogicaDeNegocios.Enumerations;

public enum TipoSangre {

    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private String name;

    TipoSangre(String name){
        this.name=name;
    }
    public String getName() {
        return this.name;
    }
}
