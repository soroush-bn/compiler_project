package items;

import java.util.*;

public class Scope {
    protected final LinkedHashMap<String, Item> symbolTable = new LinkedHashMap<>();
    public String name = "no name";
    public int scopeNumber = -1;

    public void insert(String id, Item item) {
        symbolTable.put(id, item);
    }
    public void remove(String id) {
        symbolTable.remove(id);
    }

    public Optional<Item> lookup(String id) {
        return symbolTable.keySet().stream().filter(key -> key.matches(id + "_\\d+_\\d+")).map(symbolTable::get).findFirst();
    }



    public void printScope() {
        String res = "------------- " + name + " : " + scopeNumber + " -------------";
        String end = "\n==================================================================\n";
        System.out.print(res);
        printItems();
        System.out.print(end);

    }

    public void printItems() {
        //todo refactor
        var scopes = new LinkedList<Scope>();
        scopes.push(this);
        System.out.println();
        while (!scopes.isEmpty()) {
            var scope = scopes.pop();
            scope.symbolTable.forEach((k, item) -> {
                System.out.println(item);
//                if (item instanceof Scope newScope) scopes.add(newScope);
            });
            System.out.println();

        }


    }

    public int getSize() {
        return symbolTable.size();
    }
}