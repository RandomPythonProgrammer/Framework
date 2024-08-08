import fi.iki.elonen.NanoHTTPD;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

public class MemoryServer extends NanoHTTPD {

    private static String page =
            "PCFkb2N0eXBlaHRtbD48aHRtbCBsYW5nPWVuPjxtZXRhIGNoYXJzZXQ9dXRmLTg+PG1ldGEgY29udGVudD13aWR0aD1kZXZpY2Utd2lkdGgsaW5pdGlhbC1zY2FsZT0xIG5hbWU9dmlld3BvcnQ+PHRpdGxlPk1lbW9yeSBWaWV3ZXI8L3RpdGxlPjxsaW5rIGNyb3Nzb3JpZ2luIGhyZWY9aHR0cHM6Ly9jZG4uanNkZWxpdnIubmV0L25wbS9ib290c3RyYXBANS4zLjMvZGlzdC9jc3MvYm9vdHN0cmFwLm1pbi5jc3MgaW50ZWdyaXR5PXNoYTM4NC1RV1RLWnlqcFBFaklTdjVXYVJVOU9GZVJwb2s2WWN0blltRHI1cE5seVQyYlJqWGgwSk1oalk2aFcrQUxFd0lIIHJlbD1zdHlsZXNoZWV0PjxzdHlsZT4udmgtNzB7aGVpZ2h0Ojcwdmh9PC9zdHlsZT48Ym9keT48ZGl2IGNsYXNzPWNvbnRhaW5lci1mbHVpZD48ZGl2IGNsYXNzPSJyb3cgZ3gtNCBneS0yIj48ZGl2IGNsYXNzPSJjb2wtMTIgYmctcHJpbWFyeSB0ZXh0LWxpZ2h0Ij48aDE+TWVtb3J5IEVkaXRvcjwvaDE+PC9kaXY+PGRpdiBjbGFzcz0iY29sLTYgYm9yZGVyLWVuZCBib3JkZXItMiI+PGRpdiBjbGFzcz0iYmctc2Vjb25kYXJ5IHRleHQtY2VudGVyIHRleHQtbGlnaHQgcm91bmRlZCI+PGgzPlRlbXBvcmFyeTwvaDM+PHAgY2xhc3M9ZnctbGlnaHRlcj4oU3RvcmVkIGluIHRoZSBtZW1vcnkpPC9kaXY+PGRpdiBjbGFzcz0ib3ZlcmZsb3ctYXV0byB2aC03MCJpZD10ZW1wPjwvZGl2PjxkaXY+PGRpdiBjbGFzcz0icm93IGFsaWduLWl0ZW1zLWNlbnRlciBnLTIgYm9yZGVyLXRvcCI+PGRpdiBjbGFzcz1jb2w+PGlucHV0IGNsYXNzPWZvcm0tY29udHJvbCBpZD10ZW1wTmFtZSBwbGFjZWhvbGRlcj12YXJpYWJsZT48L2Rpdj48ZGl2IGNsYXNzPWNvbC1hdXRvPj08L2Rpdj48ZGl2IGNsYXNzPWNvbD48aW5wdXQgY2xhc3M9Zm9ybS1jb250cm9sIGlkPXRlbXBWYWx1ZSBwbGFjZWhvbGRlcj12YWx1ZT48L2Rpdj48ZGl2IGNsYXNzPWNvbC0xMj48YnV0dG9uIGNsYXNzPSJidG4gYnRuLXByaW1hcnkgdy0xMDAiaWQ9dGVtcFN1Ym1pdD5DaGFuZ2U8L2J1dHRvbj48L2Rpdj48L2Rpdj48L2Rpdj48L2Rpdj48ZGl2IGNsYXNzPSJjb2wtNiBib3JkZXItc3RhcnQgYm9yZGVyLTIiPjxkaXYgY2xhc3M9ImJnLXNlY29uZGFyeSB0ZXh0LWNlbnRlciB0ZXh0LWxpZ2h0IHJvdW5kZWQiPjxoMz5QZXJzaXN0ZW50PC9oMz48cCBjbGFzcz1mdy1saWdodGVyPihTdG9yZWQgaW4gdGhlIHN0b3JhZ2UpPC9kaXY+PGRpdiBjbGFzcz0ib3ZlcmZsb3ctYXV0byB2aC03MCJpZD1wZXJzaXN0ZW50PjwvZGl2PjxkaXYgY2xhc3M9InJvdyBhbGlnbi1pdGVtcy1jZW50ZXIgZy0yIGJvcmRlci10b3AiPjxkaXYgY2xhc3M9Y29sPjxpbnB1dCBjbGFzcz1mb3JtLWNvbnRyb2wgaWQ9cGVyc2lzdGVudE5hbWUgcGxhY2Vob2xkZXI9dmFyaWFibGU+PC9kaXY+PGRpdiBjbGFzcz1jb2wtYXV0bz49PC9kaXY+PGRpdiBjbGFzcz1jb2w+PGlucHV0IGNsYXNzPWZvcm0tY29udHJvbCBpZD1wZXJzaXN0ZW50VmFsdWUgcGxhY2Vob2xkZXI9dmFsdWU+PC9kaXY+PGRpdiBjbGFzcz1jb2wtMTI+PGJ1dHRvbiBjbGFzcz0iYnRuIGJ0bi1wcmltYXJ5IHctMTAwImlkPXBlcnNpc3RlbnRTdWJtaXQ+Q2hhbmdlPC9idXR0b24+PC9kaXY+PC9kaXY+PC9kaXY+PC9kaXY+PC9kaXY+PHNjcmlwdCBjcm9zc29yaWdpbiBpbnRlZ3JpdHk9c2hhMzg0LVl2cGNyWWYwdFkzbEhCNjBOTmttWGM1czlmRFZaTEVTYUFBNTVORHpPeGh5OUdrY0lkc2xLMWVON042akllSHogc3JjPWh0dHBzOi8vY2RuLmpzZGVsaXZyLm5ldC9ucG0vYm9vdHN0cmFwQDUuMy4zL2Rpc3QvanMvYm9vdHN0cmFwLmJ1bmRsZS5taW4uanM+PC9zY3JpcHQ+PHNjcmlwdD52YXIgb25Mb2FkPSgoKT0+e2xldCBjPWBjbGlja2A7dXBkYXRlRGlzcGxheSgpO2xldCBhPWRvY3VtZW50LmdldEVsZW1lbnRCeUlkKGB0ZW1wU3VibWl0YCk7YS5hZGRFdmVudExpc3RlbmVyKGMsdXBkYXRlVGVtcG9yYXJ5KTtsZXQgYj1kb2N1bWVudC5nZXRFbGVtZW50QnlJZChgcGVyc2lzdGVudFN1Ym1pdGApO2IuYWRkRXZlbnRMaXN0ZW5lcihjLHVwZGF0ZVBlcnNpc3RlbnQpfSk7dmFyIHVwZGF0ZVBlcnNpc3RlbnQ9KCgpPT57dXBkYXRlKGBwZXJzaXN0ZW50YCl9KTt2YXIgZmV0Y2hWYWx1ZXM9KGFzeW5jKCk9PntsZXQgYT17bWV0aG9kOmBHRVRgfTtsZXQgYj1hd2FpdCBmZXRjaChgL2FwaWAsYSk7cmV0dXJuIGF3YWl0IGIuanNvbigpfSk7dmFyIGZpbGw9KChhLGIpPT57bGV0IGM9ZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoYSk7Yy5pbm5lckhUTUw9YGA7Zm9yKGxldCBhIGluIGIpe2MuaW5uZXJIVE1MKz1jcmVhdGVWaWV3KGEsYlthXSl9fSk7dmFyIHVwZGF0ZURpc3BsYXk9KGFzeW5jKCk9PntsZXQgYT1hd2FpdCBmZXRjaFZhbHVlcygpO2ZpbGwoYHRlbXBgLGEudGVtcCk7ZmlsbChgcGVyc2lzdGVudGAsYS5wZXJzaXN0ZW50KX0pO3ZhciB1cGRhdGU9KGE9PntsZXQgYj1kb2N1bWVudC5nZXRFbGVtZW50QnlJZChgJHthfU5hbWVgKS52YWx1ZTtsZXQgYz1kb2N1bWVudC5nZXRFbGVtZW50QnlJZChgJHthfVZhbHVlYCkudmFsdWU7bGV0IGQ9YHske2F9OiB7JHtifTogJHtjfX19YDtsZXQgZT1kLmxlbmd0aDtsZXQgZj17bWV0aG9kOmBQT1NUYCxib2R5OmQsaGVhZGVyczp7IkNvbnRlbnQtTGVuZ3RoIjplfX07ZmV0Y2goYC9hcGlgLGYpfSk7dmFyIHVwZGF0ZVRlbXBvcmFyeT0oKCk9Pnt1cGRhdGUoYHRlbXBgKX0pO3ZhciBjcmVhdGVWaWV3PSgoYSxiKT0+YDxkaXYgY2xhc3M9InJvdyBib3JkZXItdG9wIGJvcmRlci1ib3R0b20gbS0xIHAtMSI+PGRpdiBjbGFzcz0iY29sIGZ3LWJvbGRlciI+JHthfTwvZGl2PjxkaXYgY2xhc3M9ImNvbC1hdXRvIj49PC9kaXY+PGRpdiBjbGFzcz0iY29sIj4ke2J9PC9kaXY+PC9kaXY+YCk7d2luZG93LmFkZEV2ZW50TGlzdGVuZXIoYGxvYWRgLG9uTG9hZCk7c2V0SW50ZXJ2YWwodXBkYXRlRGlzcGxheSwxMDApPC9zY3JpcHQ+"
            ;
    public static void main(String[] args) throws IOException {
        MemoryServer server = new MemoryServer();
        server.start(-1, false);
    }

    public MemoryServer() {
        super(8888);
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            if (session.getUri().equals("/")) {
                byte[] data = Base64.getDecoder().decode(page);
                return newFixedLengthResponse(Response.Status.OK, "text/html", new String(data, "UTF-8"));
            } else if (session.getUri().equals("/api")) {
                if (session.getMethod() == Method.POST) {
                    //this is updating data
                    int size = Integer.parseInt(session.getHeaders().get("content-length"));
                    byte[] buffer = new byte[size];
                    session.getInputStream().read(buffer);
                    String body = new String(buffer, "UTF-8");
                    JSONObject json = new JSONObject(body);
                    if (json.has("persistent")) {
                        writeItems(PersistentMemory.getInstance(), json.getJSONObject("persistent"));
                    }

                    if (json.has("temp")) {
                        writeItems(TempMemory.getInstance(), json.getJSONObject("temp"));
                    }
                    return newFixedLengthResponse(Response.Status.OK, "application/json", "");

                } else if (session.getMethod() == Method.GET) {
                    //this is fetching data
                    JSONObject json = new JSONObject();

                    json.put("persistent", listItems(PersistentMemory.getInstance()));
                    json.put("temp", listItems(TempMemory.getInstance()));

                    return newFixedLengthResponse(Response.Status.OK, "application/json", json.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "application/json", "");
    }

    public static JSONObject listItems(Memory memory) throws JSONException {
        JSONObject data = new JSONObject();
        for (String key : memory.keySet()) {
            data.put(key, memory.get(key));
        }
        return data;
    }

    public static void writeItems(Memory memory, JSONObject json) {
        json.keys().forEachRemaining((key) -> {
            try {
                memory.put(key, json.getString(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
