package LogicaDeNegocios.Enumerations;

public enum ClaseLicencia {
    CLASE_A("A"),
    CLASE_B("B"),
    CLASE_C("C"),
    CLASE_D("D"),
    CLASE_E("E"),
    CLASE_F("F"),
    CLASE_G("G");

    private String name;

    ClaseLicencia(String name){
        this.name=name;
    }

    public String getName() {
        return this.name;
    }



}
