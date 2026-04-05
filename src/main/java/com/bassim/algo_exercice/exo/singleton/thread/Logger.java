package com.bassim.algo_exercice.exo.singleton.thread;

class Logger {
    private static final ThreadLocal<Logger> threadInstance =
            ThreadLocal.withInitial(Logger::new);

    private Logger() {
        System.out.println("Logger créé pour le thread : " + Thread.currentThread().getName());
    }

    public static Logger getInstance() {
        return threadInstance.get();
    }

    public void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}


