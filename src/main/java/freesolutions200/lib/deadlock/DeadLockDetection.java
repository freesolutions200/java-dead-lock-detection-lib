package freesolutions200.lib.deadlock;

import freesolutions200.lib.deadlock.internal.Constants;
import freesolutions200.lib.deadlock.internal.Util;

import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DeadLockDetection {
    private static DeadLockDetection deadLockDetection = new DeadLockDetection();
    private DeadLockDetector deadLockDetector;
    private ArrayList<DeadLockDetectionListener> deadLockDetectionListeners;
    private DeadLockDetection(){
    }
    public static DeadLockDetection getInstance(){
        return deadLockDetection;
    }
    public void start(){
        deadLockDetector = new DeadLockDetector();
        deadLockDetector.start();
    }

    public void registerListener(DeadLockDetectionListener deadLockDetectionListener){
        if(deadLockDetectionListeners == null){
            deadLockDetectionListeners = new ArrayList<>(1);
        }
        deadLockDetectionListeners.add(deadLockDetectionListener);
    }

    private class  DeadLockDetector {
        private Timer timer;
        public void start(){
            timer = new Timer();
            timer.scheduleAtFixedRate(new DeadLockDetectorTask(), Constants.DELAY, Constants.INTERVAL);
        }
        public void stop(){
            timer.cancel();
            timer.purge();
        }

        private class DeadLockDetectorTask extends TimerTask {
            @Override
            public void run() {
                ThreadInfo[] threadInfos = Util.getDeadLockedThreads();
                if(threadInfos == null){
                    return;
                }
                ThreadInfo[] threadInfoLockings = Util.getThreadLockingInfo(threadInfos);
                onDeadlockDetected(threadInfos, threadInfoLockings);
            }
        }

        private void onDeadlockDetected( ThreadInfo[] threadInfos ,ThreadInfo[] threadInfoLockings){
            if(deadLockDetectionListeners != null){
                for(DeadLockDetectionListener listener : deadLockDetectionListeners){
                    listener.onDeadlockDetected(threadInfos, threadInfoLockings);
                }
            }
        }
    }
}
