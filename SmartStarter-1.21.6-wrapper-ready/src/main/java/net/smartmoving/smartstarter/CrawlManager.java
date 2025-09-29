package net.smartmoving.smartstarter;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.EntityPose;

public final class CrawlManager {
    private static boolean crawling = false;
    public static void toggleCrawl(PlayerEntity p) {
        crawling = !crawling;
        p.setPose(crawling ? EntityPose.SWIMMING : EntityPose.STANDING);
    }
    public static boolean isCrawling(){ return crawling; }
}
