package drowningfish1412.dftitanite.events;

import net.fabricmc.fabric.api.event.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.util.ActionResult;

public interface AnvilUpdateCallback {
    /**
     * Callback for updating an anvil
     * Called before the anvil's cost and output is updated
     * Upon return:
     * - SUCCESS cancels further processing and continues with normal anvil behavior.
     * - PASS falls back to further processing and defaults to SUCCESS if no other listeners are available
     * - FAIL cancels default anvil behavior and prevents the anvil from updating its cost and output.
     */
    Event<AnvilUpdateCallback> EVENT = EventFactory.createArrayBacked(AnvilUpdateCallback.class,
            (listeners) -> (anvil) -> {
                for ( AnvilUpdateCallback listener : listeners ) {
                    ActionResult result = listener.update(anvil);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult update(AnvilScreenHandler anvil);
}
