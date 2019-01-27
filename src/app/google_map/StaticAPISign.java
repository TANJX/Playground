package app.google_map;
/*
 * Created by david on 2019/01/24.
 * Copyright ISOTOPE Studio
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;  // JDK 1.8 only - older versions may need to use Apache Commons or similar.
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;

public class StaticAPISign {

    static String getUrl(double lat, double lon, int zoom) {
        // Convert the string to a URL so we can parse it
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/staticmap?" +
                    "scale=2" +
                    "&center=" + lat + "%2c%20" + lon +
                    "&zoom=" + zoom +
                    "&size=2000x2000" +
                    "&style=feature:all%7Celement:geometry%7Cvisibility:on&style=feature:all%7Celement:labels%7Cvisibility:off&style=feature:administrative%7Celement:geometry.fill%7Cvisibility:off&style=feature:administrative.country%7Celement:all%7Cvisibility:off&style=feature:administrative.province%7Celement:all%7Cvisibility:off&style=feature:administrative.locality%7Celement:labels%7Cvisibility:off&style=feature:administrative.locality%7Celement:labels.text.fill%7Ccolor:0x2c2c2cweight:2.00&style=feature:administrative.neighborhood%7Celement:labels%7Cvisibility:off&style=feature:administrative.neighborhood%7Celement:labels.text.fill%7Ccolor:0x696969&style=feature:landscape%7Celement:geometry.fill%7Ccolor:0xffffff&style=feature:landscape.man_made%7Celement:geometry.fill%7Cvisibility:off&style=feature:poi%7Celement:geometry.fill%7Cvisibility:off&style=feature:poi.park%7Celement:geometry.fill%7Ccolor:0xf6f6f6visibility:off&style=feature:road%7Celement:geometry.fill%7Cweight:0.50&style=feature:road%7Celement:geometry.stroke%7Cvisibility:off&style=feature:road.highway%7Celement:geometry.fill%7Ccolor:0x565656weight:0.50&style=feature:road.highway%7Celement:geometry.stroke%7Cvisibility:offweight:0.01&style=feature:road.highway.controlled_access%7Celement:geometry.fill%7Cweight:0.50color:0x8e8e8e&style=feature:road.highway.controlled_access%7Celement:geometry.stroke%7Cvisibility:off&style=feature:road.arterial%7Celement:geometry.fill%7Ccolor:0x000000&style=feature:road.arterial%7Celement:geometry.stroke%7Cvisibility:offweight:0.50&style=feature:road.local%7Celement:geometry.fill%7Ccolor:0xb7b7b7&style=feature:road.local%7Celement:geometry.stroke%7Cvisibility:off&style=feature:transit%7Celement:labels.icon%7Cvisibility:off&style=feature:transit.line%7Celement:all%7Cweight:2.5&style=feature:transit.line%7Celement:geometry.fill%7Cvisibility:onweight:2.63&style=feature:water%7Celement:geometry.fill%7Ccolor:0xdedede" +
                    "&key=AIzaSyAGc1PrQU6nNa1DIVBZWlwbYGfbLXIWvrQ");
            StaticAPISign signer = new StaticAPISign(keyString);
            String request = signer.signRequest(url.getPath(), url.getQuery());

            return url.getProtocol() + "://" + url.getHost() + request;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Note: Generally, you should store your private key someplace safe
    // and read them into your code

    private static String keyString = "crfQSIdf4550zNV2VlYpWAqRMuY=";

    // This variable stores the binary key, which is computed from the string (Base64) key
    private static byte[] key;

    public StaticAPISign(String keyString) throws IOException {
        // Convert the key from 'web safe' base 64 to binary
        keyString = keyString.replace('-', '+');
        keyString = keyString.replace('_', '/');
        // Base64 is JDK 1.8 only - older versions may need to use Apache Commons or similar.
        this.key = Base64.getDecoder().decode(keyString);
    }

    public String signRequest(String path, String query) throws NoSuchAlgorithmException,
            InvalidKeyException, UnsupportedEncodingException, URISyntaxException {

        // Retrieve the proper URL components to sign
        String resource = path + '?' + query;

        // Get an HMAC-SHA1 signing key from the raw key bytes
        SecretKeySpec sha1Key = new SecretKeySpec(key, "HmacSHA1");

        // Get an HMAC-SHA1 Mac instance and initialize it with the HMAC-SHA1 key
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(sha1Key);

        // compute the binary signature for the request
        byte[] sigBytes = mac.doFinal(resource.getBytes());

        // base 64 encode the binary signature
        // Base64 is JDK 1.8 only - older versions may need to use Apache Commons or similar.
        String signature = Base64.getEncoder().encodeToString(sigBytes);

        // convert the signature to 'web safe' base 64
        signature = signature.replace('+', '-');
        signature = signature.replace('/', '_');

        return resource + "&signature=" + signature;
    }
}