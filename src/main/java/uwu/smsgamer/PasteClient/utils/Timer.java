package uwu.smsgamer.PasteClient.utils;

public class Timer {
    public long previousTime;

    public Timer() {
        previousTime = -1L;
    }

    public boolean check(float milliseconds) {
        return getTime() >= milliseconds;
    }

    public long getTime() {
        return getCurrentTime() - previousTime;
    }

    public void reset() {
        previousTime = getCurrentTime();
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public boolean delay(float milliSec) {
        return (getTime() - this.previousTime) >= milliSec;
    }

    public static long randomDelay(int minDelay, int maxDelay) {
        return RandomUtils.nextInt(minDelay, maxDelay);
    }

    public static long randomClickDelay(int minCPS, int maxCPS) {
        return (long)(Math.random() * (double)(1000 / minCPS - 1000 / maxCPS + 1) + (double)(1000 / maxCPS));
    }

}