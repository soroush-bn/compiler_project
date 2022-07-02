package items;

import java.util.ArrayList;

public final class ConditionalBlock extends Scope implements Item {
    private final ArrayList<MethodField> methodFields = new ArrayList<>();

    public ConditionalBlock(String name) {
        super.name = name;
    }

    public void addMethodField(MethodField mf) {
        methodFields.add(mf);
        this.insert("MethodField_" + mf.name, mf);

    }


    private String getMethodFields() {
        var res = "";
        for (MethodField mf : methodFields) {
            res += mf.show();
        }
        return res;
    }

    @Override
    public String toString() {

        return "Key : Conditional_" + this.name + " | Value : Class (name:" + this.name + ") ";
    }
}
