package com.github.drxaos.profiler.util;

import java.util.ArrayList;

public class ProfilerInfo {

    public static class Call {
        String cls;
        String mth;
        Long start;
        Long end;
        int depth;

        public Call cls(String cls) {
            this.cls = cls;
            return this;
        }

        public Call mth(String mth) {
            this.mth = mth;
            return this;
        }

        public Call start(Long start) {
            this.start = start;
            return this;
        }

        public Call end(Long end) {
            this.end = end;
            return this;
        }

        public Call depth(int depth) {
            this.depth = depth;
            return this;
        }

    }

    public static class Record {
        String url;
        ArrayList<Call> calls = new ArrayList<Call>();

        public Record(String url) {
            this.url = url;
        }
    }

    static ThreadLocal<Record> records = new ThreadLocal<Record>();

    public static void record(String url) {
        records.set(new Record(url));
    }

    public static Record results() {
        Record record = records.get();
        records.set(null);
        return record;
    }

    public static void start(String c, String m) {
        Record rec = records.get();
        if (rec == null) {
            return;
        }

        int depth = rec.calls.size() > 0 ? rec.calls.get(rec.calls.size() - 1).depth : 0;

        rec.calls.add(new Call()
                .cls(c)
                .mth(m)
                .start(System.currentTimeMillis())
                .depth(depth + 1)
        );
    }

    public static void end(String c, String m) {
        Record rec = records.get();
        if (rec == null) {
            return;
        }

        int depth = rec.calls.size() > 0 ? rec.calls.get(rec.calls.size() - 1).depth : 0;

        rec.calls.add(new Call()
                .cls(c)
                .mth(m)
                .end(System.currentTimeMillis())
                .depth(depth - 1)
        );
    }


}
