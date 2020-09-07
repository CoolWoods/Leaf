package cn.qingmu;

import cn.qingmu.util.IdWorker;

public class TestIdWorker {
    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker();
        for (int i = 0; i <100*100; i++) {
            long l = idWorker.nextId();
            System.out.println(l);
        }
    }
}
