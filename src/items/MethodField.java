package items;

public final class MethodField extends Variable {
    private final String type;
    private final int index;

    public MethodField(String name, String type, int index) {
        super(name);
        this.type = type;
        this.index = index;
    }

    public String show() {
        return "[type :" + this.type + ", index : " + this.index + " ]";
    }

    @Override
    public String toString() {
        return "Parameter{" + "type='" + type + '\'' + ", name='" + name + '\'' + '}';
    }
}
