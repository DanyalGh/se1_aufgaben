import javax.swing.*;
import java.util.Arrays;

public class Sort implements SortIF {

    String[][] dependencies;

    @Override
    public void readDependencies(String[][] dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public boolean isWellSorted(String[] sequence) {
        if(sequence.length <= 0){
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        for (String task : sequence) {
            for (String[] dependency : dependencies) {
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
}