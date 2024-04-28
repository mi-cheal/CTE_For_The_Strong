import java.util.HashSet;
import java.util.List;

public class Symbol {
    SymbolType name;
    SymbolType value;
    SymbolType type;

    public Symbol(SymbolType line, SymbolType line2)
    {
        this.name = line;
        this.type = line2;
    }

    public Symbol(String name2, Symbol.SymbolType type2) {
        //TODO Auto-generated constructor stub
    }

    public SymbolType getType()
    {
        return type;
    }


    public static List<Token> peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

    public static void push(HashSet hashSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'push'");
    }

    enum SymbolType
    {
        INTEGER, STRING
    }
}
