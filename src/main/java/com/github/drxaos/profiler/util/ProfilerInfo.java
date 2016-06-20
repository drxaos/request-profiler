package com.github.drxaos.profiler.util;

import java.util.ArrayList;

public class ProfilerInfo {

    static class R {
        String cls;
        String mth;
        Long start;
        Long end;
        int depth;

        public R cls(String cls) {
            this.cls = cls;
            return this;
        }

        public R mth(String mth) {
            this.mth = mth;
            return this;
        }

        public R start(Long start) {
            this.start = start;
            return this;
        }

        public R end(Long end) {
            this.end = end;
            return this;
        }

        public R depth(int depth) {
            this.depth = depth;
            return this;
        }

    }

    static ThreadLocal<ArrayList<R>> records = new ThreadLocal<ArrayList<R>>();

    public static void record() {
        records.set(new ArrayList<R>());
    }

    public static ArrayList<R> results() {
        ArrayList<R> rs = records.get();
        records.set(null);
        return rs;
    }

    public static void start(String c, String m) {
        ArrayList<R> rec = records.get();
        if (rec == null) {
            return;
        }

        int depth = rec.size() > 0 ? rec.get(rec.size() - 1).depth : 0;

        rec.add(new R()
                .cls(c)
                .mth(m)
                .start(System.currentTimeMillis())
                .depth(depth + 1)
        );
    }

    public static void end(String c, String m) {
        ArrayList<R> rec = records.get();
        if (rec == null) {
            return;
        }

        int depth = rec.size() > 0 ? rec.get(rec.size() - 1).depth : 0;

        rec.add(new R()
                .cls(c)
                .mth(m)
                .end(System.currentTimeMillis())
                .depth(depth - 1)
        );
    }


}
