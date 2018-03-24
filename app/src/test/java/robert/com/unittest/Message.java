package robert.com.unittest;

import android.support.v4.util.ArraySet;
import android.text.TextUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by robert on 2017 Nov 05.
 */
public class Message {
    /**
     * Null to empty.
     *
     * @param input the input
     * @return the string
     */
    public static String nullToEmpty(Object input) {
        return (input == null ? "" : ("null".equals(input) ? "" : input.toString()));
    }

    /**
     * Checks if is empty or null.
     *
     * @param input the input
     * @return true, if is empty or null
     */
    public static boolean isEmptyOrNull(String input) {
        if (input == null) return true;
        if (input.trim().length() == 0 || "null".equalsIgnoreCase(input.trim())) return true;
        return false;
    }

    /**
     * Checks if is empty or null.
     *
     * @param input the object
     * @return true, if is empty or null
     */
    public static boolean isEmptyOrNull(Object input) {
        if (input == null) return true;
        if (nullToEmpty(input).trim().length() == 0) return true;
        return false;
    }

    @Test
    public void uniqueVal() throws Exception {

        class UniqueVal {
            public String messageId;
            public String content;
            public String msgType;
            public String callId;

            public UniqueVal(String messageId, String content, String msgType) {
                this.messageId = messageId;
                this.content = content;
                this.msgType = msgType;
                if (!isEmptyOrNull(content) && (msgType == "STARTVIDEO" || msgType == "STARTVOICE" || msgType == "ENDVIDEO" || msgType == "ENDVOICE")) {

                    String[] tmp = content.split("\\|");
                    if (tmp != null && tmp.length == 3) {
                        callId = tmp[1];
                    } else callId = "";
                }
            }

            @Override
            public boolean equals(Object message) {
                System.out.println("\n\n\n========================>0");
                if (this == message) return true;
                System.out.println("========================>1.getClass()=" + getClass() + "|message.getClass()=" + message.getClass());
                //if (message == null || getClass() != message.getClass()) return false;

                UniqueVal uniqueVal = (UniqueVal) message;
                System.out.println("========================>2.messageId=" + messageId + "|uniqueVal.messageId=" + uniqueVal.messageId + "\n==========================|callId=" + callId + "|uniqueVal.callId=" + uniqueVal.callId);

                if (messageId.equals(uniqueVal.messageId)) return true;//messageId equals
                System.out.println("========================>3");
                if (nullToEmpty(callId).equals(nullToEmpty(uniqueVal.callId))) return true;// callId equals callId
                System.out.println("========================>4");
                if (nullToEmpty(content).startsWith(uniqueVal.messageId + "|")) return true;// content startsWith messageId
                System.out.println("========================>5");
                if (nullToEmpty(content).contains("|" + uniqueVal.messageId + "|")) return true;// content contains messageId
                System.out.println("========================>6");
                return false;
            }

            @Override
            public int hashCode() {
                // if (callId is empty or null) and messageId not null and not empty then return has code if messageId
                if (!isEmptyOrNull(messageId)) return messageId.hashCode();
                // if callId not null and not empty then return has code if callId
                if (!isEmptyOrNull(callId) && !nullToEmpty(content).startsWith(messageId + "|") && !nullToEmpty(content).contains("|" + messageId + "|")) return callId.hashCode();
                if (!isEmptyOrNull(callId) && nullToEmpty(callId).equals(messageId)) return callId.hashCode();

                // if (callId is empty or null) and messageId not null and not empty then return has code if messageId
                //if (!isEmptyOrNull(messageId)) return messageId.hashCode();

                int result = messageId.hashCode();
                result = 31 * result + (callId != null ? callId.hashCode() : 0);
                System.out.println("------------------->hashCode(444).messageId=" + messageId + "\tresult =" + result + "\t\tcallId =" + callId);
                return result;
            }

            @Override
            public String toString() {
                return messageId + "->callId=" + callId + "->content=" + content;
            }

        }

        //LinkedHashSet<UniqueVal> q = new LinkedHashSet<UniqueVal>();//Not allow input duplicate
        List<UniqueVal> q = new ArrayList<>();//Allow input duplicate
        q.add(new UniqueVal("59c21ff8e4b04346814ea37b&59a3c27ee4b0cd5b14c9c297&0386fd1ed8a711e7b", "6|0386fd1e-d8a7-11e7-b12b-c3fe53567f80|16", "ENDVIDEO"));
        q.add(new UniqueVal("59c21ff8e4b04346814ea37b&59a3c27ee4b0cd5b14c9c297&e2ee56acd67011e79", "2|e2ee56ac-d670-11e7-9d94-b3220d7d81e8|11", "ENDVOICE"));
        q.add(new UniqueVal("59c21ff8e4b04346814ea37b&59a3c27ee4b0cd5b14c9c297&d4758a5ad67011e79", "2|d4758a5a-d670-11e7-9d5f-b3220d7d81e8|6", "ENDVOICE"));
        q.add(new UniqueVal("59a3c27ee4b0cd5b14c9c297&59c21ff8e4b04346814ea37b&bfa4af3ed67011e79", "2|bfa4af3e-d670-11e7-9d31-b3220d7d81e8|14", "ENDVOICE"));
        q.add(new UniqueVal("59a3c27ee4b0cd5b14c9c297&59c21ff8e4b04346814ea37b&afa1d080d67011e79", "2|afa1d080-d670-11e7-9d00-b3220d7d81e8|5", "ENDVOICE"));
        q.add(new UniqueVal("59fa91a7e4b0f7d2b22d0ff6&59fa921ce4b0f7d2b22d1002&20171205074207833", "nj", "PP"));
        q.add(new UniqueVal("59fa91a7e4b0f7d2b22d0ff6&59fa921ce4b0f7d2b22d1002&edcf82b4cff711e78", "2|edcf82b4-cff7-11e7-8c26-191a54412024|3", "ENDVOICE"));

        q.add(new UniqueVal("59fa921ce4b0f7d2b22d1002&59fa91a7e4b0f7d2b22d0ff6&d46225e8cff711e78", "2|d46225e8-cff7-11e7-8bf1-191a54412024|6", "ENDVOICE"));
        //q.add(new UniqueVal("59fa921ce4b0f7d2b22d1002&59fa91a7e4b0f7d2b22d0ff6&d46225e8cff711e78", "3|d46225e8-cff7-11e7-8bf1-191a54412024|0", "ENDVOICE"));
        q.add(new UniqueVal("59fa921ce4b0f7d2b22d1002&59fa91a7e4b0f7d2b22d0ff6&20171205163545123", "3|d46225e8-cff7-11e7-8bf1-191a54412024|0", "ENDVOICE"));

        q.add(new UniqueVal("59fa921ce4b0f7d2b22d1002&59fa91a7e4b0f7d2b22d0ff6&810cd7d6c06511e7b", "2|810cd7d6-c065-11e7-bd64-191a54412024|23", "ENDVOICE"));
        q.add(new UniqueVal("59a3c27ee4b0cd5b14c9c297&59c21ff8e4b04346814ea37b&afa1d080d67011e79", "2|afa1d080-d670-11e7-9d00-b3220d7d81e8|5", "ENDVOICE"));
        q.add(new UniqueVal("59fa921ce4b0f7d2b22d1002&59fa91a7e4b0f7d2b22d0ff6&d905e2f4bf8211e78", "2|d905e2f4-bf82-11e7-8155-191a54412024|2", "ENDVOICE"));
        q.add(new UniqueVal("59fa921ce4b0f7d2b22d1002&59fa91a7e4b0f7d2b22d0ff6&cad51c2cbf8211e78", "2|cad51c2c-bf82-11e7-8123-191a54412024|3", "ENDVOICE"));
        q.add(new UniqueVal("58f98144e4b0144b0ae26364&5a1fc1fce4b0d041e10de270&ae20d820d68211e7a", "2|ae20d820-d682-11e7-a642-3daf7f0b8c55|32", "ENDVOICE"));
        q.add(new UniqueVal("58f98144e4b0144b0ae26364&5a1fc1fce4b0d041e10de270&ae20d820d68211e7a", "3|ae20d820-d682-11e7-a642-3daf7f0b8c55|0", "ENDVOICE"));
        q.add(new UniqueVal("5a1fc1fce4b0d041e10de270&58f98144e4b0144b0ae26364&20171201033853205", "hhhjjjjffffffffffffffffdffffffff", "PP"));
        q.add(new UniqueVal("59c21ff8e4b04346814ea37b&59a3c27ee4b0cd5b14c9c297&0386fd1ed8a711e7b", "6|0386fd1e-d8a7-11e7-b12b-c3fe53567f80|16", "ENDVIDEO"));
        q.add(new UniqueVal("5a1fc1fce4b0d041e10de270&58f98144e4b0144b0ae26364&20171201033853205", "hhhjjjjffffffffffffffffdffffffff", "PP"));

        q.add(new UniqueVal("5a1fc1fce4b0d041e10de270&58f98144e4b0144b0ae26364&bdd86148d5ba11e7a","2|bdd86148-d5ba-11e7-a117-3daf7f0b8c55|4", "ENDVOICE"));

        q.add(new UniqueVal("5a1fc1fce4b0d041e10de270&58f98144e4b0144b0ae26364&bdd86148d5ba11e7a","5a1fc1fce4b0d041e10de270&58f98144e4b0144b0ae26364&bdd86148d5ba11e7a|bdd86148-d5ba-11e7-a117-3daf7f0b8c55|4", "ENDVOICE"));

        //q.add(new UniqueVal("5a1fc1fce4b0d041e10de270&58f98144e4b0144b0ae26364&bdd86148d5ba11e7a","2|5a1fc1fce4b0d041e10de270&58f98144e4b0144b0ae26364&bdd86148d5ba11e7a|4", "ENDVOICE"));
        //if allow input duplicate message then total size equals 20 else 15
        System.out.println("\n\n\n++++++++++++++++++++++++++++++++++++++++++");
        LinkedHashSet<UniqueVal> uniqueList = new LinkedHashSet<UniqueVal>();
        uniqueList.addAll(q);
        for(Object object : uniqueList) {
            System.out.println("===>element=" + object);
        }
        System.out.println("\n\n\n------>Total size when allow input duplicate=" + q.size() + "|Total size when don't allow input duplicate=" + uniqueList.size());
        System.out.println("\n\n\n====================================================================================================\n\n\n");
        ArraySet<UniqueVal> message = new ArraySet<>();
        message.addAll(q);
        for(UniqueVal mess : message) {
            System.out.println("===>mess=" + mess);
        }

        System.out.println("\n\n\n------>Total size when allow input duplicate=" + q.size() + " && Total size when don't allow input duplicate=" + message.size());
        System.out.println("\n\n\n====================================================================================================\n\n\n");
    }

}
