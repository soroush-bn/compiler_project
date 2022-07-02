package items;

//input param
public final class Parameter extends Variable {
    private final String type;
    private final Boolean isDefiend;
    private final int index ;

    public Parameter(String name, String type, Boolean isDefiend,int index) {
        super(name);
        this.type = type;
        this.isDefiend = isDefiend;
        this.index=index;
    }
    public String show() {
        return "[type :" + this.type + ", index : " + this.index + " ]";
    }
    @Override
    public String toString() {
        return "Key : Field_" + this.name + " | Value :  Parameter [ name: (" +
                this.name + ") type : ( classType:" + this.type + ") isDefiend : ( isDefiend:" + this.isDefiend + ")]";
    }
}
