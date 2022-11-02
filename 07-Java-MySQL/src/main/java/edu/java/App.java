package edu.java;

// Java en VSC
// https://code.visualstudio.com/docs/java/java-project#_working-with-jar-files

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        MySQL_Films dao = new MySQL_Films();
        dao.traitementAvecLaBD();
    }
}
