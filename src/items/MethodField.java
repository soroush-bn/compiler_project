package items;

public final class MethodField extends Variable {
    public final String type;
    private final Boolean isDefiend;


    public MethodField(String name, String type, Boolean isDefiend) {
        super(name);
        this.type = type;
        this.isDefiend = isDefiend;
    }

    public String show() {
        return "[type :" + this.type + ", isDefiend : " + this.isDefiend + " ]";
    }

    @Override
    public String toString() {
        return "Key : Field_" + this.name + " | Value : Method Field ( [name:" +
                this.name + ") type : ( classType:" + this.type + ") isDefiend : ( isDefiend:" + this.isDefiend + ")]";
    }
}
