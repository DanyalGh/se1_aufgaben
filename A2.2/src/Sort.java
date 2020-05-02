public class Sort implements SortIF {
    
    String[][] dependencies;
    
    @Override
    public void readDependencies(String[][] dependencies) {

        this.dependencies = dependencies;
    }

    @Override
    public boolean isWellSorted(String[] sequence) {

        for (String task: sequence) {
        }
    
        return false;
    }
}
