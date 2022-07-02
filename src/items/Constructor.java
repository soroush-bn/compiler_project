package items;

import java.util.ArrayList;

public final class Constructor extends Scope implements Item {

    private final String name;
    private final ArrayList<MethodField> parameters = new ArrayList<>();

    public Constructor(String name) {
        this.name = name;
    }

    public void addParameter(MethodField parameter) {
        parameters.add(parameter);
    }

    private String getParams() {
        var res = "";
        for (MethodField mf : parameters
        ) {
            res += mf.show();

        }
        return res;
    }

    @Override
    public String toString() {
        return "Key : Constructor_" + this.name + " | Value : Constructor ( name:" + this.name + ")" + "\n [parameter list: " + getParams() + "])";
    }
}
