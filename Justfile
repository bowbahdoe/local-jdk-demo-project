clean:
    rm -rf build

compile: clean
    jvm/bin/javac -d build -g -parameters --source-path src --source 25 --target 25 src/Main.java

run: compile
    jvm/bin/java --class-path build --enable-native-access=javafx.graphics Main