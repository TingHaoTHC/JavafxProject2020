package register;

import com.github.nbaars.pwnedpasswords4j.client.PwnedPasswordChecker;
import com.github.nbaars.pwnedpasswords4j.client.PwnedPasswordClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordCompromised extends Password {

    public PasswordCompromised(String message) {

        super(message);
    }

    @Override
    public boolean checkPassword(String pass1, String pass2) {
        boolean result = false;
        String sha1 = "";
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(pass1.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String prefixHash = sha1.substring(0, 5).toUpperCase();
        String suffixHash = sha1.substring(5).toUpperCase();
//        System.out.println("hash " + sha1);
//        System.out.println("PRE " + prefixHash);
//        System.out.println("suf " + suffixHash);

        OkHttpClient client = new OkHttpClient();
        String url = "https://api.pwnedpasswords.com/range/" + prefixHash;

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute();
             ResponseBody body = response.body()) {
            if (body != null) {
                String hashes = body.string();
                String lines[] = hashes.split("\\r?\\n");
                System.out.println("return " + lines.length);



                for (String line : lines) {
                    System.out.println("hashes found "+line);
                    if (line.startsWith(suffixHash)) {
                        System.out.println(
                                "password found, count: " + line.substring(line.indexOf(":") + 1));
                        result = true;

                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
