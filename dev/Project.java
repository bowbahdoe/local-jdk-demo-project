import module info.picocli;
import module dev.mccue.tools.javac;
import module dev.mccue.tools.jar;
import module com.google.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;

@CommandLine.Command(name = "project")
public class Project {
    @CommandLine.Command(name = "clean")
    public void clean() throws Exception {
        FileUtils.deleteDirectory(new File("build"));
    }

    @CommandLine.Command(name = "compile")
    public void compile() throws Exception {
        clean();
        Javac.run(args -> {
                    args
                            ._d(Path.of("build"))
                            ._g()
                            ._parameters()
                            .__source_path(Path.of("src"))
                            // Interesting interaction with --release. It hides all modules
                            // that don't come with the stock jdk
                            // .__release(25)
                            .__source(25)
                            .__target(25)
                            .argument(Path.of("src", "Main.java"));
                });
    }

    @CommandLine.Command(name = "run")
    public void run() throws Exception {
        compile();
        Java.runner(Tool.ofSubprocess(Path.of("jvm", "bin", "java").toString()))
                .arguments(args -> {
                    args.__class_path(Path.of("build"))
                            .__enable_native_access("javafx.graphics")
                            .argument("Main");
                })
                .run();
    }

    public void main(String[] args) {
        new CommandLine(this).execute(args);
    }
}
