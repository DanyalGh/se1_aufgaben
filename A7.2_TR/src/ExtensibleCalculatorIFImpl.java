import java.util.*;
import java.util.function.BiFunction;

public class ExtensibleCalculatorIFImpl implements ExtensibleCalculatorIF {
    HashMap<String, BiFunction> operations = new HashMap<String, BiFunction>();

    @Override
    public String[] operations() {
        Set keySet = operations.keySet();
        Iterator<String> iter = keySet.iterator();
        String[] retVal = new String[operations.size()];
        int i = 0;
        while(iter.hasNext()){
            retVal[i] = iter.next();
            i++;
        }
        return retVal;
    }

    @Override
    public void addOperation(String name, BiFunction<Integer, Integer, Integer> operation) {
        operations.put(name,operation);
    }

    @Override
    public int calc(String op, int x, int y) {
        return (int) operations.get(op).apply(x,y);
    }
}
