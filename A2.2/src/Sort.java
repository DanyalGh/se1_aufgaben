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

        /*
        int i = 0;
            for (String task : sequence) {
                for (String[] dependency : dependencies) {
                    if (dependency[1].equals(task)) {
                        for (int j = i; j < sequence.length; j++) {
                            if (dependency[0].equals(sequence[j])) {
                                return false;
                            }//end if
                        }// end for
                    }// end if
                }//end for
                i++;
            }//end for
        return true;
*/


        int i = 0;

        for (String task : sequence) {
            for (String[] dependency : dependencies) {
                if (dependency[1].equals(task)) {
                    for (int j = i; j < sequence.length; j++) {
                        if (dependency[0].equals(sequence[j])) {
                            return false;
                        }//end if
                    }// end for
                    for (String[] transitive: dependencies) {
                        for (String task_ : sequence){
                            if (transitive[1].equals(dependency[0]) && transitive[0].equals(task_) && !Arrays.asList(sequence).contains(dependency[0])){
                                return false;
                            }//end if
                        }//end for
                    }//end for
                }// end if
            }//end for
            i++;
        }//end for
        return true;
    }
}