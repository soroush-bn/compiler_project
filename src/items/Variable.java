package items;

public sealed class Variable implements Item permits Parameter, ClassField, ClassArrayField,MethodField {
    protected final String name;

    public Variable(String name) {
        this.name = name;
    }
}
