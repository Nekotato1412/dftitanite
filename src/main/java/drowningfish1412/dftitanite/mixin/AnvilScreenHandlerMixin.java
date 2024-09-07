package drowningfish1412.dftitanite.mixin;

import drowningfish1412.dftitanite.events.AnvilUpdateCallback;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void updateResult(CallbackInfo ci) {
        ActionResult result = AnvilUpdateCallback.EVENT.invoker().update((AnvilScreenHandler) (Object) this);
        if (result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
