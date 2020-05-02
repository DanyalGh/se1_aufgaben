public interface SortIF {
    String[][] dependencies();

    boolean isWellSorted( String[] sequence );
}
