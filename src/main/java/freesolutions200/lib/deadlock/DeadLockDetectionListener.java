package freesolutions200.lib.deadlock;

import java.lang.management.ThreadInfo;

public interface DeadLockDetectionListener {

    void onDeadlockDetected(ThreadInfo[] threadInfos, ThreadInfo[] threadInfoLockings);
}
