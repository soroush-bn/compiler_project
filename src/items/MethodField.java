package items;

public final class MethodField extends Variable {
    private final String type;

    public MethodField(String name, String type) {
        super(name);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
