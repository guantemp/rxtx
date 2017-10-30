package gnu.lib;

import java.io.File;
import java.net.URL;

public final class Extracter {
    private static File serialTmp;
    private static File parallelTmp;
    private static boolean serialLoader;
    private static boolean parallelLoader;

    private static String extractSerialLib() {
        if (serialTmp != null)
            return serialTmp.getAbsolutePath();
        String serial = '/' + Extracter.class.getPackage().getName().replace('.', '/') + '/' + cc.foxtail.assistant.lib.Extracter.adaptionExtraLibName("rxtxSerial-2.2-20081207");
        URL url = Extracter.class.getResource(serial);
        serialTmp = cc.foxtail.assistant.lib.Extracter.createTempDirectory("rxtxSerial");
        return cc.foxtail.assistant.lib.Extracter.extractLibrary(url, serialTmp, "rxtxSerial-2.2-20081207");
    }

    private static String extractParallelLib() {
        if (parallelTmp != null)
            return parallelTmp.getAbsolutePath();
        String paralle = '/' + Extracter.class.getPackage().getName().replace('.', '/') + '/' + cc.foxtail.assistant.lib.Extracter.adaptionExtraLibName("rxtxSerial-2.2-20081207");
        URL url = Extracter.class.getResource(paralle);
        parallelTmp = cc.foxtail.assistant.lib.Extracter.createTempDirectory("rxtxParallel-2.2-20081207");
        return cc.foxtail.assistant.lib.Extracter.extractLibrary(url, parallelTmp, "rxtxParallel-2.2-20081207");
    }

    public static void main(String[] args) {
        Extracter.load();
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
