package dploy;

public class CheckOS {
	private static String OS = System.getProperty("os.name").toLowerCase();

	// Return OS type
	CheckOS(){};
	
	public String getOS() {

		if (isWindows()) {
			return "windows";
		} else if (isMac()) {
			return "mac";
		} else if (isUnix()) {
			return "unix";
		} else if (isSolaris()) {
			return "solaris";
		} else {
			return null;
		}
	}

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

}
