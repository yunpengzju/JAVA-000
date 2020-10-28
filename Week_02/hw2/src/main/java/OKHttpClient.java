import okhttp3.*;

import java.io.IOException;

public class OKHttpClient {
    private OkHttpClient client = null;

    public OKHttpClient() {
        client = new OkHttpClient();
    }

    public static void main(String[] args) {
        OKHttpClient httpClient = new OKHttpClient();

        // 构造Get Request
        String url = "http://localhost:8801";
        Request request = new Request.Builder().url(url).build();

        // 将Request封装成call
        Call call = httpClient.client.newCall(request);

        // 同步调用
        try {
            Response response = call.execute();
            if (response.body() != null) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
