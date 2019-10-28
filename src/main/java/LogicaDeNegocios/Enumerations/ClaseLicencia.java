package LogicaDeNegocios.Enumerations;

public enum ClaseLicencia {
    CLASE_A("CLASE_A"),
    CLASE_B("CLASE_B"),
    CLASE_C("CLASE_C"),
    CLASE_D("CLASE_D"),
    CLASE_E("CLASE_E"),
    CLASE_F("CLASE_F"),
    CLASE_G("CLASE_G");

    private String name;

    ClaseLicencia(String name){
        this.name=name;
    }

    public String getName() {
        return this.name;
    }
}
