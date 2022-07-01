package items;

public sealed class Variable implements Item permits Field, ClassField, ClassArrayField,MethodField {
    protected final String name;

    public Variable(String name) {
        this.name = name;
    }
}
