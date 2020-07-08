package github.yeori.beautifuldb;

/**
 * TODO Util.map(..), Util.success(..) 종류의 메서드 호출을 Res 클래스로 빼낼 예정
 */
public class Res {

    public static TypeMap success(boolean success) {
        TypeMap m = new TypeMap(1);
        m.put("success", success);
        return m;
    }

    public static Object success(Object ... args) {
        if(args.length % 2 == 1) {
            throw new RuntimeException("(key, value) mismatch: ");
        }
        TypeMap m = success(true);
        for (int i = 0; i < args.length; i+=2) {
            Object k = args[i];
            Object v = args[i+1];
            if(k.getClass() != String.class) {
                throw new BeautDbException(
                        500, 
                        "SERVER_ERROR",
                        "key [%s] at index %d is not a type of String, but %s", 
                        k.toString(), i, k.getClass());
            }
            m.put((String)k, v);
        }
        return m;
    }

}
