package dev.repliedk;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.level.Sound;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.AddEntityPacket;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void death(PlayerDeathEvent event) {
        Player player = event.getEntity();
        AddEntityPacket addEntityPacket = new AddEntityPacket();
        addEntityPacket.type = 93;
        addEntityPacket.entityRuntimeId = Entity.entityCount++;
        addEntityPacket.pitch = (float) player.getPitch();
        addEntityPacket.x = player.getFloorX();
        addEntityPacket.y = player.getFloorY();
        addEntityPacket.z = player.getFloorZ();
        Server.broadcastPacket(player.getLevel().getPlayers().values(), addEntityPacket);
        player.level.addSound(new Vector3(player.getX(), player.getY(), player.getZ()), Sound.AMBIENT_WEATHER_THUNDER);
    }

}