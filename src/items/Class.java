package items;

import java.util.ArrayList;
import java.util.Optional;

public final class Class extends Scope implements Item {
    private final ArrayList<ClassField> classFields = new ArrayList<>();
    private final ArrayList<Method> methods = new ArrayList<>();

    private final String parent;


    public Class(String name, String parent) {
        super.name=name;
        this.parent = parent;
    }

    public void addField(ClassField field) {
        classFields.add(field);
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public Optional<Method> getMethodByName(String name ){
        for (Method m :this.methods
             ) {
            if (m.name==name){
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }
    @Override
    public String toString() {
        return "Key : Class_" + this.name + " | Value : Class (name:" + this.name + ") (parent: " + this.parent + ")";
    }

    public String getParent() {
        return parent;
    }
}
