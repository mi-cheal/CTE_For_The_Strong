import java.util.HashSet;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class symbolTables {
    private Map<String, Symbol> symbols;

    public symbolTables()
    {
        symbols = new HashMap<>();
    }

    public void addSymbol(String name, Symbol.SymbolType type)
    {
        symbols.put(name, new Symbol(name, type));
    }

    public boolean contains(String name)
    {
        return symbols.containsKey(name);
    }

    public Symbol.SymbolType getType(String name)
    {
        return symbols.get(name).getType();
    }


    static void push(HashSet hashSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'push'");
    }

    static List<Token> peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

}
