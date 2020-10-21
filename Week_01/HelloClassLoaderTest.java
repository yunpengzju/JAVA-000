import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author tongchen
 */
public class HelloClassLoaderTest extends ClassLoader {
    private final static String CLASS_NAME = "Hello";
    private final static String FUNCTION = "hello";
    private final static String FILE_PATH = "/Hello.xlass";

    public static void main(String[] args) {
        try {
            Class<?> aClass = new HelloClassLoaderTest().findClass(CLASS_NAME);
            Object obj = aClass.newInstance();
            Method method = aClass.getMethod(FUNCTION);
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = this.getFileContent();
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)(255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getFileContent() {
        File f = new File(this.getClass().getResource(FILE_PATH).getPath());
        int length = (int)f.length();
        byte[] bytes = new byte[length];
        try {
            new FileInputStream(f).read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}