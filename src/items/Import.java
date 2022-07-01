package items;

public final class Import implements Item {
    private final String name;

    public Import(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Key : import_" + this.name + " | Value : import(name:" + this.name + ")";
    }
}
