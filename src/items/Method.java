package items;

import java.util.ArrayList;

public final class Method extends Scope implements Item {
    private final String returnType;
    private final ArrayList<MethodField> methodFields = new ArrayList<>();
    private final ArrayList<Parameter> parameters = new ArrayList<>();

    public Method(String name, String returnType) {
        super.name = name;
        this.returnType = returnType;
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public void addMethodField(MethodField mf) {
        methodFields.add(mf);

    }

    private String getMethodFields() {
        var res = "";
        for (MethodField mf : methodFields) {
            res += mf.show();
        }
        return res;
    }

    private String getParams() {
        var res = "";
        for (Parameter p : parameters) {
            res += p.show();
        }
        return res;
    }

    public String getReturnType() {
        return this.returnType;
    }
    public int parameterSize(){
        return parameters.size();
    }
    public boolean isClassAvailable(String query) {
        for (Parameter p : this.parameters) {
            if (p.name == query) return true;
        }
        for (MethodField f : this.methodFields) {
            if (f.type == query) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Key : Method_" + this.name + " | Value : Method ( name:" + this.name + ")" + "( return type : [" + this.returnType + "]" + "\n [parameter list: " + getParams() + "])";
    }
}
