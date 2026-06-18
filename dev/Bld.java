import rife.bld.Project;

import java.io.File;

public class Bld extends Project {
    public Bld() {
        pkg = "com.example";
        name = "my-app";
        mainClass = "Main";
        version = version(0,1,0);

        compileOperation()
                .mainSourceDirectories(new File("src"));
    }

    public static void main(String[] args) {
        new Bld().start(args);
    }
}