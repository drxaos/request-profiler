package com.github.drxaos.profiler.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProfilingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ProfilerInfo.record(((HttpServletRequest) request).getRequestURI());
        chain.doFilter(request, response);
        ProfilerInfo.Record results = ProfilerInfo.results();

        Map<Integer, ProfilerInfo.Call> map = new HashMap<Integer, ProfilerInfo.Call>();

        for (Iterator<ProfilerInfo.Call> iterator = results.calls.iterator(); iterator.hasNext(); ) {
            ProfilerInfo.Call next = iterator.next();
            if (next.start != null) {
                map.put(next.depth, next);
            } else {
                map.get(next.depth + 1).end(next.end);
                iterator.remove();
            }
        }

        System.out.println("\n\n### " + results.url + " ###");
        for (ProfilerInfo.Call call : results.calls) {
            for (int i = 0; i < call.depth; i++) {
                System.out.print(">");
            }
            System.out.println(call.cls + "::" + call.mth + " - " + (call.end - call.start) + "ms");
        }
        System.out.println("----------\n\n");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void destroy() {
    }
}