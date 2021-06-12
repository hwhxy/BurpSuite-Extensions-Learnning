package burp;

public class Config {
    private static final String EXTENDER_NAME = "plug-in4-jwtcrack4burp";
    private static final String EXTENDER_VERSION = "0.1";
    private static String PYTHON_NAME = "python3";
    private static String JWTCRACK_PATH = "~/Download/JWTPyCrack/jwtcrack.py";
    private static String FUZZ_PATH = "~/Downloads/FuzzPath";
    private static String JWTCRACK_OPTIONS_COMMAND = "";
    private static String jwt_str = "";
    private static String OS_TYPE;
    private static String REQUST_URL = "";
    private static boolean IS_INJECT = false;


    public static String getExtenderName() {
        return EXTENDER_NAME;
    }

    public static String getExtenderVersion() {
        return EXTENDER_VERSION;
    }

    public static String getPythonName() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("PYTHON_NAME");
            if (val == null) {
                return Config.PYTHON_NAME;
            } else {
                return val;
            }
        } catch (Exception e) {
            return Config.PYTHON_NAME;
        }
    }

    public static void setPythonName(String pythonName) {
        BurpExtender.callbacks.saveExtensionSetting("PYTHON_NAME", String.valueOf(pythonName));
        Config.JWTCRACK_PATH = pythonName;
    }

    public static String getJWTCRACKPath() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("JWTCRACK_PATH");
            if (val == null) {
                return Config.JWTCRACK_PATH;
            } else {
                return val;
            }
        } catch (Exception e) {
            return Config.JWTCRACK_PATH;
        }
    }

    public static void setJWTCRACKPath(String JWTCRACKPath) {
        BurpExtender.callbacks.saveExtensionSetting("JWTCRACK_PATH", String.valueOf(JWTCRACKPath));
        Config.JWTCRACK_PATH = JWTCRACKPath;
    }

    public static String GetFuzzPath() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("FUZZ_PATH");
            if (val == null) {
                return Config.FUZZ_PATH;
            } else {
                return val;
            }
        } catch (Exception e) {
            return Config.FUZZ_PATH;
        }
    }

    public static void SetFuzzPath(String fuzzpath) {
        BurpExtender.callbacks.saveExtensionSetting("FUZZ_PATH", String.valueOf(fuzzpath));
        Config.FUZZ_PATH = fuzzpath;
    }

    public static String getjwt() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("jwt_str");
            if (val == null) {
                return Config.jwt_str;
            } else {
                return val;
            }
        } catch (Exception e) {
            return Config.jwt_str;
        }
    }

    public static void setjwt(String jwt_str) {
        BurpExtender.callbacks.saveExtensionSetting("jwt_str", String.valueOf(jwt_str));
        Config.JWTCRACK_PATH = jwt_str;
    }

    //获取请求URL
    public static String getRequsturl() {
        return REQUST_URL;
    }

    //把URL写进config文件
    public static void setRequstUrl(String requestUrl) {
        REQUST_URL = requestUrl;
    }

    //get option选项
    public static String getJWTCRACKOptionsCommand() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("JWTCRACK_OPTIONS_COMMAND");
            if (val == null) {
                return Config.JWTCRACK_OPTIONS_COMMAND;
            } else {
                return val;
            }
        } catch (Exception e) {
            return Config.JWTCRACK_OPTIONS_COMMAND;
        }
    }

    //set option到config
    public static void setJWTCRACKOptionsCommand(String sqlmapOptionsCommand) {
        BurpExtender.callbacks.saveExtensionSetting("JWTCRACK_OPTIONS_COMMAND", String.valueOf(sqlmapOptionsCommand));
        Config.JWTCRACK_OPTIONS_COMMAND = sqlmapOptionsCommand;
    }


    public static boolean isIsInject() {
        return IS_INJECT;
    }

    public static void setIsInject(boolean isInject) {
        IS_INJECT = isInject;
    }
}
