package items;

import java.util.ArrayList;

public final class Method extends Scope implements Item {
    private final String name;
    private final String returnType;
    private final ArrayList<ClassField> parameters = new ArrayList<>();

    public Method(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public void addParameter(ClassField parameter) {
        parameters.add(parameter);
    }

    @Override
    public String toString() {
        return "Key : Method_" + this.name + " | Value : Method ( name:" + this.name + ")" + "( return type : [" + this.returnType + "]" + "\n [parameter list: [" + parameters + "]])";
    }
}
