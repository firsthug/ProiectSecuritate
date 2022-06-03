package com.aop.demo.Service;

import com.aop.demo.Model.AOPCase;
import com.aop.demo.Model.AOPComment;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class APIService {
    public static final String POST_CASE_API = "https://aopmada.atlassian.net/rest/api/3/issue";
    public static final String SEARCH_CASES_API = "https://aopmada.atlassian.net/rest/api/3/search";
    public static final String CASE_COMMENTS_API = "https://aopmada.atlassian.net/rest/api/3/issue/{issueKey}/comment";
    public static final String USER = "ancuta.vaduva1002@gmail.com";
    public static final String PASSWORD = "OV3hsSGLlWHnjhhDmDqm6043";


    public String sendCaseToJira(AOPCase aopCase) throws Exception {
        HttpResponse<JsonNode> response = Unirest.post(POST_CASE_API)
                .basicAuth(USER, PASSWORD)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(generateJson(aopCase).toString())
                .asJson();

        if(response.getStatus() != 201){
            throw new Exception(response.getBody().getObject().getJSONObject("errors").get("summary").toString());
        }

        return response.getBody().getObject().get("key").toString();
    }


    public Map<String, String> getCasesByIds(List<String> jiraIds) throws Exception{
        String ids = String.join(" ,", jiraIds);
        HttpResponse<JsonNode> response = Unirest.get(SEARCH_CASES_API)
                .basicAuth(USER, PASSWORD)
                .header("Accept", "application/json")
                .queryString("jql","project = AOP AND issuekey in (" + ids + ")" )
                .asJson();

        if(response.getStatus() != 201 && response.getStatus() != 200){
            throw new Exception(response.getBody().getObject().get("errorMessages").toString());
        }

        Map<String, String> keyStatusMap = new HashMap<>();
        kong.unirest.json.JSONArray issuesArray = response.getBody().getObject().getJSONArray("issues");
        for (int i = 0; i < issuesArray.length(); i++) {
            kong.unirest.json.JSONObject issueObj = issuesArray.getJSONObject(i);
            kong.unirest.json.JSONObject issueFields = issueObj.getJSONObject("fields");
            kong.unirest.json.JSONObject issueStatus = issueFields.getJSONObject("status");

            keyStatusMap.put(issueObj.get("key").toString(), issueStatus.get("name").toString());
        }

        return keyStatusMap;
    }

    public List<AOPComment> getCommentsByCaseKey(String key) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get(CASE_COMMENTS_API)
                .basicAuth(USER, PASSWORD)
                .header("Accept", "application/json")
                .routeParam("issueKey", key)
                .queryString("orderBy","-created" )
                .asJson();

        if(response.getStatus() != 201 && response.getStatus() != 200){
            throw new Exception(response.getBody().getObject().get("errorMessages").toString());
        }

        List<AOPComment> comments = new ArrayList<>();
        kong.unirest.json.JSONArray commentsArray = response.getBody().getObject().getJSONArray("comments");
        for (int i = 0; i < commentsArray.length(); i++) {
            AOPComment comment = new AOPComment();
            kong.unirest.json.JSONObject commObj = commentsArray.getJSONObject(i);
            kong.unirest.json.JSONObject authorObj = commObj.getJSONObject("author");

            comment.setAuthor(authorObj.getString("displayName"));

            LocalDateTime commDate = LocalDateTime.parse(commObj.get("created").toString(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
            comment.setCommentTime(commDate);

            kong.unirest.json.JSONObject bodyCommObj = commObj.getJSONObject("body");
            kong.unirest.json.JSONArray bodyCommArrayContent = bodyCommObj.getJSONArray("content");
            kong.unirest.json.JSONObject bodyCommContentObj = bodyCommArrayContent.getJSONObject(0);
            kong.unirest.json.JSONArray contentArray = bodyCommContentObj.getJSONArray("content");
            kong.unirest.json.JSONObject contentObj = contentArray.getJSONObject(0);

            comment.setText(contentObj.get("text").toString());
            comments.add(comment);
        }

        return comments;
    }

    public JSONObject generateJson(AOPCase AopCase){
        String jsonInputString;
        JSONObject fields = new JSONObject();

        //issueType
        JSONObject issueType = new JSONObject();
        issueType.put("id","10001");
        fields.put("issuetype",issueType);

        //project
        JSONObject project = new JSONObject();
        project.put("id","10000");
        fields.put("project",project);

        //customField userId
        fields.put("customfield_10031",AopCase.getCaseUser().getUserID().toString());

        //summary
        fields.put("summary",AopCase.getSubject());

        //description
        JSONObject descriptionInnerContentItem = new JSONObject();
        descriptionInnerContentItem.put("type","text");
        descriptionInnerContentItem.put("text",AopCase.getDescription());

        JSONArray innerContentArray = new JSONArray();
        innerContentArray.put(descriptionInnerContentItem);

        JSONObject descriptionOuterContentItem = new JSONObject();
        descriptionOuterContentItem.put("type","paragraph");
        descriptionOuterContentItem.put("content", innerContentArray);

        JSONArray outerContentArray = new JSONArray();
        outerContentArray.put(descriptionOuterContentItem);

        JSONObject description = new JSONObject();
        description.put("type", "doc");
        description.put("version", 1);
        description.put("content", outerContentArray);

        fields.put("description", description);

        //priority
        JSONObject priority = new JSONObject();
        priority.put("id",AopCase.getPriority());//1-Highest 2-High 3-Medium 4-Low 5-Lowest
        fields.put("priority",priority);

        //labels
        JSONArray labels = new JSONArray();
        labels.put(AopCase.getType());
        labels.put(AopCase.getCategory());
        fields.put("labels", labels);

        //parent object
        JSONObject parentObject = new JSONObject();
        parentObject.put("fields", fields);

        jsonInputString = parentObject.toString();
        System.out.println(jsonInputString);

        return parentObject;
    }

    /*
    public String sendCaseToJira(AOPCase AopCase) throws Exception {
        URL url = new URL (POST_CASE_API);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        String authHeaderValue = "Basic " + new String(encodedAuth);
        con.setRequestProperty("Authorization", authHeaderValue);

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        HttpURLConnection con = initializeConnection(POST_CASE_API);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        String jsonInputString = generateJson(AopCase).toString();

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());

            JSONObject obj = new JSONObject(response.toString());
            return obj.get("id").toString();
        }
    }
     */
}
