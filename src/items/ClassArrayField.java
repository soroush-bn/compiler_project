package items;

public final class ClassArrayField extends Variable {
    public final String type;
    private final Boolean isDefiend;

    public ClassArrayField(String name, String type, Boolean isDefiend) {
        super(name);
        this.type = type;
        this.isDefiend = isDefiend;
    }

    @Override
    public String toString() {
        return "Key : Field_" + this.name + " | Value : Class Array Field ( [name:" + this.name + ") type : ( classType:" + this.type + ") isDefiend : ( isDefiend:" + this.isDefiend + ")]";
    }
}
