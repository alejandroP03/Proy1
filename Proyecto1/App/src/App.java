import org.json.JSONObject;

public class App {
    public static void main(String[] args) throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("JSON", "Test");
        System.out.println(jo.toString());
    }
}
