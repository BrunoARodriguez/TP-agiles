package LogicaDeNegocios.Enumerations;

public enum ClaseLicencia {
    CLASE_A("Clase A"),
    CLASE_B("Clase B"),
    CLASE_C("Clase C"),
    CLASE_D("Clase D"),
    CLASE_E("Clase E"),
    CLASE_F("Clase F"),
    CLASE_G("Clase G");

    private String name;

    ClaseLicencia(String name){
        this.name=name;
    }

    public String getName() {
        return this.name;
    }
}
