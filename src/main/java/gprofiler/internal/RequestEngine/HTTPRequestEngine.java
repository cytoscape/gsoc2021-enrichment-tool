package gprofiler.internal.RequestEngine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.work.SynchronousTaskManager;

/**
 * For handling API requests to gProfiler
 */
public class HTTPRequestEngine {

    private final String basicURL = "https://biit.cs.ut.ee/gprofiler/api/";
    HashMap<String,String> defaultParameters;

    public HTTPRequestEngine(){
        /**
         * Initializing default parameters
         * Reference for values: https://github.com/PathwayCommons/app-ui/blob/master/src/server/external-services/gprofiler/gprofiler.js
         */
        defaultParameters = new HashMap<>();
        defaultParameters.put("organism",new String("hsapiens"));
        defaultParameters.put("sources","['GO:BP', 'REAC']");
        defaultParameters.put("user_threshold","0.05");
        defaultParameters.put("all_results","false");
        defaultParameters.put("ordered","false");
        defaultParameters.put("combined", "false");
        defaultParameters.put("measure_underrepresentation", "false");
        defaultParameters.put("no_iea", "false");
        defaultParameters.put("domain_scope","annotated");
        defaultParameters.put("numeric_ns","ENTREZGENE_ACC");
        defaultParameters.put("significance_threshold_method","g_SCS");
        defaultParameters.put("background","[]");
        defaultParameters.put("no_evidences", "false");

    }

    public HttpResponse<String> makePostRequest(String endpoint , Map<String,String> parameters) {
        HttpClient client = HttpClient.newHttpClient();
        StringBuffer urlConverter = new StringBuffer();
        urlConverter.append(this.basicURL);
        urlConverter.append(endpoint);
        String url = urlConverter.toString();
        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        String jsonBody = gson.toJson(parameters,gsonType);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public HttpResponse<String> makeGetRequests(String endpoint) {
        //fetches data using a specific api endpoint
        HttpClient client = HttpClient.newHttpClient();
        StringBuffer urlConverter = new StringBuffer();
        urlConverter.append(this.basicURL);
        urlConverter.append(endpoint);
        String url = urlConverter.toString();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
};

