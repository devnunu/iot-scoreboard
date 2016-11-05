package com.example.anew.score;

/**
 * Created by new on 2016. 9. 17..
 */

public class StopWatch {
    private long startTime = 0; // 시작시간
    private long stopTime = System.nanoTime();  // 종료 시간
    private long pauseTime = 0;
    private long elapsed = 0;
    private boolean running = false;    // 시작되었는지 확인
    private boolean flag = false;    // 시작되었는지 확인

    public void start() {
        this.startTime = System.nanoTime();     // 시간 재기 시작
        this.running = true;
    }

    public void stop() {
        this.stopTime = System.nanoTime();
        this.running = false;
        this.flag=true;
    }

    public void restart(){
        pauseTime += System.nanoTime() - stopTime;
        this.running = true;
        this.flag = true;
    }

    public void reset() {
        this.startTime = 0;
        this.stopTime = 0;
        this.running = false;
    }

    // 정지와 리셋
    public void refresh(){
        stop();
        reset();
    }

    //elaspsed time in microseconds
    public long getElapsedTimeMicro() {
        if (running) {
            elapsed = ((System.nanoTime() - startTime) / 1000);
        }else{
            elapsed = ((stopTime - startTime) / 1000);
        }

        return elapsed;
    }

    //elaspsed time in milliseconds
    public long getElapsedTimeMilli() {
        if (running) {
            if(!flag)
                elapsed = ((System.nanoTime() - startTime) / 100000);
            else
                elapsed = (((System.nanoTime() - pauseTime - startTime) / 100000));
        }
        else{
            elapsed = ((stopTime - pauseTime - startTime) / 100000);
        }

        return elapsed;
    }

    public double getFormatF(){
        double ftime = 0.0;

        long ltime = getElapsedTimeMilli();

        double nTime = ltime / 10000.0;

        ftime = Double.parseDouble(String.format("%02f", nTime));

        return ftime;
    }
}