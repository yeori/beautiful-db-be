package github.yeori.beautifuldb.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import github.yeori.beautifuldb.BeautDbException;

public class Enc {

	public static String sha256(String plainText) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(plainText.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new BeautDbException(e, 500, "ENC_ALGO_ERROR");
		} catch (UnsupportedEncodingException e) {
			throw new BeautDbException(e, 500, "ENCODING_ERROR");
		}
	}
}
