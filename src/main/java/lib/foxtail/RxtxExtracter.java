package lib.foxtail;

import cc.foxtail.assistant.lib.Extracter;

import java.io.File;
import java.net.URL;

public final class RxtxExtracter {
    private static File serialTmp;
    private static File parallelTmp;
    private static boolean serialLoader;
    private static boolean parallelLoader;

    private static String extractSerialLib() {
        if (serialTmp != null)
            return serialTmp.getAbsolutePath();
        String serial = '/' + RxtxExtracter.class.getPackage().getName().replace('.', '/') + '/' + Extracter.adaptionExtraLibName("rxtxSerial-22-20081207");
        URL url = RxtxExtracter.class.getResource(serial);
        serialTmp = Extracter.createTempDirectory("rxtxSerial");
        return Extracter.extractLibrary(url, serialTmp, "rxtxSerial.dll");
    }

    private static String extractParallelLib() {
        if (parallelTmp != null)
            return parallelTmp.getAbsolutePath();
        String paralle = '/' + RxtxExtracter.class.getPackage().getName().replace('.', '/') + '/' + Extracter.adaptionExtraLibName("rxtxSerial-22-20081207");
        URL url = RxtxExtracter.class.getResource(paralle);
        parallelTmp = Extracter.createTempDirectory("rxtxParallel-2.2-20081207");
        return Extracter.extractLibrary(url, parallelTmp, "rxtxParallel-2.2-20081207.dll");
    }

    public static void main(String[] args) {
        RxtxExtracter.load();
    }

    /**
     * load native dll or so
     */
    public static synchronized void load() {
        if (!serialLoader) {
            System.load(extractSerialLib());
            serialLoader = true;
        }
        if (!parallelLoader) {
            System.load(extractParallelLib());
            parallelLoader = true;
        }
    }
}
