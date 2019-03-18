package org.dq;

import org.joda.time.DateTime;

/**
 * @Author: duanqiong
 * @Date: 2019/3/18 22:31
 * @Version 1.0
 */
public class Test {
    public void myTest() {
        try {
            System.out.println("test" + System.currentTimeMillis());
            Thread.sleep(10000);
            System.out.println("test");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
