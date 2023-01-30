package org.encinet.nirvana.mixins.client;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    // 如果目标方法无返回值类型，那么在添加目标方法参数之后必须有一个参数，且必须是 CallbackInfo 类型。
    @Inject(
            method = "createDisplay",
            at = @At(
                    value = "INVOKE", // 表示注入在方法被调用之前
                    shift = At.Shift.AFTER, // 把注入点往后移一位，也就变成注入在方法被调用之后
                    target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V",
                    remap = false // <-- 因为 Display.setTitle 并不存在于混淆表中，所以设置 remap 属性为 false
            )
    )
    private void inject_createDisplay(CallbackInfo ci) {
        Display.setTitle("Test Title");
        // ci.cancel() // <-- 如果调用了 CallbackInfo::cancel ，就表示执行完注入方法后目标方法直接return，不再执行原方法剩余的代码。
        // cancel() 方法能被调用的前提是在 @Inject 里设置 cancellable 属性为 true ，否则会抛出异常。
    }

    @Inject(
            method = "getLimitFramerate",
            at = @At("HEAD"),
            cancellable = true
    )
    private void inject_getLimitFramerate(CallbackInfoReturnable<Integer> cir) {
        // 功能: 窗口未聚焦时 FPS锁定为5
        if (!Display.isActive()) {
            cir.setReturnValue(5);
        }
    }
}
