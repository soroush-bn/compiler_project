package items;

import java.util.ArrayList;

public final class Constructor extends Scope implements Item {

    private final String name;
    private final ArrayList<ClassField> parameters = new ArrayList<>();

    public Constructor(String name) {
        this.name = name;
    }

    public void addParameter(ClassField parameter) {
        parameters.add(parameter);
    }

    @Override
    public String toString() {
        return "Key : Method_" + this.name + " | Value : Method ( name:" + this.name + ")" + "\n [parameter list: [" + parameters + "]])";
    }
}
