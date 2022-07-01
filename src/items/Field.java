package items;

public final class Field extends Variable {
    private final String type;
    private final Boolean isDefiend;

    public Field(String name, String type, Boolean isDefiend) {
        super(name);
        this.type = type;
        this.isDefiend = isDefiend;
    }

    @Override
    public String toString() {
        return "Key : Field_" + this.name + " | Value :  Field ( [name:" +
                this.name + ") type : ( classType:" + this.type + ") isDefiend : ( isDefiend:" + this.isDefiend + ")]";
    }
}
