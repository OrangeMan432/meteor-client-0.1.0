package minegame159.meteorclient.modules.movement;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import minegame159.meteorclient.events.TickEvent;
import minegame159.meteorclient.mixininterface.IKeyBinding;
import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;

public class AutoWalk extends Module {
    public enum Mode {
        Simple
    }

    private Setting<Mode> mode = addSetting(new EnumSetting.Builder<Mode>()
            .name("mode")
            .description("Walking mode.")
            .defaultValue(Mode.Simple)
            .build()
    );

    private int timer = 0;

    public AutoWalk() {
        super(Category.Movement, "auto-walk", "Automatically walks forward.");
    }

    @Override
    public void onActivate() {
    }

    @Override
    public void onDeactivate() {
        if (mode.get() == Mode.Simple) ((IKeyBinding) mc.options.keyForward).setPressed(false);
    }

    @EventHandler
    private Listener<TickEvent> onTick = new Listener<>(event -> {
        if (mode.get() == Mode.Simple) {
            ((IKeyBinding) mc.options.keyForward).setPressed(true);
        }
    });
}
