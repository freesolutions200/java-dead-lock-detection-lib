package freesolutions200.internal;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;

import static freesolutions200.internal.Constants.*;

public class Util {

    public static ThreadInfo[] getDeadLockedThreads() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadBean.findDeadlockedThreads();
        HashMap<Long, ThreadInfo> threadInfoHashSet;
        if(threadIds != null){
            threadInfoHashSet = new HashMap<>(2);
            return threadBean.getThreadInfo(threadIds, DEPTH);
        }
        return null;
    }

    public static ThreadInfo[] getThreadLockingInfo(ThreadInfo[] threadInfos) {
        if(threadInfos == null){
            return null;
        }
        long[] threadIds = new long[threadInfos.length];
        for(int i = 0; i < threadInfos.length; i++){
            threadIds[i] = threadInfos[i].getThreadId();
        }
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        return threadBean.getThreadInfo(threadIds, LOCKED_MONITORS, LOCKED_SYNCHRONIZERS);
    }
}
