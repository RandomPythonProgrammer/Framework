package com.jchen.memoryserver;

import fi.iki.elonen.NanoHTTPD;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

public class MemoryServer extends NanoHTTPD {

    private static String page =
            "PCFkb2N0eXBlaHRtbD48aHRtbCBsYW5nPWVuPjxtZXRhIGNoYXJzZXQ9dXRmLTg+PG1ldGEgY29udGVudD13aWR0aD1kZXZpY2Utd2lkdGgsaW5pdGlhbC1zY2FsZT0xIG5hbWU9dmlld3BvcnQ+PHRpdGxlPk1lbW9yeSBWaWV3ZXI8L3RpdGxlPjxsaW5rIGNyb3Nzb3JpZ2luIGhyZWY9aHR0cHM6Ly9jZG4uanNkZWxpdnIubmV0L25wbS9ib290c3RyYXBANS4zLjMvZGlzdC9jc3MvYm9vdHN0cmFwLm1pbi5jc3MgaW50ZWdyaXR5PXNoYTM4NC1RV1RLWnlqcFBFaklTdjVXYVJVOU9GZVJwb2s2WWN0blltRHI1cE5seVQyYlJqWGgwSk1oalk2aFcrQUxFd0lIIHJlbD1zdHlsZXNoZWV0PjxzdHlsZT4udmgtNzB7aGVpZ2h0Ojcwdmh9PC9zdHlsZT48Ym9keT48ZGl2IGNsYXNzPWNvbnRhaW5lci1mbHVpZD48ZGl2IGNsYXNzPSJyb3cgZ3gtNCBneS0yIj48ZGl2IGNsYXNzPSJjb2wtMTIgYmctcHJpbWFyeSB0ZXh0LWxpZ2h0Ij48aDE+TWVtb3J5IEVkaXRvcjwvaDE+PC9kaXY+PGRpdiBjbGFzcz0iY29sLTYgYm9yZGVyLWVuZCBib3JkZXItMiI+PGRpdiBjbGFzcz0iYmctc2Vjb25kYXJ5IHRleHQtY2VudGVyIHRleHQtbGlnaHQgcm91bmRlZCI+PGgzPlRlbXBvcmFyeTwvaDM+PHAgY2xhc3M9ZnctbGlnaHRlcj4oU3RvcmVkIGluIHRoZSBtZW1vcnkpPC9kaXY+PGRpdiBjbGFzcz0ib3ZlcmZsb3ctYXV0byB2aC03MCJpZD10ZW1wPjwvZGl2PjxkaXY+PGRpdiBjbGFzcz0icm93IGFsaWduLWl0ZW1zLWNlbnRlciBnLTIiPjxkaXYgY2xhc3M9Y29sPjxpbnB1dCBjbGFzcz1mb3JtLWNvbnRyb2wgaWQ9dGVtcE5hbWUgcGxhY2Vob2xkZXI9dmFyaWFibGU+PC9kaXY+PGRpdiBjbGFzcz1jb2wtYXV0bz49PC9kaXY+PGRpdiBjbGFzcz1jb2w+PGlucHV0IGNsYXNzPWZvcm0tY29udHJvbCBpZD10ZW1wVmFsdWUgcGxhY2Vob2xkZXI9dmFsdWU+PC9kaXY+PGRpdiBjbGFzcz1jb2wtMTI+PGJ1dHRvbiBjbGFzcz0iYnRuIGJ0bi1wcmltYXJ5IHctMTAwImlkPXRlbXBTdWJtaXQ+Q2hhbmdlPC9idXR0b24+PC9kaXY+PC9kaXY+PC9kaXY+PC9kaXY+PGRpdiBjbGFzcz0iY29sLTYgYm9yZGVyLXN0YXJ0IGJvcmRlci0yIj48ZGl2IGNsYXNzPSJiZy1zZWNvbmRhcnkgdGV4dC1jZW50ZXIgdGV4dC1saWdodCByb3VuZGVkIj48aDM+UGVyc2lzdGVudDwvaDM+PHAgY2xhc3M9ZnctbGlnaHRlcj4oU3RvcmVkIGluIHRoZSBzdG9yYWdlKTwvZGl2PjxkaXYgY2xhc3M9Im92ZXJmbG93LWF1dG8gdmgtNzAiaWQ9cGVyc2lzdGVudD48L2Rpdj48ZGl2IGNsYXNzPSJyb3cgYWxpZ24taXRlbXMtY2VudGVyIGctMiI+PGRpdiBjbGFzcz1jb2w+PGlucHV0IGNsYXNzPWZvcm0tY29udHJvbCBpZD1wZXJzaXN0ZW50TmFtZSBwbGFjZWhvbGRlcj12YXJpYWJsZT48L2Rpdj48ZGl2IGNsYXNzPWNvbC1hdXRvPj08L2Rpdj48ZGl2IGNsYXNzPWNvbD48aW5wdXQgY2xhc3M9Zm9ybS1jb250cm9sIGlkPXBlcnNpc3RlbnRWYWx1ZSBwbGFjZWhvbGRlcj12YWx1ZT48L2Rpdj48ZGl2IGNsYXNzPWNvbC0xMj48YnV0dG9uIGNsYXNzPSJidG4gYnRuLXByaW1hcnkgdy0xMDAiaWQ9cGVyc2lzdGVudFN1Ym1pdD5DaGFuZ2U8L2J1dHRvbj48L2Rpdj48L2Rpdj48L2Rpdj48L2Rpdj48L2Rpdj48c2NyaXB0IGNyb3Nzb3JpZ2luIGludGVncml0eT1zaGEzODQtWXZwY3JZZjB0WTNsSEI2ME5Oa21YYzVzOWZEVlpMRVNhQUE1NU5Eek94aHk5R2tjSWRzbEsxZU43TjZqSWVIeiBzcmM9aHR0cHM6Ly9jZG4uanNkZWxpdnIubmV0L25wbS9ib290c3RyYXBANS4zLjMvZGlzdC9qcy9ib290c3RyYXAuYnVuZGxlLm1pbi5qcz48L3NjcmlwdD48c2NyaXB0PnZhciBvbkxvYWQ9KCgpPT57bGV0IGM9YGNsaWNrYDt1cGRhdGVEaXNwbGF5KCk7bGV0IGE9ZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoYHRlbXBTdWJtaXRgKTthLmFkZEV2ZW50TGlzdGVuZXIoYyx1cGRhdGVUZW1wb3JhcnkpO2xldCBiPWRvY3VtZW50LmdldEVsZW1lbnRCeUlkKGBwZXJzaXN0ZW50U3VibWl0YCk7Yi5hZGRFdmVudExpc3RlbmVyKGMsdXBkYXRlUGVyc2lzdGVudCl9KTt2YXIgdXBkYXRlUGVyc2lzdGVudD0oKCk9Pnt1cGRhdGUoYHBlcnNpc3RlbnRgKX0pO3ZhciBmZXRjaFZhbHVlcz0oYXN5bmMoKT0+e2xldCBhPXttZXRob2Q6YEdFVGB9O2xldCBiPWF3YWl0IGZldGNoKGAvYXBpYCxhKTtyZXR1cm4gYXdhaXQgYi5qc29uKCl9KTt2YXIgZmlsbD0oKGEsYik9PntsZXQgYz1kb2N1bWVudC5nZXRFbGVtZW50QnlJZChhKTtjLmlubmVySFRNTD1gYDtmb3IobGV0IGEgaW4gYil7Yy5pbm5lckhUTUwrPWNyZWF0ZVZpZXcoYSxiW2FdKX19KTt2YXIgdXBkYXRlRGlzcGxheT0oYXN5bmMoKT0+e2xldCBhPWF3YWl0IGZldGNoVmFsdWVzKCk7ZmlsbChgdGVtcGAsYS50ZW1wKTtmaWxsKGBwZXJzaXN0ZW50YCxhLnBlcnNpc3RlbnQpfSk7dmFyIHVwZGF0ZT0oYT0+e2xldCBiPWRvY3VtZW50LmdldEVsZW1lbnRCeUlkKGAke2F9TmFtZWApLnZhbHVlO2xldCBjPWRvY3VtZW50LmdldEVsZW1lbnRCeUlkKGAke2F9VmFsdWVgKS52YWx1ZTtsZXQgZD1geyR7YX06IHske2J9OiAke2N9fX1gO2xldCBlPWQubGVuZ3RoO2xldCBmPXttZXRob2Q6YFBPU1RgLGJvZHk6ZCxoZWFkZXJzOnsiQ29udGVudC1MZW5ndGgiOmV9fTtmZXRjaChgL2FwaWAsZil9KTt2YXIgdXBkYXRlVGVtcG9yYXJ5PSgoKT0+e3VwZGF0ZShgdGVtcGApfSk7dmFyIGNyZWF0ZVZpZXc9KChhLGIpPT5gPGRpdiBjbGFzcz0icm93IGJvcmRlci10b3AgYm9yZGVyLWJvdHRvbSBtLTEgcC0xIj48ZGl2IGNsYXNzPSJjb2wgZnctYm9sZGVyIj4ke2F9PC9kaXY+PGRpdiBjbGFzcz0iY29sLWF1dG8iPj08L2Rpdj48ZGl2IGNsYXNzPSJjb2wiPiR7Yn08L2Rpdj48L2Rpdj5gKTt3aW5kb3cuYWRkRXZlbnRMaXN0ZW5lcihgbG9hZGAsb25Mb2FkKTtzZXRJbnRlcnZhbCh1cGRhdGVEaXNwbGF5LDEwMCk8L3NjcmlwdD4="
            ;

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
