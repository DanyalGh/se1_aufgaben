public class Sort implements SortIF {
    
    String[][] dependencies;
    
    @Override
    public void readDependencies(String[][] dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public boolean isWellSorted(String[] sequence) {
        int i = 0;
        for (String task : sequence) {
            for (String[] dependency : dependencies) {
                if (dependency[1].equals(task)) {
                    for (int j = i; j < sequence.length; j++) {
                        if (dependency[0].equals(sequence[j])) {
                            return false;
                        }
                    }
                }
            }
            i++;
        }
    
        return true;
    }
}
