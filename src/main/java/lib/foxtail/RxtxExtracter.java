package lib.foxtail;

import cc.foxtail.assistant.lib.Extracter;
import cc.foxtail.assistant.util.UrlHelper;

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
        URL url = UrlHelper.toUrlWithPoint("lib.foxtail." + Extracter.adaptionExtraLibName("rxtxSerial-22-20081207"));
        System.out.println(url);
        serialTmp = Extracter.createTempDirectory("rxtxSerial-2.2-20081207");
        return Extracter.extractLibrary(url, serialTmp, "rxtxSerial-2.2-20081207");
    }

    private static String extractParallelLib() {
        if (parallelTmp != null)
            return parallelTmp.getAbsolutePath();
        URL url = UrlHelper.toUrlWithPoint("lib.foxtail." + Extracter.adaptionExtraLibName("rxtxParallel-22-20081207"));
        parallelTmp = Extracter.createTempDirectory("rxtxParallel-2.2-20081207");
        return Extracter.extractLibrary(url, parallelTmp, "rxtxParallel-2.2-20081207");
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
