import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class TopSortIterator implements Iterator<String> {

    String[][] order;
    int retlength = 0;
    HashSet<String> elements = new HashSet();
    List<String> sequence = new ArrayList();
    Iterator iter;

    public TopSortIterator(String[][] order) {
        this.order = order;

        for (String[] tmp : order) {
            for (String element : tmp) {
                elements.add(element);
            }
        }

        HashSet<String> usedElements = new HashSet();
        for (int i = 0; i < elements.size(); i++) {
            sequence.add((String) elements.toArray()[i]);
            usedElements.add((String) elements.toArray()[i]);
            for (int j = 1; j < elements.size(); j++) {
                for (String str : elements) {
                    if (!usedElements.contains(str)) {
                        sequence.add(str);
                        String[] tmp = new String[sequence.size()];
                        sequence.toArray(tmp);
                        if (isWellSorted(tmp)) {
                            usedElements.add(str);
                            break;
                        }
                        else {
                            sequence.remove(str);
                        }
                    }
                }
            }
            String[] tmp = new String[sequence.size()];
            sequence.toArray(tmp);
            if (isWellSorted(tmp) && (sequence.size() == elements.size())) {
                break;
            }
            else {
                usedElements = new HashSet();
                sequence = new ArrayList();
            }
        }

        iter = sequence.iterator();
    }

    public boolean isWellSorted(String[] sequence) {
        if(sequence.length <= 0){
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        for (String task : sequence) {
            for (String[] dependency : order) {
                if (dependency[1].equals(task)) {
                    for (int j = i; j < sequence.length; j++) {
                        if (dependency[0].equals(sequence[j])) {
                            return false;
                        }//end if
                    }// end for

                    boolean transitivityFailure = true;               // When checking a specific task we first assume that transitivity is not given.
                    for (int j = 0; j < i; j++) {
                        if (dependency[0].equals(sequence[j])) {
                            transitivityFailure = false;              // For the selected task, we have to find a proof, that transitivity works
                        }//end if
                    }// end for
                    if(transitivityFailure){                          //Only if transitivity was not detected for the current task, it will fail completely
                        return false;
                    }
                }// end if
            }//end for
            i++;
        }//end for
        return true;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public String next() {
        return (String) iter.next();
    }
}
