package github.yeori.beautifuldb;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.auth.AuthOption;

public class Util {

	/**
     * roomName => [prefix]room_name[suffix]
     * @param prefix
     * @param cmf camel-case string
     * @param suffix
     * @return snake case string
     */
    public static String camelToSnake(String prefix, String cmf, String suffix) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("[a-z][A-Z]");
        Matcher m = p.matcher(cmf);
        int offset = 0;
        if (prefix != null) {
        	sb.append(prefix.trim());
        }
        while ( m.find() ) {
            sb.append( cmf.substring(offset, m.start()+1));
            sb.append('_');
            sb.append(Character.toLowerCase(cmf.charAt(m.end()-1)));
            offset = m.end();
        }
        sb.append(cmf.substring(offset));
        if (suffix != null) {
        	sb.append(suffix.trim());
        }
        return sb.toString();
    }
    /**
     * roomName => room_name[suffix]
     * @param cmf camel-case string
     * @param suffix
     * @return snake case string
     */
    public static String camelToSnake(String cmf, String suffix) {
    	return camelToSnake(null, cmf, suffix);
    }
    /**
     * roomName => room_name
     * @param cmf camel-case string
     * @return snake case string
     */
    public static String camelToSnake(String cmf) {
    	return camelToSnake(null, cmf, null);
    }
	public static Date during(String format) {
		String fmt = format.toLowerCase();
		int p1 = fmt.lastIndexOf("day");
		if (p1 > 0) {
			fmt = fmt.substring(0, p1).trim();
			int day = Integer.parseInt(fmt);
			long time = System.currentTimeMillis() + day*24*60*60*1000L;
			return new Date(time);
		} else  {
			throw new BeautDbException(500, "INVALID_PARAM", "invalid date format: [%s]", format);
		}
	}
	
	public static String parseBearer(String authrizationValue) {
		if (authrizationValue != null && authrizationValue.startsWith(("Bearer "))) {
			return authrizationValue.substring("Bearer ".length());
		} else {
			return null;
		}
	}
}
