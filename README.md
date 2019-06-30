# java-dead-lock-detection-lib

This library can be used to detect dead lock situation in java application.   

## Note
We recommanded to contribute or intimate if any improvement or issue found. This will become matured as time  goes, and to eliminate the requirement forsetting any monitoring tool immediatly when dedlock is detected in live system.

## Installation

Use maven to build library in local machine.

```bash
mvn clean install
```

## Usage

```java
DeadLockDetection.getInstance().start();
DeadLockDetection.getInstance().registerListener(new DeadLockDetectionListener() {
            @Override
            public void onDeadlockDetected(ThreadInfo[] threadInfos, ThreadInfo[] threadInfoLockings) {
               
            }
        });
DeadLockDetection.getInstance().stop();
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to mention what you would like to change.

## Roadmap
1. Add test cases
2. Configurable checking interval and start up 
delay
3. Java documentation
4. Upload to maven repository
5. Create another auto starter for spring boot
6. Add appropriate license
## License
None
