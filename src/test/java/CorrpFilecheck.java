import java.io.File;
import java.lang.reflect.Method;

public class CorrpFilecheck {
    public static void main(String[] args) {
        System.out.println("Jeleel");
        File directory = new File("target/classes");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".class"));

            if (files != null) {
                for (File file : files) {
                    try {
                        String className = file.getAbsolutePath()
                            .replace("target" + File.separator + "classes" + File.separator, "")
                            .replace(File.separator, ".")
                            .replace(".class", "");

                        System.out.println("Attempting to load class: " + className);

                        // Attempt to load the class
                        Class<?> clazz = Class.forName(className);
                        Method[] methods = clazz.getDeclaredMethods();
                        System.out.println("Class loaded: " + className);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Corrupted class file: " + file.getAbsolutePath());
                    } catch (NoClassDefFoundError e) {
                        System.out.println("Class definition not found: " + file.getAbsolutePath());
                    } catch (Exception e) {
                        System.out.println("Error processing class file: " + file.getAbsolutePath() + " - " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("Target directory does not exist or is not a directory.");
        }
    }
}
