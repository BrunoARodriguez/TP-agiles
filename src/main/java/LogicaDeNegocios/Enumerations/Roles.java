package LogicaDeNegocios.Enumerations;

public enum Roles {
    OPERADOR("OPERADOR"),
    SUPER_USUARIO("SUPER_USUARIO");

    private String name;
    Roles(String name){this.name = name;}
    public String getName(){return this.name;}
}
