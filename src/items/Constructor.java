package items;

import java.util.ArrayList;

public final class Constructor extends Scope implements Item {

    private final ArrayList<Parameter> parameters = new ArrayList<>();

    public Constructor(String name) {
        super.name=name;
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
        this.insert("Method_"+parameter.name,parameter);

    }

    private String getParams() {
        var res = "";
        for (Parameter p : parameters
        ) {
            res += p.show();

        }
        return res;
    }

    @Override
    public String toString() {
        return "Key : Constructor_" + this.name + " | Value : Constructor ( name:" + this.name + ")" + "\n [parameter list: " + getParams() + "])";
    }
}
