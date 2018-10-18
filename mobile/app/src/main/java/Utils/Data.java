package Utils;
/**
 *
 * @author: Haoyang Cui
 * @Purpose: this class is used as a temporary data storage class.
 * */
import org.json.JSONArray;
import org.json.JSONObject;

public class Data {
    // login state
    private static boolean login_state = false;
    // image label
    private static String label_img = "";
    // username, "anonymous" by default
    private static String username = "anonymous";
    // the user submission name
    private static String submitUsername = "anonymous";
    // server's ip address
    public static String url = "http://10.12.163.175";
    // input stream of the crime data, stored as string and parse into JSON after that
    public static String casesString = null;
    // the data of city Tags
    private static String cityTagsData = "";
    // set the cases string
    public static void sendCases(String cases) { casesString = cases;}
    // get case string
    public static String getCases() {return casesString;}
    // set city tags
    public static void sendCityTagsData(String cityTags) { cityTagsData=cityTags;}
    // get city tags data
    public static String getCityTagsData() {return cityTagsData;}
    // login function
    public static void loginSuccess(){
        login_state = true;
    }
    // log out function
    public static void logOut(){login_state = false;}
    // get login state
    public static boolean getLoginState(){
        return login_state;
    }
    // get server ip address
    public static String getUrl(){
        return url;
    }
    // set user name
    public static void setUsername(String un){ username = un;}
    // get user name
    public static String getUsername(){ return username;}
    // get image label info
    public static String getImageLabel(){return label_img;}
    // set image label info
    public static void setImageLabel(String label){label_img = label;}
    // set submission username, login user could submit anonymously
    public static void setSubmitUsername(String un){ submitUsername = un;}
    // get submission username
    public static String getSubmitUsername(){ return submitUsername;}
    // get case information, used in the first version API.
    public static String getCase(String ID){
        String caseInfo = null;
        try {
            JSONArray jsonArray = new JSONArray(Data.casesString);
            int i = 0;
            String[] caseID = new String[jsonArray.length()];
            String[] caseType = new String[jsonArray.length()];
            String[] caseStreet = new String[jsonArray.length()];
            String[] caseCity = new String[jsonArray.length()];
            String[] caseHour = new String[jsonArray.length()];
            String[] caseMinute = new String[jsonArray.length()];
            for (i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                caseID[i] = jsonObject.getString("id");
                caseType[i] = jsonObject.getString("title");
                caseStreet[i] = jsonObject.getString("summary");
                caseCity[i] = jsonObject.getString("content");
                caseHour[i] = jsonObject.getString("is_valid");
                caseMinute[i] = jsonObject.getString("created_at");
            }
            for (i = 0; i < jsonArray.length(); i++){
                if(caseID[i].equals(ID)){
                    caseInfo = "Title: " + caseType[i] + "\n" +
                            "summary: " + caseStreet[i] + "\n" +
                            "content: " + caseCity[i] + "\n" +
                            "valid: " + caseHour[i] + "\n" + "created at" + caseMinute[i] + "\n";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caseInfo;
    }
}
