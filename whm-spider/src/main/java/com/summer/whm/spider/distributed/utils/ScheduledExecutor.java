package com.summer.whm.spider.distributed.utils;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.spider.distributed.utils.jdk7.ScheduledThreadPoolExecutor;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public class ScheduledExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledExecutor.class);

    private ScheduledExecutorService scheduledExecutor;

    private static ScheduledExecutor INSTANCE = new ScheduledExecutor();

    public static ScheduledExecutor getInstance() {
        return INSTANCE;
    }

    private ScheduledExecutor() {
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(
                2, new NamedThreadFactory("global.scheduler", true));
        scheduledExecutor.setMaximumPoolSize(2);
        scheduledExecutor.setKeepAliveTime(120, TimeUnit.SECONDS);
        scheduledExecutor.allowCoreThreadTimeOut(true);
        scheduledExecutor.setRemoveOnCancelPolicy(true);
        this.scheduledExecutor = scheduledExecutor;
    }

    public void shutdown() {
        if (scheduledExecutor == null) {
            return;
        }
        try {
            scheduledExecutor.shutdown();
            if (!scheduledExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                logger.warn("Executor did not terminate in the specified time.");
                List<Runnable> droppedTasks = scheduledExecutor.shutdownNow();
                logger.warn("Executor was abruptly shut down. "
                        + droppedTasks.size() + " tasks will not be executed.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // ignored
        }
    }

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return scheduledExecutor.schedule(command, delay, unit);
    }

    public ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay,
                                                  long delay, TimeUnit unit) {
        return scheduledExecutor.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }
}
